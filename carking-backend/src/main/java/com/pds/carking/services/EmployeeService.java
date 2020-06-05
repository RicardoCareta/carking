package com.pds.carking.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.carking.dto.EmployeeDTO;
import com.pds.carking.dto.LoginDTOResponse;
import com.pds.carking.dto.abstracts.EmployeeBaseDTO;
import com.pds.carking.exception.InvalidInputDataException;
import com.pds.carking.exception.NotFoundException;
import com.pds.carking.model.Attendant;
import com.pds.carking.model.Driver;
import com.pds.carking.model.Employee;
import com.pds.carking.model.Manager;
import com.pds.carking.model.enums.Roles;
import com.pds.carking.repository.DriverRepository;
import com.pds.carking.repository.EmployeeRepository;
import com.pds.carking.security.JwtTokenUtil;
import com.pds.carking.util.MapOperations;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public EmployeeDTO updateEmployee(EmployeeBaseDTO employeeDTO, String employeeToken) throws NotFoundException, InvalidInputDataException {

		final String employeeUsername = jwtTokenUtil.getUsernameFromToken(employeeToken);
		
		
		if (!employeeUsername.equals(employeeDTO.getUsername())) { 
			if (usernameIsUsed(employeeDTO.getUsername())) {
				throw new InvalidInputDataException("An employee with that username already exists");
			}
		}
		
		Employee employee = employeeRepository.findByUsername(employeeUsername);
		
		employee.setName(employeeDTO.getName());
		employee.setPassword(employeeDTO.getPassword());
		employee.setUsername(employeeDTO.getUsername());

		employeeRepository.save(employee);
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	public String storeEmployee(EmployeeDTO employeeDTO) throws NotFoundException, InvalidInputDataException {

		Employee employee = null;
		
		if (usernameIsUsed(employeeDTO.getUsername())) {
			throw new InvalidInputDataException("An employee with that username already exists");
		}
		
		switch (employeeDTO.getRole()) {
		case Roles.Values.MANAGER:
			employee = modelMapper.map(employeeDTO, Manager.class);
			break;

		case Roles.Values.ATTENDANT:
			employee = modelMapper.map(employeeDTO, Attendant.class);
			break;

		case Roles.Values.DRIVER:
			employee = modelMapper.map(employeeDTO, Driver.class);
			break;
		default :
			throw new NotFoundException("Role not found");
		}
		
		employee.setId(UUID.randomUUID());
		employee = employeeRepository.save(employee);

		return employee.getId().toString();
	}

	public EmployeeDTO getEmployee(String employeeId) {
		Optional<Employee> optEmployee = employeeRepository.findById(UUID.fromString(employeeId));
		if (optEmployee.isPresent()) {
			return modelMapper.map(optEmployee.get(), EmployeeDTO.class);
		}
		return null;
	}
	
	public Employee getEmployeeToken (String token) throws NotFoundException {
		final String username = jwtTokenUtil.getUsernameFromToken(token);
		return getEmployeeFromUsername(username);
	}

	public Employee getEmployeeFromUsername(String userName) throws NotFoundException {
		Employee employee = employeeRepository.findByUsername(userName);
		if (employee != null) {
			return employee;
		}
		throw new NotFoundException("Employee not found");
	}

	public Employee loginEmployee(String username, String password) throws NotFoundException {
		Employee employee = getEmployeeFromUsername(username);
		if (employee.getPassword().equals(password)) {
			return employee;
		}
		return null;
	}

	public List<Map<String, Object>> getAllEmployee() {
		List<Employee> employees = employeeRepository.findAll();
		List<Map<String, Object>> mapEmployeeDTO = new ArrayList<Map<String, Object>>();
		
		for (Employee employee : employees) {
			EmployeeBaseDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
			
			Map<String, Object> mapEmployee = MapOperations.convertToMap(employeeDTO);
			mapEmployee.put("id", employee.getId().toString());
			mapEmployee.put("role", employee.getRole());
			
			mapEmployeeDTO.add(mapEmployee);
		}
		return mapEmployeeDTO;
	}
	
	public LoginDTOResponse getLoginDTO (String username, String access) throws NotFoundException, InvalidInputDataException {
		final Employee employee = getEmployeeFromUsername(username);
		final String token = jwtTokenUtil.generateToken(username);
		
		if (!employee.getSystemAccess().getAccess().equals(access)) {
			throw new InvalidInputDataException("Employee do not have access for this platform");
		}
		
		return new LoginDTOResponse(token, employee.getRole());
	}
	
	public List<EmployeeDTO> getNoBusyDrivers() {
		return Arrays.asList(modelMapper.map(driverRepository.findAll(), EmployeeDTO[].class));
	}
	
	private boolean usernameIsUsed (String username) {
		return employeeRepository.findByUsername(username) != null;
	}
	
	
}
