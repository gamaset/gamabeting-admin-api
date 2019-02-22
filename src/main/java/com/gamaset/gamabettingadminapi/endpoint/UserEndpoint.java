package com.gamaset.gamabettingadminapi.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.gamabettingadminapi.model.UserModel;
import com.gamaset.gamabettingadminapi.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserEndpoint {
	
	@Autowired
	private UserService service;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public GenericResponse<UserModel> listUsers(){
		return new GenericResponse<UserModel>(service.list());
	}

}