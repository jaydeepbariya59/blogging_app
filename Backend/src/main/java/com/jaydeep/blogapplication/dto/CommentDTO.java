package com.jaydeep.blogapplication.dto;


public class CommentDTO {

	private Integer commentId;
	private String content;
	
	public CommentDTO(Integer commentId, String content) {
		super();
		this.commentId = commentId;
		this.content = content;
	}

	public CommentDTO() {
		super();
	}

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

	@Override
	public String toString() {
		return "CommentDTO [commentId=" + commentId + ", content=" + content + "]";
	}
	
	
	
}

