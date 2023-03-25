package com.jaydeep.blogapplication.util;

import org.springframework.security.core.userdetails.UserDetails;


public class JwtAuthResponse {

	private String token;
	private UserDetails userDetails;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	


	
	
	
	
	
}
