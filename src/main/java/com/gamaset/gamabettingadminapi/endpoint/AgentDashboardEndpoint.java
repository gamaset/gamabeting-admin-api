package com.gamaset.gamabettingadminapi.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.gamabettingadminapi.endpoint.schema.AgentDashboard;
import com.gamaset.gamabettingadminapi.endpoint.schema.ManagerRequest;
import com.gamaset.gamabettingadminapi.service.AgentDashboardService;
import com.gamaset.gamabettingadminapi.service.ManagerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/agent-dashboard")
public class AgentDashboardEndpoint {

	@Autowired
	private AgentDashboardService service;
	

	@PreAuthorize("hasRole('AGENT')")
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public AgentDashboard buildDash() {
		return service.buildDashboard();
	}

}
