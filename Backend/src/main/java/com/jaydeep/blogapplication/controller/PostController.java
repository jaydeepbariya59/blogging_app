package com.jaydeep.blogapplication.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaydeep.blogapplication.config.AppConstants;
import com.jaydeep.blogapplication.dto.PostDTO;
import com.jaydeep.blogapplication.dto.PostPageDTO;
import com.jaydeep.blogapplication.exception.PostException;
import com.jaydeep.blogapplication.service.PostServiceImpl;

@RestController
@RequestMapping("")
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@PostMapping("user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId) throws PostException{
		
		PostDTO postDTOSaved = postServiceImpl.addPost(postDTO, userId, categoryId);
		
		
		return new ResponseEntity<PostDTO>(postDTOSaved, HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<PostPageDTO> getPostsByCategory(@PathVariable Integer categoryId, @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize, @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber) throws PostException{ 
		
		PostPageDTO postPageDTO = postServiceImpl.getAllPostsByCategory(categoryId,pageSize,pageNumber);
		
		return new ResponseEntity<PostPageDTO>(postPageDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId) throws PostException{ 
		
		List<PostDTO> userPosts = postServiceImpl.getAllPostsByUser(userId);
		
		return new ResponseEntity<List<PostDTO>>(userPosts, HttpStatus.OK);
	}
	
	
	@GetMapping("posts")
	public ResponseEntity<PostPageDTO> getAllPosts(@RequestParam(defaultValue = "4") Integer pageSize, 
			@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue ="title") String sortBy) throws PostException{ 
		
		PostPageDTO postPageDTO = postServiceImpl.getAllPosts(pageSize, pageNumber, sortBy);
		
		return new ResponseEntity<PostPageDTO>(postPageDTO, HttpStatus.OK);
	}
	
	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) throws PostException{ 
		
		PostDTO postDTO = postServiceImpl.getPostById(postId);
		
		return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
	}
	
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDTO> updatePostById(@PathVariable Integer postId,@RequestBody PostDTO postDTO) throws PostException{ 
		
		PostDTO postDTOSaved = postServiceImpl.updatePost(postId, postDTO);
		
		return new ResponseEntity<PostDTO>(postDTOSaved, HttpStatus.OK);
	}
	
	@GetMapping("posts/search")
	public ResponseEntity<List<PostDTO>> searchPosts(@RequestParam String keyword) throws PostException{
		
		List<PostDTO> postDTOs = postServiceImpl.searchPosts(keyword);
		
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<String> deletePostById(@PathVariable Integer postId) throws PostException{
		
		String res = postServiceImpl.deletePost(postId);
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
}
