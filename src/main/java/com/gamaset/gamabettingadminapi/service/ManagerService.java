package com.gamaset.gamabettingadminapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamaset.gamabettingadminapi.endpoint.schema.ManagerRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.SignUpRequest;
import com.gamaset.gamabettingadminapi.model.ManagerModel;
import com.gamaset.gamabettingadminapi.model.UserModel;
import com.gamaset.gamabettingadminapi.repository.ManagerRepository;
import com.gamaset.gamabettingadminapi.security.AuthService;

@Service
public class ManagerService {

	private ManagerRepository managerRepository;
	private AuthService authService;
	
	@Autowired
	public ManagerService(ManagerRepository managerRepository, AuthService authService) {
		this.managerRepository = managerRepository;
		this.authService = authService;
	}

	public List<ManagerModel> list(){
		return (List<ManagerModel>) managerRepository.findAll();
	}
	
	@Transactional
	public ManagerModel insert(ManagerRequest request) {
		//TODO: add validation component

		UserModel userCreated = authService.signUp(buildUser(request));
		
		return managerRepository.save(new ManagerModel(userCreated));
	}
	
	private SignUpRequest buildUser(ManagerRequest request) {
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setEmail(request.getEmail());
		signUpRequest.setName(request.getName());
		signUpRequest.setPassword(request.getPassword());
		signUpRequest.setTaxId(request.getTaxId());
		signUpRequest.setUsername(request.getUsername());
		Set<String> roles = new HashSet<>();
		roles.add("MANAGER");
		signUpRequest.setRole(roles);
		return signUpRequest;
	}
	
}
