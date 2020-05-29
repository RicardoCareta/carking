package com.pds.carking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds.carking.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID>{

}
