package com.jaydeep.blogapplication.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jaydeep.blogapplication.dto.UserDTO;
import com.jaydeep.blogapplication.entity.Role;
import com.jaydeep.blogapplication.entity.User;
import com.jaydeep.blogapplication.repository.RoleRepository;
import com.jaydeep.blogapplication.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String registerUser(UserDTO userDTO) {
		
		User user = new User();
		
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setAbout(userDTO.getAbout());
		
		Role role = roleRepository.findById(2).get(); //NORMAL_USER
		user.setRoles(Set.of(role));
		
		userRepository.save(user);
		
		return "USER_REGISTRATION_SUCCESSFUL";
	}
}
