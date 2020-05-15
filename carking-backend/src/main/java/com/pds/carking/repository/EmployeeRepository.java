package com.pds.carking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds.carking.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{
	Employee findByUsername(String userName);
}
