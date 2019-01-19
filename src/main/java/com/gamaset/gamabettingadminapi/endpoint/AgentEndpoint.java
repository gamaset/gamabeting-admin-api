package com.gamaset.gamabettingadminapi.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.gamabettingadminapi.model.Agent;
import com.gamaset.gamabettingadminapi.service.AgentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/agents")
public class AgentEndpoint {

	@Autowired
	private AgentService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GenericResponse<Agent> list() {
		return new GenericResponse<Agent>(service.list());
	}

	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public Agent create(@RequestBody Agent request) {
		return service.insert(request);
	}
}
