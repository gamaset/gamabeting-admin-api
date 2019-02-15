package com.gamaset.gamabettingadminapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.infra.exception.NotFoundException;
import com.gamaset.gamabettingadminapi.model.CustomerModel;
import com.gamaset.gamabettingadminapi.repository.CustomerRepository;
import com.gamaset.gamabettingadminapi.security.SHA512Hasher;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SHA512Hasher passHasher;
	
	public List<CustomerModel> list(){
		return (List<CustomerModel>) customerRepository.findAll();
	}
	
	public CustomerModel insert(CustomerModel request) {
		//TODO: add validation component
		
//		request.setPassword(passHasher.hash(request.getPassword()));
		
		return customerRepository.save(request);
	}
	
	public CustomerModel getCustomer(Long customerId) {
		Optional<CustomerModel> customerOpt = customerRepository.findById(customerId);
		if(!customerOpt.isPresent()) {
			throw new NotFoundException("Cliente não encontrado com o ID " + customerId);
		}
		
		return customerOpt.get();
	}
	
}
