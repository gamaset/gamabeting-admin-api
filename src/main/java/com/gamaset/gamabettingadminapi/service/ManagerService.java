package com.gamaset.gamabettingadminapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.model.Manager;
import com.gamaset.gamabettingadminapi.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	
	public List<Manager> list(){
		return (List<Manager>) managerRepository.findAll();
	}
	
	public Manager insert(Manager request) {
		return managerRepository.save(request);
	}
	
}
