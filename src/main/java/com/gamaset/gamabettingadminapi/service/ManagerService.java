package com.gamaset.gamabettingadminapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.model.ManagerModel;
import com.gamaset.gamabettingadminapi.repository.ManagerRepository;
import com.gamaset.gamabettingadminapi.security.SHA512Hasher;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private SHA512Hasher passHasher;
	
	public List<ManagerModel> list(){
		return (List<ManagerModel>) managerRepository.findAll();
	}
	
	public ManagerModel insert(ManagerModel request) {
		//TODO: add validation component
		
//		request.setPassword(passHasher.hash(request.getgetPassword()));
		
		return managerRepository.save(request);
	}
	
}
