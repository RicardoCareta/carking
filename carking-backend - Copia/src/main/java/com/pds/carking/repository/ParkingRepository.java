package com.pds.carking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds.carking.model.Parking;

public interface ParkingRepository extends JpaRepository<Parking, UUID>{
	
}
