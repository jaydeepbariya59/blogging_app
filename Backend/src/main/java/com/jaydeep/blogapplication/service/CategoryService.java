package com.jaydeep.blogapplication.service;

import java.util.List;

import com.jaydeep.blogapplication.dto.CategoryDTO;
import com.jaydeep.blogapplication.exception.CategoryException;


public interface CategoryService {
	
	public List<CategoryDTO> getAllCategories() throws CategoryException;
	public CategoryDTO getCategoryById(Integer CategoryId) throws CategoryException;
	public CategoryDTO addCategory(CategoryDTO categoryDTO) throws CategoryException;
	public CategoryDTO updateCategory(Integer CategoryId,CategoryDTO categoryDTO) throws CategoryException;
	public String deleteCategoryById(Integer CategoryId) throws CategoryException;
}
