package com.jaydeep.blogapplication.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaydeep.blogapplication.dto.CommentDTO;
import com.jaydeep.blogapplication.entity.Comment;
import com.jaydeep.blogapplication.entity.Post;
import com.jaydeep.blogapplication.exception.PostException;
import com.jaydeep.blogapplication.repository.CommentRepository;
import com.jaydeep.blogapplication.repository.PostRepository;

@Service("commentServiceImpl")
@Transactional
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;



	@Override
	public String deleteComment(Integer commentId) throws PostException {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new PostException("CommentService.COMMENT_NOT_FOUND"));
		
		commentRepository.delete(comment);
		
		return "Comment Deleted Successfully";
	}



	@Override
	public CommentDTO addComment(CommentDTO commentDTO, Integer postId) throws PostException {

		Post post = postRepository.findById(postId).orElseThrow(()-> new PostException("CommentService.POST_NOT_FOUND"));
		
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = commentRepository.save(comment);
		
		CommentDTO commentDTOSaved = modelMapper.map(savedComment, CommentDTO.class);

		return commentDTOSaved;
	}
}
