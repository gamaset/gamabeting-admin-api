package com.gamaset.gamabettingadminapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.model.CompetitionModel;
import com.gamaset.gamabettingadminapi.repository.CompetitionRepository;

@Service
public class CompetitionService {

	@Autowired
	private CompetitionRepository repo;
	
	public void create(List<CompetitionModel> list) {
		repo.saveAll(list);
	}

	
}
