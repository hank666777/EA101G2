package com.mycoupon.model;


public class MyCouponVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String couponsno;
	private Integer couponStatus;
	private String couponno;
	private String memno;
	private String count;
	

	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public MyCouponVO() {
		super();
	}
	public MyCouponVO(String couponsno, Integer couponStatus, String couponno, String memno) {
		super();
		this.couponsno = couponsno;
		this.couponStatus = couponStatus;
		this.couponno = couponno;
		this.memno = memno;
	}
	public String getCouponsno() {
		return couponsno;
	}
	public void setCouponsno(String couponsno) {
		this.couponsno = couponsno;
	}
	public Integer getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(Integer couponStatus) {
		this.couponStatus = couponStatus;
	}
	public String getCouponno() {
		return couponno;
	}
	public void setCouponno(String couponno) {
		this.couponno = couponno;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	@Override
	public String toString() {
		return "MyCouponVO [couponsno=" + couponsno + ", couponStatus=" + couponStatus + ", couponno=" + couponno
				+ ", memno=" + memno + ", count=" + count + "]";
	}
	
	
}
