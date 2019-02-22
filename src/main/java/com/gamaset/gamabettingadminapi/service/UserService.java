package com.gamaset.gamabettingadminapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.model.UserModel;
import com.gamaset.gamabettingadminapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<UserModel> list() {
		return (List<UserModel>) repo.findAll();
	}

}
