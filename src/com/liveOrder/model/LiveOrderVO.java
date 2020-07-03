package com.liveOrder.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class LiveOrderVO implements Serializable{
	private String liveOrderno;
	private String empno;
	private String tableno;
	private Timestamp liveOrderTime;
	private Double liveOrderTotal;
	private Integer liveOrderPayment;
	private Integer liveOrderStatus;
	
	public LiveOrderVO() {
		super();
	}
	
	public LiveOrderVO(String liveOrderno, String empno, String tableno, Timestamp liveOrderTime, Double liveOrderTotal,
			Integer liveOrderPayment, Integer liveOrderStatus) {
		super();
		this.liveOrderno = liveOrderno;
		this.empno = empno;
		this.tableno = tableno;
		this.liveOrderTime = liveOrderTime;
		this.liveOrderTotal = liveOrderTotal;
		this.liveOrderPayment = liveOrderPayment;
		this.liveOrderStatus = liveOrderStatus;
	}

	public String getLiveOrderno() {
		return liveOrderno;
	}

	public void setLiveOrderno(String liveOrderno) {
		this.liveOrderno = liveOrderno;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getTableno() {
		return tableno;
	}

	public void setTableno(String tableno) {
		this.tableno = tableno;
	}

	public Timestamp getLiveOrderTime() {
		return liveOrderTime;
	}

	public void setLiveOrderTime(Timestamp liveOrderTime) {
		this.liveOrderTime = liveOrderTime;
	}

	public Double getLiveOrderTotal() {
		return liveOrderTotal;
	}

	public void setLiveOrderTotal(Double liveOrderTotal) {
		this.liveOrderTotal = liveOrderTotal;
	}

	public Integer getLiveOrderPayment() {
		return liveOrderPayment;
	}

	public void setLiveOrderPayment(Integer liveOrderPayment) {
		this.liveOrderPayment = liveOrderPayment;
	}

	public Integer getLiveOrderStatus() {
		return liveOrderStatus;
	}

	public void setLiveOrderStatus(Integer liveOrderStatus) {
		this.liveOrderStatus = liveOrderStatus;
	}
	
}
