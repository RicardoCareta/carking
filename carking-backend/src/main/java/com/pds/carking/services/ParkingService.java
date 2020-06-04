package com.pds.carking.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.carking.dto.CustomerDTO;
import com.pds.carking.dto.EmployeeDTO;
import com.pds.carking.dto.ParkingDTO;
import com.pds.carking.dto.ParkingParkingPlaceFilterDTO;
import com.pds.carking.dto.VehicleDTO;
import com.pds.carking.exception.InvalidInputDataException;
import com.pds.carking.exception.NotFoundException;
import com.pds.carking.model.Customer;
import com.pds.carking.model.Driver;
import com.pds.carking.model.Employee;
import com.pds.carking.model.Parking;
import com.pds.carking.model.ParkingPlace;
import com.pds.carking.model.Vehicle;
import com.pds.carking.model.enums.ParkingPlaceStatus;
import com.pds.carking.model.enums.ParkingStatus;
import com.pds.carking.model.enums.Roles;
import com.pds.carking.repository.CustomerRepository;
import com.pds.carking.repository.EmployeeRepository;
import com.pds.carking.repository.ParkingRepository;
import com.pds.carking.repository.VehicleRepository;
import com.pds.carking.security.JwtTokenUtil;
import com.pds.carking.util.MoneyFormatter;

@Service
public class ParkingService {

	@Autowired
	private ParkingRepository parkingRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DriverService driverService;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public static final int PARK_SIZE_R = 4;
	public static final int PARK_SIZE_C = 6;

	public String storeParking(@Valid ParkingDTO parkingDTO) throws Exception {
		final String attendantUsername = jwtTokenUtil.getUsernameFromToken(parkingDTO.getAttendant());

		final Employee attendant = employeeRepository.findByUsername(attendantUsername);
		employeeExistsAndMatchRole(attendant, Roles.Values.ATTENDANT);

		final String driverId = parkingDTO.getDriver();
		final Optional<Employee> opDriver = employeeRepository.findById(UUID.fromString(driverId));

		employeeExistsAndMatchRole(opDriver, Roles.Values.DRIVER);
		Driver driver = (Driver) opDriver.get();

		String vehicleId = vehicleService.storeVehicle(parkingDTO.getVehicle());
		Vehicle vehicle = vehicleRepository.findById(UUID.fromString(vehicleId)).get();

		String customerId = customerService.storeCustomer(parkingDTO.getCustomer());
		Customer customer = customerRepository.findById(UUID.fromString(customerId)).get();

		Parking parking = modelMapper.map(parkingDTO, Parking.class);
		parking.setDriver(driver);
		parking.setAttendent(attendant);
		parking.setParkingPlace(new ParkingPlace(parkingDTO.getParkingPlace()));
		parking.setVehicle(vehicle);
		parking.setCustomer(customer);

		parking = parkingRepository.save(parking);
		return parking.getId().toString();
	}

	public List<Map<String, Object>> getParking() {
		List<Parking> parkings = parkingRepository.findAll();
		List<Map<String, Object>> parkingMaps = new ArrayList<Map<String, Object>>();
		for (Parking parking : parkings) {
			Map<String, Object> parkingMap = new LinkedHashMap<String, Object>();
			parkingMap.put("parkingPlace", parking.getParkingPlace().getName());
			parkingMap.put("customer", modelMapper.map(parking.getCustomer(), CustomerDTO.class));
			parkingMap.put("driver", modelMapper.map(parking.getDriver(), EmployeeDTO.class));
			parkingMap.put("attendant", modelMapper.map(parking.getAttendent(), EmployeeDTO.class));
			parkingMap.put("vehicle", modelMapper.map(parking.getVehicle(), VehicleDTO.class));

			parkingMaps.add(parkingMap);
		}
		return parkingMaps;
	}
	
	public ParkingParkingPlaceFilterDTO getParkingByFilter (String parkingPlace) throws NotFoundException {
		Parking parking = parkingRepository.findByParkingPlace(new ParkingPlace(parkingPlace));
		if (parking == null) {
			throw new NotFoundException("Parking not found");
		}
		
		ParkingParkingPlaceFilterDTO pFilterDTO = modelMapper.map(parking, ParkingParkingPlaceFilterDTO.class);
		pFilterDTO.setCustomerDTO(modelMapper.map(parking.getCustomer(), CustomerDTO.class));
		pFilterDTO.setPrice(MoneyFormatter.formatMoney(getPriceParking(parking)));
		
		return pFilterDTO;
	}

	public List<List<Map<String, Object>>> getParkingMapped () {
		//List<Parking> parkings = parkingRepository.findAll();
		List<List<Map<String, Object>>> parkingMaps = new ArrayList<List<Map<String, Object>>>();
		
		for (int row = 0; row < PARK_SIZE_R; row++) {
			parkingMaps.add(new ArrayList<>());
			for (int column = 0; column < PARK_SIZE_C; column++) {
				
				String parkingPlace = (row + 1) + "" +(char) (65 + column);
				Map<String, Object> parkingMap = new LinkedHashMap<>();
				
				parkingMap.put("parkingPlace", parkingPlace);
				parkingMap.put("status", ParkingPlaceStatus.EMPTY.getStatus());
				
				Parking parking = parkingRepository.findByParkingPlace(new ParkingPlace(parkingPlace));
				if (parking != null && parking.getStatus() != ParkingStatus.PARK_EXIT) {
					parkingMap.put("id", parking.getId().toString());
					parkingMap.put("vehicle", modelMapper.map(parking.getVehicle(), VehicleDTO.class));
					parkingMap.put("status", ParkingPlaceStatus.BUSY.getStatus());
				}
				
				parkingMaps.get(row).add(parkingMap);
			}
		}
		
		return parkingMaps;
	}
	
	public Map<String, Object> takeOffVehicleParking (String parkingId) throws NotFoundException, InvalidInputDataException {
		Optional<Parking> optParking = parkingRepository.findById(UUID.fromString(parkingId));
		if (!optParking.isPresent()) {
			throw new NotFoundException("Parking not found");
		}
		Parking parking = optParking.get();
		
		if (parking.getStatus() != ParkingStatus.PARKED) {
			throw new InvalidInputDataException("Parking in invalid status");
		}
		
		Driver driver = driverService.randomDriver();
		driver.setBusy(true);
		
		parking.setDriverTakeoff(driver);
		parking.setRemoveDate(new Date());
		
		long price = getPriceParking(parking);
		
		employeeRepository.save(driver);
		parkingRepository.save(parking);
		
		Map<String, Object> returnTakeOffVehicle = new LinkedHashMap<>();
		returnTakeOffVehicle.put("price", price);
		returnTakeOffVehicle.put("driver", driver);
		
		return returnTakeOffVehicle;
	}
	
	private Long getPriceParking (Parking parking) {
		final Date startDate = parking.getParkedDate();
		final Date endDate = (parking.getRemoveDate() == null ? new Date() : parking.getRemoveDate());
		
		long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
		long diffMinutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		long price = 0L;
		
		if (diffMinutes > 15) {
			int diffHours = Math.toIntExact(diffMinutes / 60);
			if (diffHours == 0) {
				diffHours = 1;
			}
			price = diffHours * 10;
		}
		return price;
	}

	private boolean employeeExistsAndMatchRole(Employee employee, String role) throws NotFoundException {
		return employeeExistsAndMatchRole(Optional.ofNullable(employee), role);
	}

	private boolean employeeExistsAndMatchRole(Optional<Employee> opEmployee, String role) throws NotFoundException {
		if (!opEmployee.isPresent()) {
			throw new NotFoundException("Employee " + role + " not found");
		}
		if (!opEmployee.get().getRole().equals(role)) {
			throw new NotFoundException("Employee " + role + " not found");
		}
		return true;
	}
}
