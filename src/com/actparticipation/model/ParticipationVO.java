package com.actparticipation.model;

import java.sql.Timestamp;

public class ParticipationVO implements java.io.Serializable{
	
	private String avPartno;
	private String memno;
	private String actno;
	private Timestamp actPatTime;
	private Integer actParEnr;
	private Integer actTalFee;
	
	public String getAvPartno() {
		return avPartno;
	}
	public void setAvPartno(String avPartno) {
		this.avPartno = avPartno;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getActno() {
		return actno;
	}
	public void setActno(String actno) {
		this.actno = actno;
	}
	public Timestamp getActPatTime() {
		return actPatTime;
	}
	public void setActPatTime(Timestamp actPatTime) {
		this.actPatTime = actPatTime;
	}
	public Integer getActParEnr() {
		return actParEnr;
	}
	public void setActParEnr(Integer actParEnr) {
		this.actParEnr = actParEnr;
	}
	public Integer getActTalFee() {
		return actTalFee;
	}
	public void setActTalFee(Integer actTalFee) {
		this.actTalFee = actTalFee;
	}
}
