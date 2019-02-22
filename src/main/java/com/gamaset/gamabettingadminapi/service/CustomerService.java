package com.gamaset.gamabettingadminapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamaset.gamabettingadminapi.endpoint.schema.CustomerRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.SignUpRequest;
import com.gamaset.gamabettingadminapi.infra.exception.NotFoundException;
import com.gamaset.gamabettingadminapi.model.CustomerModel;
import com.gamaset.gamabettingadminapi.model.UserModel;
import com.gamaset.gamabettingadminapi.repository.AgentRepository;
import com.gamaset.gamabettingadminapi.repository.CustomerRepository;
import com.gamaset.gamabettingadminapi.security.AuthService;

@Service
public class CustomerService {

	private AuthService authService;
	private AgentRepository agentRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(AuthService authService, AgentRepository agentRepository,
			CustomerRepository customerRepository) {
		this.authService = authService;
		this.agentRepository = agentRepository;
		this.customerRepository = customerRepository;
	}

	public List<CustomerModel> list() {
		return (List<CustomerModel>) customerRepository.findAll();
	}

	@Transactional
	public CustomerModel insert(CustomerRequest request) {
		// TODO: add validation component

		UserModel userCreated = authService.signUp(buildUser(request));

		return customerRepository
				.save(new CustomerModel(userCreated, agentRepository.findById(request.getAgentId()).get()));
	}

	public CustomerModel getCustomer(Long customerId) {
		Optional<CustomerModel> customerOpt = customerRepository.findById(customerId);
		if (!customerOpt.isPresent()) {
			throw new NotFoundException("Cliente não encontrado com o ID " + customerId);
		}

		return customerOpt.get();
	}

	private SignUpRequest buildUser(CustomerRequest request) {
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setEmail(request.getEmail());
		signUpRequest.setName(request.getName());
		signUpRequest.setPassword(request.getPassword());
		signUpRequest.setTaxId(request.getTaxId());
		signUpRequest.setUsername(request.getUsername());
		return signUpRequest;
	}

}
