package com.jaydeep.blogapplication.service;


import java.util.List;

import com.jaydeep.blogapplication.dto.PostDTO;
import com.jaydeep.blogapplication.dto.PostPageDTO;

import com.jaydeep.blogapplication.exception.PostException;

public interface PostService {
	
	public PostDTO addPost(PostDTO postDTO, Integer userId, Integer categoryId) throws PostException;
	
	public PostDTO updatePost(Integer postId, PostDTO postDTO) throws PostException;
	
	public String deletePost(Integer postId) throws PostException;
	
	public PostPageDTO getAllPosts(Integer pageSize, Integer pageNumber, String sortBy) throws PostException;

	public PostDTO getPostById(Integer postId) throws PostException;
	
	public PostPageDTO getAllPostsByCategory(Integer categoryId, Integer pageSize, Integer pageNumber) throws PostException;
	
	public List<PostDTO> getAllPostsByUser(Integer userId) throws PostException;
	
	public List<PostDTO> searchPosts(String keyword) throws PostException;
	
}
