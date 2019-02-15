package com.gamaset.gamabettingadminapi.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.gamabettingadminapi.model.ManagerModel;
import com.gamaset.gamabettingadminapi.service.ManagerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/managers")
public class ManagerEndpoint {

	@Autowired
	private ManagerService service;
	

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public GenericResponse<ManagerModel> list() {
		return new GenericResponse<ManagerModel>(service.list());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public ManagerModel create(@RequestBody ManagerModel request) {
		return service.insert(request);
	}

}
