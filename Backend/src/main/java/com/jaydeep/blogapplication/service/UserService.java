package com.jaydeep.blogapplication.service;

import java.util.List;

import com.jaydeep.blogapplication.dto.UserDTO;
import com.jaydeep.blogapplication.exception.UserException;



public interface UserService {
	
	UserDTO addUser(UserDTO userDTO);
	UserDTO updateUser(Integer userId, UserDTO userDTO) throws UserException;
	UserDTO getUserById(Integer userId) throws UserException;
	List<UserDTO> getAllUsers() throws UserException;
	String deleteUser(Integer userId) throws UserException;
}
