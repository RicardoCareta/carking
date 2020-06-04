package com.pds.carking.services;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.carking.model.Attendant;
import com.pds.carking.model.Customer;
import com.pds.carking.model.Driver;
import com.pds.carking.model.Manager;
import com.pds.carking.model.Parking;
import com.pds.carking.model.ParkingPlace;
import com.pds.carking.model.Vehicle;
import com.pds.carking.model.enums.ParkingStatus;
import com.pds.carking.repository.CustomerRepository;
import com.pds.carking.repository.EmployeeRepository;
import com.pds.carking.repository.ParkingRepository;
import com.pds.carking.repository.VehicleRepository;
import com.pds.carking.util.GenerateCPF;
import com.pds.carking.util.faker.FakerExtras;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

@Service
public class InitValues {

	@Autowired
	private FakerExtras faker;

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ParkingRepository parkingRepository;

	@PostConstruct
	public void init() {

		Fixture.of(Driver.class).addTemplate("valid", new Rule() {
			{
				add("username", "driver");
				add("password", "123");
				add("name", faker.name().fullName().toUpperCase());
				add("cellNumber", faker.phoneNumber().cellPhone());
				add("cpf", GenerateCPF.randomCPF());
				add("isBusy", false);
			}
		});

		Fixture.of(Manager.class).addTemplate("valid", new Rule() {
			{
				add("username", "admin");
				add("password", "123456");
				add("name", faker.name().fullName().toUpperCase());
				add("cellNumber", faker.phoneNumber().cellPhone());
				add("cpf", GenerateCPF.randomCPF());
			}
		});

		Fixture.of(Attendant.class).addTemplate("valid", new Rule() {
			{
				add("username", "atendente");
				add("password", "123456");
				add("name", faker.name().fullName().toUpperCase());
				add("cellNumber", faker.phoneNumber().cellPhone());
				add("cpf", GenerateCPF.randomCPF());
			}
		});

		Driver driver = Fixture.from(Driver.class).gimme("valid");
		employeeRepository.save(driver);

		Manager manager = Fixture.from(Manager.class).gimme("valid");
		employeeRepository.save(manager);

		Attendant attendant = Fixture.from(Attendant.class).gimme("valid");
		employeeRepository.save(attendant);

		String make = faker.vehicle().make();
		Fixture.of(Vehicle.class).addTemplate("valid", new Rule() {
			{
				add("brand", make);
				add("model", faker.vehicle().model(make));
				add("plate", faker.vehicle().randomBrazilianPlate());
				add("color", faker.color().name());
			}
		});

		Vehicle vehicle = Fixture.from(Vehicle.class).gimme("valid");
		vehicleRepository.save(vehicle);

		Fixture.of(Customer.class).addTemplate("valid", new Rule() {
			{
				add("name", faker.name().fullName());
				add("cellNumber", faker.phoneNumber().cellPhone().toString());
				add("CPF", GenerateCPF.randomCPF());
				add("vehicle", vehicle);
			}
		});

		Customer customer = Fixture.from(Customer.class).gimme("valid");
		customerRepository.save(customer);

		ParkingPlace parkingPlace = new ParkingPlace(
				(faker.number().numberBetween(1, ParkingService.PARK_SIZE_R) + "" + (char) faker.number().numberBetween(65, 65 + ParkingService.PARK_SIZE_C)));


		Fixture.of(Parking.class).addTemplate("valid", new Rule() {
			{
				add("vehicle", vehicle);
				add("parkedDate", faker.date().past(60, TimeUnit.MINUTES));
				add("removeDate", null);
				add("customer", customer);
				add("parkingPlace", parkingPlace);
				add("attendent", attendant);
				add("driver", driver);
			}
		});
		
		Parking parking = Fixture.from(Parking.class).gimme("valid");
		parking.setStatus(ParkingStatus.PARKED);
		parkingRepository.save(parking);
	}

}
