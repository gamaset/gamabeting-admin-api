package com.gamaset.gamabettingadminapi.security;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.endpoint.schema.SignInResponse;
import com.gamaset.gamabettingadminapi.endpoint.schema.SignUpRequest;
import com.gamaset.gamabettingadminapi.infra.exception.NotFoundException;
import com.gamaset.gamabettingadminapi.infra.exception.UserAlreadyTakenException;
import com.gamaset.gamabettingadminapi.model.Role;
import com.gamaset.gamabettingadminapi.model.RoleName;
import com.gamaset.gamabettingadminapi.model.UserModel;
import com.gamaset.gamabettingadminapi.repository.RoleRepository;
import com.gamaset.gamabettingadminapi.repository.UserRepository;
import com.gamaset.gamabettingadminapi.security.jwt.JwtProvider;

@Service
public class AuthService {

	AuthenticationManager authenticationManager;
	UserRepository userRepository;
	RoleRepository roleRepository;
	PasswordEncoder encoder;
	JwtProvider jwtProvider;

	@Autowired
	public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder encoder, JwtProvider jwtProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtProvider = jwtProvider;
	}

	public SignInResponse authenticateUser(LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return new SignInResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
	}

	public UserModel signUp(SignUpRequest signUpRequest) {
		verifyIfUsernameAlreadyTaken(signUpRequest.getUsername());
		verifyIfEmailAlreadyUse(signUpRequest.getEmail());

		UserModel user = new UserModel(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getTaxId());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = verifyRoles(strRoles);

		user.setRoles(roles);
		return userRepository.save(user);

	}

	private Set<Role> verifyRoles(Set<String> strRoles) {
		Set<Role> roles = new HashSet<>();
		if (Objects.isNull(strRoles)) {
			Role userRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
					.orElseThrow(() -> new NotFoundException("Fail! -> Cause: User Role not find."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ADMIN":
					Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
							.orElseThrow(() -> new NotFoundException("Fail! -> Cause: User Role not find."));
					roles.add(adminRole);

					break;
				case "AGENT":
					Role agentRole = roleRepository.findByName(RoleName.ROLE_AGENT)
							.orElseThrow(() -> new NotFoundException("Fail! -> Cause: User Role not find."));
					roles.add(agentRole);

					break;
				case "MANAGER":
					Role managerRole = roleRepository.findByName(RoleName.ROLE_MANAGER)
							.orElseThrow(() -> new NotFoundException("Fail! -> Cause: User Role not find."));
					roles.add(managerRole);

					break;
				default:
					Role userRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
							.orElseThrow(() -> new NotFoundException("Fail! -> Cause: User Role not find."));
					roles.add(userRole);
				}
			});
		}

		return roles;
	}

	private void verifyIfUsernameAlreadyTaken(String username) {
		if (userRepository.existsByUsername(username)) {
			throw new UserAlreadyTakenException("Fail -> Username is already taken!");
		}
	}

	private void verifyIfEmailAlreadyUse(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new UserAlreadyTakenException("Fail -> Email is already in use!");
		}
	}
}
