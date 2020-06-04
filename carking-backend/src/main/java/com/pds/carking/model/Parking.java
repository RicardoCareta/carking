package com.pds.carking.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.pds.carking.model.enums.ParkingStatus;

@Entity
public class Parking {

	@Id
	private UUID id;
	
	@OneToOne()
	private Vehicle vehicle;
	private Date parkedDate;
	private Date removeDate;
	
	@ManyToOne
	private Customer customer;
	
	@AttributeOverrides({
		@AttributeOverride(name = "name", column=@Column(name="parking_place_name"))
	})
	@Embedded
	private ParkingPlace parkingPlace;
	
	@ManyToOne
	private Employee attendent;
	
	@ManyToOne
	private Employee driver;
	
	@ManyToOne
	private Employee driverTakeoff;
	
	@Enumerated(EnumType.STRING)
	private ParkingStatus status;
	
	
	public Parking() {
		id = UUID.randomUUID();
		parkedDate = new Date();
		status = ParkingStatus.PARK_ENTER;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getParkedDate() {
		return parkedDate;
	}

	public void setParkedDate(Date parkedDate) {
		this.parkedDate = parkedDate;
	}

	public Date getRemoveDate() {
		return removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

	public ParkingPlace getParkingPlace() {
		return parkingPlace;
	}

	public void setParkingPlace(ParkingPlace parkingPlace) {
		this.parkingPlace = parkingPlace;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Employee getAttendent() {
		return attendent;
	}

	public void setAttendent(Employee attendent) {
		this.attendent = attendent;
	}

	public Employee getDriver() {
		return driver;
	}

	public void setDriver(Employee driver) {
		this.driver = driver;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getDriverTakeoff() {
		return driverTakeoff;
	}

	public void setDriverTakeoff(Employee driverTakeoff) {
		this.driverTakeoff = driverTakeoff;
	}

	public ParkingStatus getStatus() {
		return status;
	}

	public void setStatus(ParkingStatus status) {
		this.status = status;
	}	
}
