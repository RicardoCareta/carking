package com.pds.carking.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pds.carking.dto.ParkingDTO;
import com.pds.carking.services.ParkingService;

@Controller
@RequestMapping("/parking")
public class ParkingController {
	
	@Autowired
	private ParkingService parkingService;
	
	@PostMapping
	public ResponseEntity<?> storeParking (@Valid @RequestBody ParkingDTO parkingDTO) throws Exception {
		Map<String, String> controllerStore = new HashMap<String, String>();
		final String parkingId = parkingService.storeParking(parkingDTO);
		controllerStore.put("id", parkingId);
		return ResponseEntity.status(HttpStatus.CREATED).body(controllerStore);
	}
	
	@GetMapping
	public ResponseEntity<?> getParking () {
		return ResponseEntity.ok(parkingService.getParking());
	}
	
	@GetMapping("/map")
	public ResponseEntity<?> getParkingMap() {
		return ResponseEntity.ok(parkingService.getParkingMapped());
	}
	
	@GetMapping("/filter")
	public ResponseEntity<?> getParkingByFilter (@RequestParam(name = "parkingPlace") String parkingPlace) throws Exception{
		return ResponseEntity.ok(parkingService.getParkingByFilter(parkingPlace));
	}
	
	@PatchMapping("/{parkingId}")
	public ResponseEntity<?> takeOffVehicleParking (@PathVariable String parkingId) throws Exception{
		return ResponseEntity.ok(parkingService.takeOffVehicleParking(parkingId));
	}
}
