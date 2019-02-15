package com.gamaset.gamabettingadminapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.model.CountryModel;
import com.gamaset.gamabettingadminapi.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository repo;
	
	public void create(List<CountryModel> list) {
		repo.saveAll(list);
	}

}
