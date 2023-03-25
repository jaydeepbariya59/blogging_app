package com.jaydeep.blogapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaydeep.blogapplication.dto.CommentDTO;
import com.jaydeep.blogapplication.exception.PostException;
import com.jaydeep.blogapplication.service.CommentServiceImpl;

@RestController
@RequestMapping("")
@CrossOrigin("*")
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	@PostMapping("posts/{postId}/comments")
	public ResponseEntity<CommentDTO> addCommentToPost(@PathVariable Integer postId,@RequestBody CommentDTO commentDTO) throws PostException{
		
		CommentDTO commentDTOSaved = commentServiceImpl.addComment(commentDTO, postId);
		
		return new ResponseEntity<CommentDTO>(commentDTOSaved, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer commentId) throws PostException{
		
		String response = commentServiceImpl.deleteComment(commentId);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
