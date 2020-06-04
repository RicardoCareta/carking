package com.pds.carking.util.faker;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class FakerExtras extends Faker{
	
	private final Vehicle vehicle;
	

    public FakerExtras(Locale locale) {
        super(locale, (Random)null);
        this.vehicle = new Vehicle(this);
    }
    
    public Vehicle vehicle () {
    	return this.vehicle;
    }
}
