package com.jaydeep.blogapplication.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaydeep.blogapplication.entity.Comment;
import com.jaydeep.blogapplication.entity.Post;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

	Set<Comment> findByPost(Post post);

}
