package com.pds.carking.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.carking.dto.CustomerDTO;
import com.pds.carking.dto.ParkingInformationToDriver;
import com.pds.carking.exception.InvalidInputDataException;
import com.pds.carking.exception.NotFoundException;
import com.pds.carking.model.Driver;
import com.pds.carking.model.Parking;
import com.pds.carking.model.enums.ParkingStatus;
import com.pds.carking.repository.DriverRepository;
import com.pds.carking.repository.EmployeeRepository;
import com.pds.carking.repository.ParkingRepository;
import com.pds.carking.security.JwtTokenUtil;

@Service
public class DriverService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private ParkingRepository parkingRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private ModelMapper modelMapper;

	public Driver randomDriver() throws NotFoundException {
		return this.randomDriver(false);
	}

	public Driver randomDriver(boolean requireNoBusy) throws NotFoundException {
		return randomDriver(requireNoBusy, false);
	}

	public Driver randomDriver(boolean requireNoBusy, boolean isBusy) throws NotFoundException {

		List<Driver> drivers = employeeRepository.findByIsBusy(isBusy);
		if (drivers.isEmpty() && requireNoBusy) {
			throw new NotFoundException("Not found driver");
		}

		else if (drivers.isEmpty()) {
			drivers = driverRepository.findAll();

		}
		Random rand = new Random();
		return drivers.get(rand.nextInt(drivers.size()));
	}

	public List<ParkingInformationToDriver> getDriversAuthentication(String token, ParkingStatus parkingStatus)
			throws NotFoundException, InvalidInputDataException {
		final String username = jwtTokenUtil.getUsernameFromToken(token);

		final Driver driver = getDriverFromUsername(username);

		return getDriversParking(driver.getId().toString(), parkingStatus);
	}

	public List<ParkingInformationToDriver> getDriversEntranceAuthentication(String token) throws NotFoundException, InvalidInputDataException {
		return getDriversAuthentication(token, ParkingStatus.PARK_ENTER);
	}

	public List<ParkingInformationToDriver> getDriversTakeoffAuthentication(String token) throws NotFoundException, InvalidInputDataException {
		return getDriversAuthentication(token, ParkingStatus.PARKED);
	}

	public List<ParkingInformationToDriver> getDriversEntrance(String driverId) throws InvalidInputDataException {
		return getDriversParking(driverId, ParkingStatus.PARK_ENTER);
	}

	public List<ParkingInformationToDriver> getDriversTakeoff(String driverId) throws InvalidInputDataException {
		return getDriversParking(driverId, ParkingStatus.PARKED);
	}

	public List<ParkingInformationToDriver> getDriversParking(String driverId, ParkingStatus parkingStatus) throws InvalidInputDataException {
		List<Parking> parkingsEntrance = parkingRepository.findByStatus(parkingStatus);

		if (parkingStatus == ParkingStatus.PARKED) {
			parkingsEntrance = parkingsEntrance.stream()
					.filter(parking -> parking.getDriverTakeoff() != null
							&& parking.getDriverTakeoff().getId().equals(UUID.fromString(driverId)))
					.collect(Collectors.toList());
		}

		else if (parkingStatus == ParkingStatus.PARK_ENTER) {
			parkingsEntrance = parkingsEntrance.stream().filter(parking -> parking.getDriver() != null)
					.collect(Collectors.toList());
		}
		else {
			throw new InvalidInputDataException("Invalid status");
		}

		List<ParkingInformationToDriver> parkingInformationToDrivers = Arrays
				.asList(modelMapper.map(parkingsEntrance, ParkingInformationToDriver[].class));

		for (int i = 0; i < parkingInformationToDrivers.size(); i++) {
			parkingInformationToDrivers.get(i)
					.setCustomerDTO(modelMapper.map(parkingsEntrance.get(i).getCustomer(), CustomerDTO.class));
		}

		return parkingInformationToDrivers;
	}

	public String driverConfirmParkingVehicle(String idDriver, String idParking, boolean isEntrance)
			throws NotFoundException {
		Optional<Parking> opParking = parkingRepository.findById(UUID.fromString(idParking));

		if (!opParking.isPresent()) {
			throw new NotFoundException("Parking not found");
		}

		ParkingStatus nextStatus = null;

		if (isEntrance) {
			if (!opParking.get().getDriver().getId().toString().equals(idDriver)) {
				throw new NotFoundException("Driver not found for this parking");
			} else if (opParking.get().getStatus() != ParkingStatus.PARK_ENTER) {
				throw new NotFoundException("Parking operation not corresponds with current status");
			}
			nextStatus = ParkingStatus.PARKED;
		} else {
			if (!opParking.get().getDriverTakeoff().getId().toString().equals(idDriver)) {
				throw new NotFoundException("Driver not found for this parking");
			} else if (opParking.get().getStatus() != ParkingStatus.PARKED) {
				throw new NotFoundException("Parking operation not corresponds with current status");
			}
			nextStatus = ParkingStatus.PARK_EXIT;
		}

		Parking parking = opParking.get();
		parking.setStatus(nextStatus);

		parkingRepository.save(parking);

		return "{\"id\":\"" + parking.getId().toString() + "\"}";
	}

	public String driverConfirmParkingVehicleAuthentication(String token, String parkingId, boolean isEntrance)
			throws NotFoundException {
		final String driverUsername = jwtTokenUtil.getUsernameFromToken(token);
		final String driverId = getDriverFromUsername(driverUsername).getId().toString();
		return driverConfirmParkingVehicle(driverId, parkingId, isEntrance);
	}

	private Driver getDriverFromUsername(String username) throws NotFoundException {
		Driver driver = driverRepository.findByUsername(username);
		if (driver == null) {
			throw new NotFoundException("Driver not found");
		}
		return driver;
	}

}
