package com.sprinboot.jwt.app.controller;

import com.sprinboot.jwt.app.config.JwtTokenUtil;
import com.sprinboot.jwt.app.util.GenericResponse;
import com.sprinboot.jwt.app.util.JwtRequest;
import com.sprinboot.jwt.app.util.JwtResponse;
import com.sprinboot.jwt.app.dto.UserDTO;
import com.sprinboot.jwt.app.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public GenericResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		GenericResponse authenticationResponse = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		if(authenticationResponse.getCode() != 200)
			return authenticationResponse;
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		authenticationResponse.setData(new JwtResponse(token));
		return authenticationResponse;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private GenericResponse authenticate(String username, String password) throws Exception {
		GenericResponse response = null;
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			response = new GenericResponse(200, "AUTHENTICATED");
		} catch (DisabledException e) {
			response = new GenericResponse(401, "USER_DISABLED");
			System.err.println("Exception --> "+e.getMessage());
		} catch (BadCredentialsException e) {
			response = new GenericResponse(401, "INVALID_CREDENTIALS");
			System.err.println("Exception --> "+e.getMessage());
		}
		return response;
	}
}
