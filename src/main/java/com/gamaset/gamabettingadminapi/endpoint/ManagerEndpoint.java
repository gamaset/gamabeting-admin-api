package com.gamaset.gamabettingadminapi.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.gamabettingadminapi.model.Manager;
import com.gamaset.gamabettingadminapi.service.ManagerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/managers")
public class ManagerEndpoint {

	@Autowired
	private ManagerService service;
	
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public GenericResponse<Manager> list() {
		return new GenericResponse<Manager>(service.list());
	}

	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public Manager create(@RequestBody Manager request) {
		return service.insert(request);
	}

}
