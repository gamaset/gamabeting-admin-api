package com.gamaset.gamabettingadminapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamaset.gamabettingadminapi.endpoint.schema.AgentRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.SignUpRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.UserPrinciple;
import com.gamaset.gamabettingadminapi.model.AgentModel;
import com.gamaset.gamabettingadminapi.model.ManagerModel;
import com.gamaset.gamabettingadminapi.model.UserModel;
import com.gamaset.gamabettingadminapi.repository.AgentRepository;
import com.gamaset.gamabettingadminapi.repository.ManagerRepository;
import com.gamaset.gamabettingadminapi.security.AuthService;

@Service
public class AgentService {

	private AuthService authService;
	private ManagerRepository managerRepository;
	private AgentRepository agentRepository;

	@Autowired
	public AgentService(AuthService authService, ManagerRepository managerRepository, AgentRepository agentRepository) {
		this.authService = authService;
		this.managerRepository = managerRepository;
		this.agentRepository = agentRepository;
	}

	public List<AgentModel> list() {
		return (List<AgentModel>) agentRepository.findAll();
	}

	@Transactional
	public AgentModel insert(AgentRequest request) {
		// TODO: add validation component
		UserPrinciple priciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		UserModel userCreated = authService.signUp(buildUser(request));

		ManagerModel manager = managerRepository.findByUserId(priciple.getId());

		return agentRepository.save(new AgentModel(userCreated, manager));
	}

	private SignUpRequest buildUser(AgentRequest request) {
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setEmail(request.getEmail());
		signUpRequest.setName(request.getName());
		signUpRequest.setPassword(request.getPassword());
		signUpRequest.setTaxId(request.getTaxId());
		signUpRequest.setUsername(request.getUsername());
		Set<String> roles = new HashSet<>();
		roles.add("AGENT");
		signUpRequest.setRole(roles);
		return signUpRequest;
	}

}
