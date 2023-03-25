package com.jaydeep.blogapplication.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CategoryDTO {
	
	
	private Integer categoryId;
	
	@NotEmpty(message = "Title cannot be empty")
	@Length(max=50, message = "Title should have maximum 50 characters")
	private String categoryTitle;
	
	
	@NotEmpty(message = "Description cannot be empty")
	@Length(max=500, message = "Description should have maximum 500 characters")
	private String categoryDesc;
	
	public CategoryDTO(Integer categoryId, String categoryTitle, String categoryDesc) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
	}

	public CategoryDTO() {
		super();
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDesc="
				+ categoryDesc + "]";
	}
}
