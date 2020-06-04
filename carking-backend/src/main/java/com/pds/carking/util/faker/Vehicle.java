package com.pds.carking.util.faker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class Vehicle {
	
	private final Faker faker;
	private final FakeValuesService fakeValuesService;
	
    static final String VIN_REGEX = "[A-Z0-9]{3}[A-Z0-9]{5}[A-Z0-9]{1}[A-Z0-9]{1}[A-Z0-0]{1}[A-Z0-9]{1}\\d{5}";
    
    public Vehicle(Faker faker) {
		this.faker = faker;
        this.fakeValuesService = new FakeValuesService(new Locale("pt-BR"), new RandomService());
	}
	
    
    public String vin () {
        return this.fakeValuesService.regexify(VIN_REGEX);
    }

    public String manufacture () {
        return this.fakeValuesService.fetchString("vehicle.manufacture");
    }

    public String make () {
        return this.fakeValuesService.fetchString("vehicle.makes");
    }

    public String model(String make) {
        ArrayList<String> arr = (ArrayList<String>) this.fakeValuesService.fetchObject("vehicle.makes");
        if (arr.contains(make))
            return this.fakeValuesService.fetchString(String.format("vehicle.models_by_make.%s", make));
        else
            return "Only Supports models of the following make: " + arr;
    }

    public String makeAndModel() {
        String make = make();
        return make + " " + model(make);
    }

    public String style () {
        return this.fakeValuesService.fetchString("vehicle.styles");
    }

    public String color () {
        return this.fakeValuesService.fetchString("vehicle.colors");
    }

    public String transmission () {
        return this.fakeValuesService.fetchString("vehicle.transmissions");
    }

    public String driveType() {
        return this.fakeValuesService.fetchString("vehicle.drive_types");
    }

    public String fuelType() {
        return this.fakeValuesService.fetchString("vehicle.fuel_types");
    }

    public String carType() {
        return this.fakeValuesService.fetchString("vehicle.car_types");
    }

    public String engine() {
        return this.fakeValuesService.fetch("vehicle.engine_sizes") + " " + this.fakeValuesService.fetchObject("vehicle.cylinder_engine");
    }

    public ArrayList carOption() {
        int optionSize =faker.number().numberBetween(5, 10);
        ArrayList<String> arr = new ArrayList<String>(optionSize);
        while (optionSize > 0) {
            arr.add(this.fakeValuesService.fetchString("vehicle.car_options"));
            optionSize -= 1;
        }
        return arr;
    }

    public ArrayList standardSpecs() {
        int standardSpecsSize =faker.number().numberBetween(5, 10);
        ArrayList<String> arr = new ArrayList<String>(standardSpecsSize);
        while (standardSpecsSize > 0) {
            arr.add(this.fakeValuesService.fetchString("vehicle.standard_specs"));
            standardSpecsSize -= 1;
        }
        return arr;
    }

    public Object door () {
        return this.fakeValuesService.fetch("vehicle.doors");
    }

    public int year() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return faker.number().numberBetween(currentYear - 10, currentYear);
    }

    public int mileage(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public String licensePlate(String state_abbreviation) {
        String key;
        if (state_abbreviation != null && !state_abbreviation.isEmpty()) {
            key = "vehicle.license_plate_by_state." + state_abbreviation;
            return faker.regexify(faker.bothify(this.fakeValuesService.fetchObject(key).toString()));
        } else
            key = "vehicle.license_plate";
            return faker.regexify(faker.bothify(this.fakeValuesService.fetchObject(key).toString()));
    }
    
    public String randomBrazilianPlate () {
    	return faker.lorem().characters(3, false, false).toUpperCase() + "-" + faker.number().digits(4);
    }
}
