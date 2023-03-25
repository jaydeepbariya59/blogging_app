package com.jaydeep.blogapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaydeep.blogapplication.entity.Category;
import com.jaydeep.blogapplication.entity.Post;
import com.jaydeep.blogapplication.entity.User;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
	public List<Post> findByTitleContaining(String keyword);
}
