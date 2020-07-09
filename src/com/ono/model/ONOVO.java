package com.ono.model;

import java.sql.Timestamp;

public class ONOVO implements java.io.Serializable {
	private String onono;
	private String memno;
	private String couponSno;
	private Timestamp onoTime;
	private Integer onoTotal;
	private Integer onoStatus;
	private Integer onoPay;
	
	public ONOVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ONOVO(String onono, String memno, String couponSno, Timestamp onoTime, Integer onoTotal, Integer onoStatus,
			Integer onoPay) {
		super();
		this.onono = onono;
		this.memno = memno;
		this.couponSno = couponSno;
		this.onoTime = onoTime;
		this.onoTotal = onoTotal;
		this.onoStatus = onoStatus;
		this.onoPay = onoPay;
	}
	public String getonono() {
		return onono;
	}
	public void setonono(String onono) {
		this.onono = onono;
	}
	public String getmemno() {
		return memno;
	}
	public void setmemno(String memno) {
		this.memno = memno;
	}
	public String getcouponSno() {
		return couponSno;
	}
	public void setcouponSno(String couponSno) {
		this.couponSno = couponSno;
	}
	public Timestamp getonoTime() {
		return onoTime;
	}
	public void setonoTime(Timestamp onoTime) {
		this.onoTime = onoTime;
	}
	public Integer getonoTotal() {
		return onoTotal;
	}
	public void setonoTotal(Integer onoTotal) {
		this.onoTotal = onoTotal;
	}
	public Integer getonoStatus() {
		return onoStatus;
	}
	public void setonoStatus(Integer onoStatus) {
		this.onoStatus = onoStatus;
	}
	public Integer getonoPay() {
		return onoPay;
	}
	public void setonoPay(Integer onoPay) {
		this.onoPay = onoPay;
	}
	
	
	
	
	
	
	
}
