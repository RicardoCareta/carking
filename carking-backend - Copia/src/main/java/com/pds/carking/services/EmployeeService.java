package com.pds.carking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.pds.carking.dto.EmployeeDTO;
import com.pds.carking.exception.NotFoundException;
import com.pds.carking.model.Attendant;
import com.pds.carking.model.Driver;
import com.pds.carking.model.Employee;
import com.pds.carking.model.Manager;
import com.pds.carking.model.enums.Roles;
import com.pds.carking.repository.EmployeeRepository;
import com.pds.carking.util.GenerateCPF;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private Faker faker;

	@PostConstruct
	public void init() {

		Fixture.of(Driver.class).addTemplate("valid", new Rule() {
			{
				add("username", faker.name().username());
				add("password", faker.internet().password());
				add("name", faker.name().fullName().toUpperCase());
				add("cellNumber", faker.phoneNumber().cellPhone());
				add("cpf", GenerateCPF.randomCPF());
				add("isBusy", false);
			}
		});

		Fixture.of(Attendant.class).addTemplate("valid", new Rule() {
			{
				add("username", faker.name().username());
				add("password", faker.internet().password());
				add("name", faker.name().fullName().toUpperCase());
				add("cellNumber", faker.phoneNumber().cellPhone());
				add("cpf", GenerateCPF.randomCPF());
			}
		});

		Fixture.of(Manager.class).addTemplate("valid", new Rule() {
			{
				add("username", "admin");
				add("password", "123456");
				add("name", faker.name().fullName().toUpperCase());
				add("cellNumber", faker.phoneNumber().cellPhone());
				add("cpf", GenerateCPF.randomCPF());
			}
		});

		Driver driver = Fixture.from(Driver.class).gimme("valid");
		System.out.println("driver = " + employeeRepository.save(driver).getId().toString());

		Attendant attendant = Fixture.from(Attendant.class).gimme("valid");
		System.out.println("attendant = " + employeeRepository.save(attendant).getId().toString());

		Manager manager = Fixture.from(Manager.class).gimme("valid");
		System.out.println("manager = " + employeeRepository.save(manager).getId().toString());
	}

	public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String employeeId) throws NotFoundException {

		Employee employee = getEmployeeById(employeeId);
		employee.setName(employeeDTO.getName());
		employee.setPassword(employeeDTO.getPassword());
		employee.setUsername(employeeDTO.getUsername());

		employeeRepository.save(employee);
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	public String storeEmployee(EmployeeDTO employeeDTO) throws NotFoundException {
		Employee employee = null;
		//System.out.println(employeeDTO.getType() + " " + Roles.Values.ATTENDANT);

		switch (employeeDTO.getType()) {
		case Roles.Values.MANAGER:
			employee = modelMapper.map(employeeDTO, Manager.class);
			/*
			 * O exemplo acima equivale fazer:
			 * 
			 * employee = new Manager(); employee.setName(employeeDTO.getName())
			 * employee.setUsername(employeeDTO.getUsername())
			 * 
			 * e continua assim
			 */
			break;

		case Roles.Values.DRIVER:
			employee = modelMapper.map(employeeDTO, Driver.class);
			break;
		case Roles.Values.ATTENDANT:
			employee = modelMapper.map(employeeDTO, Attendant.class);
			break;
		default:
			throw new NotFoundException("Type is not found");
		}
		employee.setId(UUID.randomUUID());
		employeeRepository.save(employee);
		return employee.getId().toString();
	}

	public EmployeeDTO getEmployee(String employeeId) {
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

	public List<EmployeeDTO> getAllEmployee() {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		for (Employee employee : employees) {
			EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
			employeeDTO.setType(employee.getRole());
			employeeDTOs.add(employeeDTO);
		}
		return employeeDTOs;
	}
	
	public List<EmployeeDTO> getNoBusyDrivers() {
		List<Driver> employees = employeeRepository.findByIsBusy(false);
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		for (Employee employee : employees) {
			EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
			employeeDTO.setType(employee.getRole());
			employeeDTOs.add(employeeDTO);
		}
		return employeeDTOs;
	}

	private Employee getEmployeeById(String employeeId) throws NotFoundException {
		Optional<Employee> optEmployee = employeeRepository.findById(UUID.fromString(employeeId));
		if (optEmployee.isPresent()) {
			return optEmployee.get();
		}
		throw new NotFoundException("Employee not found");
	}
}
