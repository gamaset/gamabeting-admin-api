package com.gamaset.gamabettingadminapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.model.Agent;
import com.gamaset.gamabettingadminapi.repository.AgentRepository;

@Service
public class AgentService {

	@Autowired
	private AgentRepository agentRepository;
	
	public List<Agent> list(){
		return (List<Agent>) agentRepository.findAll();
	}

	public Agent insert(Agent request) {
		return agentRepository.save(request);
	}
	
}
