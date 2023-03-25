package com.jaydeep.blogapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer commentId;
	
	private String content;

	@ManyToOne
	private Post post;
	


	public Integer getCommentId() {
		return commentId;
	}


	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}




	public Comment(Integer commentId, String content, Post post) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.post = post;

	}


	public Comment() {
		super();
	}


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content + ", post=" + post + "]";
	}
	
	
}
