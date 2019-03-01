package com.gamaset.gamabettingadminapi.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.gamabettingadminapi.endpoint.schema.BetPatchRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.BetRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.BetResponse;
import com.gamaset.gamabettingadminapi.service.BetService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/bets")
public class BetEndpoint {

	@Autowired
	private BetService service;
	
	@PreAuthorize("hasRole('AGENT') or hasRole('CUSTOMER') or hasRole('ADMIN')")
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GenericResponse<BetResponse> list() {
		return new GenericResponse<BetResponse>(service.list());
	}

	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public BetResponse create(@RequestBody BetRequest request) {
		return service.createBet(request);
	}

	@PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
	@PatchMapping(value = "/{betId}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public void updateStatus(@PathVariable("betId") Long betId, @RequestBody BetPatchRequest request) {
		service.updateStatusBet(betId, request);
	}

	@PreAuthorize("hasRole('AGENT') or hasRole('CUSTOMER') or hasRole('ADMIN')")
	@GetMapping(value = "/{betId}", produces = APPLICATION_JSON_UTF8_VALUE)
	public BetResponse getBet(@PathVariable("betId") Long betId) {
		return service.getBet(betId);
	}
}
