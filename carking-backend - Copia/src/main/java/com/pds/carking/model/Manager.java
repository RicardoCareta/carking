package com.pds.carking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.pds.carking.model.enums.Roles;

@Entity
@DiscriminatorValue(value = Roles.Values.MANAGER)
public class Manager extends Employee{}
