package com.jaydeep.blogapplication.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaydeep.blogapplication.entity.User;
import com.jaydeep.blogapplication.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> opt = userRepository.findByEmail(username);
		User user = opt.get();

		if(user==null) {
			throw new UsernameNotFoundException("USER_NOT_FOUND");
		}
		
		return user;
	}

}
