package com.pds.carking.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.carking.dto.CustomerDTO;
import com.pds.carking.dto.EmployeeDTO;
import com.pds.carking.dto.ParkingDTO;
import com.pds.carking.dto.VehicleDTO;
import com.pds.carking.exception.NotFoundException;
import com.pds.carking.model.Customer;
import com.pds.carking.model.Driver;
import com.pds.carking.model.Employee;
import com.pds.carking.model.Parking;
import com.pds.carking.model.ParkingPlace;
import com.pds.carking.model.Vehicle;
import com.pds.carking.model.enums.Roles;
import com.pds.carking.repository.CustomerRepository;
import com.pds.carking.repository.EmployeeRepository;
import com.pds.carking.repository.ParkingRepository;
import com.pds.carking.repository.VehicleRepository;
import com.pds.carking.security.JwtTokenUtil;

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
	private ModelMapper modelMapper;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public String storeParking(@Valid ParkingDTO parkingDTO) throws Exception{
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
			Map<String, Object> parkingMap = new HashMap<String, Object>();
			parkingMap.put("customer", modelMapper.map(parking.getCustomer(), CustomerDTO.class));
			parkingMap.put("parkingPlace", parking.getParkingPlace().getName());
			parkingMap.put("driver", modelMapper.map(parking.getDriver(), EmployeeDTO.class));
			parkingMap.put("attendant", modelMapper.map(parking.getAttendent(), EmployeeDTO.class));
			parkingMap.put("vehicle", modelMapper.map(parking.getVehicle(), VehicleDTO.class));
			
			parkingMaps.add(parkingMap);
		}
		return parkingMaps;
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
