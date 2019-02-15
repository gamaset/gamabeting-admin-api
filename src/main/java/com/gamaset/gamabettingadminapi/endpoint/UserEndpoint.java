package com.gamaset.gamabettingadminapi.endpoint;

import java.security.Principal;
import java.util.Base64;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.gamabettingadminapi.endpoint.schema.User;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserEndpoint {

	@PostMapping("/login")
	public boolean login(@RequestBody User user) {

		if (Objects.isNull(user)) {
			return false;
		}

		if (StringUtils.isBlank(user.getUsername())) {
			return false;
		}

		System.out.println(user);

		return user.getUsername().equals("user") && user.getPassword().equals("password");
	}

	@GetMapping("/authenticated")
	public Principal user(HttpServletRequest request) {
		
		System.out.println("header: " + request.getHeader("Authorization"));
		if(StringUtils.isBlank(request.getHeader("Authorization"))) {
			return () -> new String();
		}
		String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
		return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
	}

}