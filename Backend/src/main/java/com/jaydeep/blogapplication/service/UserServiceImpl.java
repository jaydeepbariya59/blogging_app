package com.jaydeep.blogapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jaydeep.blogapplication.dto.UserDTO;
import com.jaydeep.blogapplication.entity.User;
import com.jaydeep.blogapplication.exception.UserException;
import com.jaydeep.blogapplication.repository.RoleRepository;
import com.jaydeep.blogapplication.repository.UserRepository;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RoleRepository roleRepository;
		
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	public UserDTO addUser(UserDTO userDTO) {
		
		User userToSave = this.modelMapper.map(userDTO, User.class);
		userToSave.setPassword(userDTO.getPassword());
		
		User userSaved = userRepository.save(userToSave);
		
		UserDTO userSavedDTO = this.modelMapper.map(userSaved, UserDTO.class);
				
		
		
		return userSavedDTO;
	}

	@Override
	public UserDTO getUserById(Integer userId) throws UserException {
		Optional<User> opt = userRepository.findById(userId);
		User user = opt.orElseThrow(()-> new UserException("UserService.USER_NOT_EXISTS"));
		
		UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
		
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() throws UserException {
		
		List<User> users = userRepository.findAll();
		
		if(users.isEmpty()) {
			throw new UserException("UserService.NO_USER_EXISTS");
		}
		
		List<UserDTO> userDTOs = new ArrayList<>();
		
		users.forEach(user ->{
			
			UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
			userDTOs.add(userDTO);
		});
		
		
		return userDTOs;
	}

	@Override
	public String deleteUser(Integer userId) throws UserException {
		
		Optional<User> opt = userRepository.findById(userId);
		User user = opt.orElseThrow(()-> new UserException("UserService.USER_NOT_EXISTS"));
		
		userRepository.delete(user);
		
		return "User deleted successfully with id : "+ userId;
	}

	@Override
	public UserDTO updateUser(Integer userId, UserDTO userDTO) throws UserException {
		
		Optional<User> opt = userRepository.findById(userId);
		User user = opt.orElseThrow(()-> new UserException("UserService.USER_NOT_EXISTS"));
		
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		
		User userSaved = userRepository.save(user);
		
		UserDTO userDTOSaved = this.modelMapper.map(userSaved, UserDTO.class);
		
		return userDTOSaved;
	}


}
