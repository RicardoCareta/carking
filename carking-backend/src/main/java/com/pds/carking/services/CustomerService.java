package com.pds.carking.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.carking.dto.CustomerDTO;
import com.pds.carking.model.Customer;
import com.pds.carking.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public String storeCustomer (CustomerDTO customerDTO) {
		Customer client = customerRepository.findByCPF(customerDTO.getCpf());
		if (client != null) {
			return client.getId().toString();
		}
		return customerRepository.save(modelMapper.map(customerDTO, Customer.class)).getId().toString();
	}
}
