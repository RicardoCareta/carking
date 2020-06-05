package com.pds.carking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.pds.carking.model.enums.Roles;
import com.pds.carking.model.enums.SystemAccesses;

@Entity
@DiscriminatorValue(value = Roles.Values.MANAGER)
public class Manager extends Employee{
	
	@Override
	public SystemAccesses getSystemAccess() {
		return SystemAccesses.WEB;
	}
}
