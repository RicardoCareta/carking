package com.pds.carking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pds.carking.dto.DriverConfirmParkingDTO;
import com.pds.carking.services.DriverService;
import com.pds.carking.services.EmployeeService;

@RequestMapping("/driver")
@Controller
public class DriverController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DriverService driverService;

	@GetMapping("/no-busy")
	public ResponseEntity<?> getNoBusyDrivers() { 
		return ResponseEntity.ok(employeeService.getNoBusyDrivers());
	} 

	@GetMapping("/{driverId}/entrance")
	public ResponseEntity<?> getDriversEntrance(@PathVariable String driverId) throws Exception{
		return ResponseEntity.ok(driverService.getDriversEntrance(driverId));
	}

	@GetMapping("/{driverId}/takeoff")
	public ResponseEntity<?> getDriversTakeof(@PathVariable String driverId) throws Exception{
		return ResponseEntity.ok(driverService.getDriversTakeoff(driverId));
	}
	
	@GetMapping("/entrance")
	public ResponseEntity<?> getDriversEntranceAuthentication(@RequestHeader ("Authorization") String token) throws Exception{
		return ResponseEntity.ok(driverService.getDriversEntranceAuthentication(token));
	}

	@GetMapping("/takeoff")
	public ResponseEntity<?> getDriversTakeofAuthentication(@RequestHeader ("Authorization") String token) throws Exception{
		return ResponseEntity.ok(driverService.getDriversTakeoffAuthentication(token));
	}

	@PatchMapping("/{driverId}/confirm")
	public ResponseEntity<?> driverConfirmParkingVehicle(@PathVariable String driverId,
			@Valid @RequestBody DriverConfirmParkingDTO confirmParkingDTO) throws Exception {
		
		return ResponseEntity.ok(driverService.driverConfirmParkingVehicle(driverId, confirmParkingDTO.getParkingId(),
				confirmParkingDTO.getConfirmType().equals("entrance")));
	}
	
	@PatchMapping("/confirm")
	public ResponseEntity<?> driverConfirmParkingVehicleAuthentication (@RequestHeader ("Authorization") String token, @Valid @RequestBody DriverConfirmParkingDTO confirmParkingDTO) throws Exception {
		return ResponseEntity.ok(driverService.driverConfirmParkingVehicleAuthentication(token, confirmParkingDTO.getParkingId(), confirmParkingDTO.getConfirmType().equals("entrance")));
	}
}
