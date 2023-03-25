package com.jaydeep.blogapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaydeep.blogapplication.dto.CategoryDTO;
import com.jaydeep.blogapplication.entity.Category;
import com.jaydeep.blogapplication.exception.CategoryException;
import com.jaydeep.blogapplication.repository.CategoryRepository;



@Service("cateogryServiceImpl")
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CategoryDTO> getAllCategories() throws CategoryException {
		
		List<Category> list = categoryRepository.findAll();
		
		if(list.isEmpty()) {
			throw new CategoryException("CategoryService.NO_CATEGORY_FOUND");
		}
		
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		
		list.forEach(
				category ->
				{
					CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
					categoryDTOs.add(categoryDTO);
				}
				);
		
		return categoryDTOs;
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) throws CategoryException {
		
		Optional<Category> opt = categoryRepository.findById(categoryId);
		Category category = opt.orElseThrow(()-> new CategoryException("Service.CATEGORY_NOT_EXISTS   "+categoryId));
		
		CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
		
		return categoryDTO;
	}

	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) throws CategoryException {
		
		Category category = modelMapper.map(categoryDTO, Category.class);
		
		Category categorySaved = categoryRepository.save(category);
		
		CategoryDTO categoryDTOSaved = modelMapper.map(categorySaved, CategoryDTO.class);
		
		return categoryDTOSaved;
	}

	@Override
	public CategoryDTO updateCategory(Integer categoryId, CategoryDTO categoryDTO) throws CategoryException {
		
		Optional<Category> opt = categoryRepository.findById(categoryId);
		Category category = opt.orElseThrow(()-> new CategoryException("Service.CATEGORY_NOT_EXISTS   "+categoryId));
		
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDesc(categoryDTO.getCategoryDesc());
		
		Category categorySaved = categoryRepository.save(category);
		
		CategoryDTO categoryDTOSaved = modelMapper.map(categorySaved, CategoryDTO.class);
		
		return categoryDTOSaved;
	}

	@Override
	public String deleteCategoryById(Integer categoryId) throws CategoryException {
		
		Optional<Category> opt = categoryRepository.findById(categoryId);
		Category category = opt.orElseThrow(()-> new CategoryException("Service.CATEGORY_NOT_EXISTS"+categoryId));
		
		categoryRepository.delete(category);
		
		return "Category Deleted Successfully With Id : "+ categoryId;
	}
}
