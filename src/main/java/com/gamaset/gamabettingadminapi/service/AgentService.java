package com.gamaset.gamabettingadminapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.model.AgentModel;
import com.gamaset.gamabettingadminapi.repository.AgentRepository;
import com.gamaset.gamabettingadminapi.security.SHA512Hasher;

@Service
public class AgentService {

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private SHA512Hasher passHasher;

	public List<AgentModel> list() {
		return (List<AgentModel>) agentRepository.findAll();
	}

	public AgentModel insert(AgentModel request) {
		// TODO: add validation component

//		request.setPassword(passHasher.hash(request.getPassword()));

		return agentRepository.save(request);
	}

}
