package com.pds.carking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.pds.carking.model.enums.Roles;

@Entity
@DiscriminatorValue(value = Roles.Values.ATTENDANT)
public class Attendant extends Employee{
	
}
