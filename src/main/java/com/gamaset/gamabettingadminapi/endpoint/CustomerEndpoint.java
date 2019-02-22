package com.gamaset.gamabettingadminapi.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.gamabettingadminapi.endpoint.schema.CustomerRequest;
import com.gamaset.gamabettingadminapi.model.CustomerModel;
import com.gamaset.gamabettingadminapi.service.CustomerService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerEndpoint {

	@Autowired
	private CustomerService service;
	
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public GenericResponse<CustomerModel> list() {
		return new GenericResponse<CustomerModel>(service.list());
	}
	
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public CustomerModel create(@RequestBody CustomerRequest request) {
		return service.insert(request);
	}

}
