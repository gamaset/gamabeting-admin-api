package com.gamaset.gamabettingadminapi.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.gamabettingadminapi.endpoint.schema.SignInResponse;
import com.gamaset.gamabettingadminapi.endpoint.schema.SignUpRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthEndpoint {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/signin")
	public SignInResponse authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		SignInResponse response = authService.authenticateUser(loginRequest);
		return response;
	}

	@PostMapping("/signup")
	public String registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		authService.signUp(signUpRequest);
		return "User registered successfully!";
	}
}