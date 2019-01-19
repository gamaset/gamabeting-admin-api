package com.gamaset.gamabettingadminapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.model.CustomerModel;
import com.gamaset.gamabettingadminapi.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository managerRepository;
	
	public List<CustomerModel> list(){
		return (List<CustomerModel>) managerRepository.findAll();
	}
	
	public CustomerModel insert(CustomerModel request) {
		return managerRepository.save(request);
	}
	
	
}
