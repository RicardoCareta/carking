package com.pds.carking.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.carking.dto.EmployeeDTO;
import com.pds.carking.model.Employee;
import com.pds.carking.repository.EmployeeRepository;

import javassist.NotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ModelMapper modelMapper;

	@PostConstruct
	public void init() {
		Employee employee = new Employee();
		employee.setName("Teste");
		employee.setUsername("admin");
		employee.setPassword("123456");
		employee = employeeRepository.save(employee);
		System.out.println(employee.getId());
	}
	
	public EmployeeDTO updateEmployee (EmployeeDTO employeeDTO, String employeeId) throws NotFoundException {
		
		Employee employee = getEmployeeById(employeeId);
		employee.setName(employeeDTO.getName());
		employee.setPassword(employeeDTO.getPassword());
		employee.setUsername(employeeDTO.getUsername());
		
		employeeRepository.save(employee);
		return modelMapper.map(employee, EmployeeDTO.class);
	}
	
	public String storeEmployee (EmployeeDTO employeeDTO) {
		Employee employee = employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
		return employee.getId().toString();
	}
	
	public EmployeeDTO getEmployee (String employeeId) {
		Optional<Employee> optEmployee = employeeRepository.findById(UUID.fromString(employeeId));
		if (optEmployee.isPresent()) {
			return modelMapper.map(optEmployee.get(), EmployeeDTO.class);
		}
		return null;
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
	
	public List<EmployeeDTO> getAllEmployee () {
		return Arrays.asList(modelMapper.map(employeeRepository.findAll(), EmployeeDTO[].class));
	}
	private Employee getEmployeeById(String employeeId) throws NotFoundException {
		Optional<Employee> optEmployee = employeeRepository.findById(UUID.fromString(employeeId));
		if (optEmployee.isPresent()) {
			return optEmployee.get();
		}
		throw new NotFoundException("Employee not found");
	}
}
