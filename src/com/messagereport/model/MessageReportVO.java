package com.messagereport.model;


import java.sql.Timestamp;

public class MessageReportVO implements java.io.Serializable{
	private String reportno;
	private String reportDetail;
	private Timestamp reportTime;
	private Integer reportStatus;
	private String memno;
	private String postno;
	
	public MessageReportVO() {
		super();
	}

	public String getReportno() {
		return reportno;
	}

	public void setReportno(String reportno) {
		this.reportno = reportno;
	}


	public String getReportDetail() {
		return reportDetail;
	}

	public void setReportDetail(String reportdetail) {
		this.reportDetail = reportdetail;
	}

	public Timestamp getReportTime() {
		return reportTime;
	}

	public void setReportTime(Timestamp reporttime) {
		this.reportTime = reporttime;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportstatus) {
		this.reportStatus = reportstatus;
	}

	public String getMemno() {
		return memno;
	}
	
	public void setMemno(String memno) {
		this.memno = memno;
	}
	
	public String getPostno() {
		return postno;
	}
	
	public void setPostno(String postno) {
		this.postno = postno;
	}
	
}
