package com.activitypost.model;

import java.sql.Timestamp;

public class ActivitypostVO implements java.io.Serializable{
	private String actPostno;
	private String actno;
	private String memno;
	private Timestamp actPostDate;
	private String actPostCon;
	private byte[] actPostPic;
	
	public String getActPostno() {
		return actPostno;
	}
	public void setActPostno(String actPostno) {
		this.actPostno = actPostno;
	}
	public String getActno() {
		return actno;
	}
	public void setActno(String actno) {
		this.actno = actno;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public Timestamp getActPostDate() {
		return actPostDate;
	}
	public void setActPostDate(Timestamp actPostDate) {
		this.actPostDate = actPostDate;
	}
	public String getActPostCon() {
		return actPostCon;
	}
	public void setActPostCon(String actPostCon) {
		this.actPostCon = actPostCon;
	}
	public byte[] getActPostPic() {
		return actPostPic;
	}
	public void setActPostPic(byte[] actPostPic) {
		this.actPostPic = actPostPic;
	}
}
