package com.gamaset.gamabettingadminapi.endpoint;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerEndpoint {

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void list() {

		
	}

}
