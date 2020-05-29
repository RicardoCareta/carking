package com.pds.carking.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds.carking.model.Driver;
import com.pds.carking.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{
	Employee findByUsername(String userName);
	List<Driver> findByIsBusy(boolean isBusy);
}
