package com.booking.model;

import java.sql.Date;

import sun.misc.JavaAWTAccess;

public class BokVO implements java.io.Serializable {
	private String bkno ;
	private Date bkDate ;
	private String bkPeriod ;
	private Integer numOfPeople ;
	private Integer bkPrice ;
	private Integer bkStatus ;
	private String memno ;
	
	public String getBkno() {
		return bkno;
	}
	public void setBkno(String bokNo) {
		bkno = bokNo;
	}
	public Date getBkDate() {
		return bkDate;
	}
	public void setBkDate(Date bokDate) {
		bkDate = bokDate;
	}
	public String getBkPeriod() {
		return bkPeriod;
	}
	public void setBkPeriod(String bokPeriod) {
		bkPeriod = bokPeriod;
	}
	public Integer getNumOfPeople() {
		return numOfPeople;
	}
	public void setNumOfPeople(Integer numberOfPeople) {
		numOfPeople = numberOfPeople;
	}
	public Integer getBkPrice() {
		return bkPrice;
	}
	public void setBkPrice(Integer bokPrice) {
		bkPrice = bokPrice;
	}
	public Integer getBkStatus() {
		return bkStatus;
	}
	public void setBkStatus(Integer bokStatus) {
		bkStatus = bokStatus;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memNo) {
		memno = memNo;
	}
	
	
	
	

}
