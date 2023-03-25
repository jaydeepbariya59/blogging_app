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

import com.jaydeep.blogapplication.dto.CategoryDTO;
import com.jaydeep.blogapplication.exception.CategoryException;
import com.jaydeep.blogapplication.service.CategoryServiceImpl;

@RestController
@RequestMapping("")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping("categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategories() throws CategoryException{
		
		List<CategoryDTO> categoryDTOs = categoryServiceImpl.getAllCategories();
		
		return new ResponseEntity<List<CategoryDTO>>(categoryDTOs, HttpStatus.OK);
		
	}
	
	@GetMapping("categories/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) throws CategoryException{
		
		CategoryDTO categoryDTO = categoryServiceImpl.getCategoryById(id);
		
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
		
	}
	
	@PostMapping("categories")
	public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws CategoryException{
		
		CategoryDTO categoryDTOSaved = categoryServiceImpl.addCategory(categoryDTO);
		
		return new ResponseEntity<CategoryDTO>(categoryDTOSaved, HttpStatus.OK);
		
	}
	
	
	@PutMapping("categories/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) throws CategoryException{
		
		CategoryDTO categoryDTOUpdated = categoryServiceImpl.updateCategory(id, categoryDTO);
		
		return new ResponseEntity<CategoryDTO>(categoryDTOUpdated, HttpStatus.OK);
		
	}
	
	@DeleteMapping("categories/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer id) throws CategoryException{
		
		String response = categoryServiceImpl.deleteCategoryById(id);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
		
	}
}
