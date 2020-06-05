package com.pds.carking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.pds.carking.model.enums.Roles;
import com.pds.carking.model.enums.SystemAccesses;

@Entity
@DiscriminatorValue(value = Roles.Values.DRIVER)
public class Driver extends Employee{
	
	private boolean isBusy;
	
	public boolean getBusy () {
		return isBusy;
	}
	public void setBusy (boolean isBusy) {
		this.isBusy = isBusy;
	}
	
	@Override
	public SystemAccesses getSystemAccess() {
		return SystemAccesses.MOBILE;
	}
}
