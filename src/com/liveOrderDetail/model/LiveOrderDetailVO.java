package com.liveOrderDetail.model;

import java.io.Serializable;

public class LiveOrderDetailVO implements Serializable{
	private String liveOrderno;
	private String pno;
	private Integer pp;
	private Integer liveOrderQty;
	
	public LiveOrderDetailVO() {
		super();
	}
	
	public LiveOrderDetailVO(String liveOrderno, String pno, Integer pp, Integer liveOrderQty) {
		super();
		this.liveOrderno = liveOrderno;
		this.pno = pno;
		this.pp = pp;
		this.liveOrderQty = liveOrderQty;
	}
	
	public String getLiveOrderno() {
		return liveOrderno;
	}
	public void setLiveOrderno(String liveOrderno) {
		this.liveOrderno = liveOrderno;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public Integer getPp() {
		return pp;
	}
	public void setPp(Integer pp) {
		this.pp = pp;
	}
	public Integer getLiveOrderQty() {
		return liveOrderQty;
	}
	public void setLiveOrderQty(Integer liveOrderQty) {
		this.liveOrderQty = liveOrderQty;
	}
}
