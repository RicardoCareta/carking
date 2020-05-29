package com.pds.carking.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.carking.dto.VehicleDTO;
import com.pds.carking.model.Vehicle;
import com.pds.carking.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public String storeVehicle(VehicleDTO vehicleDTO) {
		return vehicleRepository.save(modelMapper.map(vehicleDTO, Vehicle.class)).getId().toString();
	}
}
