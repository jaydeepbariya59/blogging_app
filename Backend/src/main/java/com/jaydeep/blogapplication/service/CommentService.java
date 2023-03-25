package com.jaydeep.blogapplication.service;

import com.jaydeep.blogapplication.dto.CommentDTO;

public interface CommentService {
	
	public CommentDTO addComment(CommentDTO commentDTO, Integer postId) throws Exception;
	public String deleteComment(Integer commentId) throws Exception;
	
}
