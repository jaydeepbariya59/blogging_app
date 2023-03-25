package com.jaydeep.blogapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaydeep.blogapplication.dto.UserDTO;
import com.jaydeep.blogapplication.security.CustomUserDetailsService;
import com.jaydeep.blogapplication.security.JwtAuthRequest;
import com.jaydeep.blogapplication.service.AuthServiceImpl;
import com.jaydeep.blogapplication.util.JwtAuthResponse;
import com.jaydeep.blogapplication.util.JwtTokenHelper;

@RestController
@RequestMapping("auth")
@Validated
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{
		
		this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		
		String token = jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(token);
		jwtAuthResponse.setUserDetails(userDetails);
		
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);

				
	}
	
	
	@PostMapping("registerUser")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO){
		
		String result = authServiceImpl.registerUser(userDTO);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
		
	}

	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		}
		catch(DisabledException e) {
			throw new Exception("Disabled User");
		}
		catch(BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}		
	}
	
	
}
