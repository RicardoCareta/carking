package com.pds.carking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds.carking.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, UUID>{
	Driver findByUsername(String userName);
}
