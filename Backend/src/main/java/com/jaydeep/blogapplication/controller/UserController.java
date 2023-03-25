package com.jaydeep.blogapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaydeep.blogapplication.dto.UserDTO;
import com.jaydeep.blogapplication.exception.UserException;
import com.jaydeep.blogapplication.service.UserServiceImpl;

@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@GetMapping("users")
	public ResponseEntity<List<UserDTO>> getAllUsers() throws UserException{
		
		List<UserDTO> userDTOs = userServiceImpl.getAllUsers();
		
		return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
		
	}
	
	
	@GetMapping("users/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) throws UserException{
		
		UserDTO userDTO = userServiceImpl.getUserById(id);
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		
	}
	
	
	@PostMapping("users")
	public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) throws UserException{
		
		UserDTO userDTO1 = userServiceImpl.addUser(userDTO);
		
		return new ResponseEntity<UserDTO>(userDTO1, HttpStatus.OK);
		
	}
	
	@PutMapping("users/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) throws UserException{
		
		UserDTO userDTO1 = userServiceImpl.updateUser(id,userDTO);
		
		return new ResponseEntity<UserDTO>(userDTO1, HttpStatus.OK);
		
	}
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Integer id) throws UserException{
		
		String res = userServiceImpl.deleteUser(id);
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
		
	}
	
}
