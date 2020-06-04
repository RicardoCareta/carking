package com.pds.carking.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds.carking.model.Parking;
import com.pds.carking.model.ParkingPlace;
import com.pds.carking.model.enums.ParkingStatus;

public interface ParkingRepository extends JpaRepository<Parking, UUID>{
	
	Parking findByParkingPlace (ParkingPlace parkingPlace);
	
	List<Parking> findByStatus (ParkingStatus status);
}
