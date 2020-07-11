package com.messageboard.model;


import java.sql.Timestamp;

public class MessageBoardVO implements java.io.Serializable{
	private String postno;  
	
	private String parentno;
	private String postTitle;
	private Integer postSort;
	private String postDetail;
	private Timestamp postTime;
	private Integer postStatus;
	private String memno;

	public MessageBoardVO() {
		super();
	}

	public String getPostno() {
		return postno;
	}

	public void setPostno(String postno) {
		this.postno = postno;
	}


	public String getParentno() {
		return parentno;
	}

	public void setParentno(String parentno) {
		this.parentno = parentno;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String posttitle) {
		this.postTitle = posttitle;
	}

	public Integer getPostSort() {
		return postSort;
	}

	public void setPostSort(Integer postsort) {
		this.postSort = postsort;
	}

	public String getPostDetail() {
		return postDetail;
	}

	public void setPostDetail(String postdetail) {
		this.postDetail = postdetail;
	}

	public Timestamp getPostTime() {
		return postTime;
	}

	public void setPostTime(Timestamp posttime) {
		this.postTime = posttime;
	}

	public Integer getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(Integer poststatus) {
		this.postStatus = poststatus;
	}
	
	public String getMemno() {
		return memno;
	}
	
	public void setMemno(String memno) {
		this.memno = memno;
	}
}