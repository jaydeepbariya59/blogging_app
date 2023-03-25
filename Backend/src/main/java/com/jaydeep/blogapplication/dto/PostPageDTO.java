package com.jaydeep.blogapplication.dto;

import java.util.List;

public class PostPageDTO {
	
	private List<PostDTO> postDTOs;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalElement;
	private Integer totalPages;
	private boolean lastPage;
	
	public PostPageDTO(List<PostDTO> postDTOs, Integer pageNumber, Integer pageSize, Integer totalElement,
			Integer totalPages, boolean lastPage) {
		super();
		this.postDTOs = postDTOs;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElement = totalElement;
		this.totalPages = totalPages;
		this.lastPage = lastPage;
	}

	public PostPageDTO() {
		super();
	}

	public List<PostDTO> getPostDTOs() {
		return postDTOs;
	}

	public void setPostDTOs(List<PostDTO> postDTOs) {
		this.postDTOs = postDTOs;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(Integer totalElement) {
		this.totalElement = totalElement;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	public String toString() {
		return "PostPageDTO [postDTOs=" + postDTOs + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totalElement=" + totalElement + ", totalPages=" + totalPages + ", lastPage=" + lastPage + "]";
	}
	
	
	
}
