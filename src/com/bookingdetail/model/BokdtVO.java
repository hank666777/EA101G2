package com.bookingdetail.model;
import java.sql.Date;

public class BokdtVO implements java.io.Serializable{
	//////////////////////////////
	private String bkDetailno ;
	private Date bkDate ;
	private String bkPeriod ;
	private String tableno ;
	private String bkno ;
	private Integer orderStatus ;
	/////////////////////////////
	public String getBkDetailno() {
		return bkDetailno;
	}
	public void setBkDetailno(String bokDetailNo) {
		bkDetailno = bokDetailNo;
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
	public String getTableno() {
		return tableno;
	}
	public void setTableno(String tableNo) {
		tableno = tableNo;
	}
	public String getBkno() {
		return bkno;
	}
	public void setBkno(String bokNo) {
		bkno = bokNo;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
}
