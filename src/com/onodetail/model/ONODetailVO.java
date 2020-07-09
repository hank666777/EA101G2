package com.onodetail.model;

public class ONODetailVO implements java.io.Serializable {
	@Override
	public String toString() {
		return "ONODetailVO [onono=" + onono + ", pno=" + pno + ", onoQty=" + onoQty + ", onoPrice=" + onoPrice + "]";
	}
	private String onono;
	private String pno;
	private Integer onoQty;
	private Integer onoPrice;
	
	public ONODetailVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getonono() {
		return onono;
	}
	public ONODetailVO(String onono, String pno, Integer onoQty, Integer onoPrice) {
		super();
		this.onono = onono;
		this.pno = pno;
		this.onoQty = onoQty;
		this.onoPrice = onoPrice;
	}
	public void setonono(String onono) {
		this.onono = onono;
	}
	public String getpno() {
		return pno;
	}
	public void setpno(String pno) {
		this.pno = pno;
	}
	public Integer getonoQty() {
		return onoQty;
	}
	public void setonoQty(Integer onoQty) {
		this.onoQty = onoQty;
	}
	public Integer getonoPrice() {
		return onoPrice;
	}
	public void setonoPrice(Integer onoPrice) {
		this.onoPrice = onoPrice;
	}
	
}
