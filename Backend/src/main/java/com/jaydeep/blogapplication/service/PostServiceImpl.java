package com.jaydeep.blogapplication.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jaydeep.blogapplication.dto.CommentDTO;
import com.jaydeep.blogapplication.dto.PostDTO;
import com.jaydeep.blogapplication.dto.PostPageDTO;
import com.jaydeep.blogapplication.entity.Category;
import com.jaydeep.blogapplication.entity.Comment;
import com.jaydeep.blogapplication.entity.Post;
import com.jaydeep.blogapplication.entity.User;
import com.jaydeep.blogapplication.exception.PostException;
import com.jaydeep.blogapplication.repository.CategoryRepository;
import com.jaydeep.blogapplication.repository.PostRepository;
import com.jaydeep.blogapplication.repository.UserRepository;

@Service("postServiceImpl")
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	@Override
	public PostDTO addPost(PostDTO postDTO, Integer userId, Integer categoryId) throws PostException {

		User user = userRepository.findById(userId).orElseThrow(()-> new PostException("PostService.USER_NOT_FOUND"));
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new PostException("PostService.CATEGORY_NOT_FOUND"));
		
		Post post = modelMapper.map(postDTO, Post.class);
		post.setCategory(category);
		post.setUser(user);
		
		Post postSaved = postRepository.save(post);
		
		PostDTO postDTOSaved = modelMapper.map(postSaved, PostDTO.class);
		
		return postDTOSaved;
	}

	@Override
	public PostDTO updatePost(Integer postId, PostDTO postDTO) throws PostException {
		Optional<Post> opt = postRepository.findById(postId);
		Post post = opt.orElseThrow(()-> new PostException("PostService.POST_NOT_FOUND"));
		
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		
		Post postSaved = postRepository.save(post);
		
		PostDTO postDTOSaved = modelMapper.map(postSaved, PostDTO.class);
		
		return postDTOSaved;
	}

	@Override
	public String deletePost(Integer postId) throws PostException {
		Optional<Post> opt = postRepository.findById(postId);
		Post post = opt.orElseThrow(()-> new PostException("PostService.POST_NOT_FOUND"));
		
		postRepository.delete(post);
		
		return "Post Deleted Successfully with Id : "+ postId;
	}

	@Override
	public  PostPageDTO getAllPosts(Integer pageSize, Integer pageNumber, String sortBy) throws PostException {
		
		 Pageable p= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		 
		Page<Post> posts= postRepository.findAll(p);
		List<Post> postsOnPage = posts.getContent();
		
		
		if(postsOnPage.isEmpty()) {
			throw new PostException("Service.NO_POST_FOUND");
		}
		
		List<PostDTO> postDTOs = new ArrayList<>();
		
		postsOnPage.forEach(post ->{
			
			PostDTO postDTO = this.modelMapper.map(post, PostDTO.class);
			postDTOs.add(postDTO);
			
		});
		
		PostPageDTO postPageDTO = new PostPageDTO();
		postPageDTO.setPostDTOs(postDTOs);
		postPageDTO.setPageNumber(posts.getNumber());
		postPageDTO.setPageSize(posts.getSize());
		postPageDTO.setTotalElement(posts.getNumberOfElements());
		postPageDTO.setTotalPages(posts.getTotalPages());
		postPageDTO.setLastPage(posts.isLast());
		
		
		return postPageDTO;
	}

	@Override
	public PostDTO getPostById(Integer postId) throws PostException {
		
		Optional<Post> opt = postRepository.findById(postId);
		Post post = opt.orElseThrow(()-> new PostException("PostService.POST_NOT_FOUND"));
		
		Set<Comment> commentsOfPost = post.getComments();
		
		Set<CommentDTO> commentsOfPostDTO = new HashSet<>();
		
		commentsOfPost.forEach(c ->{
			
			CommentDTO commentDTO = modelMapper.map(c, CommentDTO.class);
			commentsOfPostDTO.add(commentDTO);
			
		});
		
		PostDTO postDTO = modelMapper.map(post, PostDTO.class);
		postDTO.setComments(commentsOfPostDTO);
		
		return postDTO;
	}

	@Override
	public PostPageDTO getAllPostsByCategory(Integer categoryId, Integer pageSize, Integer pageNumber) throws PostException {
		
		
		Optional<Category> opt = categoryRepository.findById(categoryId);
		Category category = opt.orElseThrow(()-> new PostException("PostService.CATEGORY_NOT_FOUND"));
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> postsByCategory = postRepository.findAll(p);
		List<Post> posts = postRepository.findByCategory(category);
		
		@SuppressWarnings("unchecked")
		List<PostDTO> postDTOs = modelMapper.map(posts, List.class);
		
		PostPageDTO postPageDTO = new PostPageDTO();

		postPageDTO.setPostDTOs(postDTOs);
		postPageDTO.setPageNumber(postsByCategory.getNumber());
		postPageDTO.setPageSize(postsByCategory.getSize());
		postPageDTO.setTotalElement(postsByCategory.getNumberOfElements());
		postPageDTO.setTotalPages(postsByCategory.getTotalPages());
		postPageDTO.setLastPage(postsByCategory.isLast());
		
		
		
		return postPageDTO;
	}

	@Override
	public List<PostDTO> getAllPostsByUser(Integer userId) throws PostException {
		Optional<User> opt = userRepository.findById(userId);
		User user = opt.orElseThrow(()-> new PostException("PostService.USER_NOT_FOUND"));
		
		List<Post> posts = postRepository.findByUser(user);
		
		List<PostDTO> postDTOs = new ArrayList<>();
		
		posts.forEach((post) -> {
			PostDTO postDTO = modelMapper.map(post, PostDTO.class);
			postDTOs.add(postDTO);
		});
		
	
		return postDTOs;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) throws PostException {
		
		List<Post> posts = postRepository.findByTitleContaining(keyword);
		
		if(posts.isEmpty()) {
			throw new PostException("PostService.NO_TITLE_POST_WITH_THIS_KEYWORD");
		}
		
		List<PostDTO> postDTOs = new ArrayList<>();
		
		posts.forEach(post ->{
			
			PostDTO postDTO = this.modelMapper.map(post, PostDTO.class);
			postDTOs.add(postDTO);
			
		});
		return postDTOs;
	}


	

}
