package com.pds.carking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds.carking.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{

	Customer findByCPF (String cpf);
}
