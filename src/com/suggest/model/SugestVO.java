package com.suggest.model;

import java.sql.Timestamp;

public class SugestVO implements java.io.Serializable{
	
	private String suggestno ;
	private Timestamp suggestDate;
	private String suggestDetail;
	private Integer resStatus;
	private String responseDetail;
	private String memno;
	
	
	public SugestVO(String suggestno, Timestamp suggestDate, String suggestDetail, Integer resStatus,
			String responseDetail, String memno) {
		super();
		this.suggestno = suggestno;
		this.suggestDate = suggestDate;
		this.suggestDetail = suggestDetail;
		this.resStatus = resStatus;
		this.responseDetail = responseDetail;
		this.memno = memno;
	}
	
	
	public SugestVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getSuggestno() {
		return suggestno;
	}
	public void setSuggestno(String suggestno) {
		this.suggestno = suggestno;
	}
	public Timestamp getSuggestDate() {
		return suggestDate;
	}
	public void setSuggestDate(Timestamp suggestDate) {
		this.suggestDate = suggestDate;
	}
	public String getSuggestDetail() {
		return suggestDetail;
	}
	public void setSuggestDetail(String suggestDetail) {
		this.suggestDetail = suggestDetail;
	}
	public Integer getResStatus() {
		return resStatus;
	}
	public void setResStatus(Integer resStatus) {
		this.resStatus = resStatus;
	}
	
	
	public String getResponseDetail() {
		return responseDetail;
	}


	public void setResponseDetail(String responseDetail) {
		this.responseDetail = responseDetail;
	}


	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}

}
