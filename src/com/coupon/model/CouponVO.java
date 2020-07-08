package com.coupon.model;


public class CouponVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String couponno;
	private String couponName;
	private Integer couponDiscount;
	private byte[] couponPic;
	
	
	public CouponVO(String couponno, String couponName, Integer couponDiscount, byte[] couponPic) {
		super();
		this.couponno = couponno;
		this.couponName = couponName;
		this.couponDiscount = couponDiscount;
		this.couponPic = couponPic;
	}

	public CouponVO() {
		super();
	}
	
	public String getCouponno() {
		return couponno;
	}
	public void setCouponno(String couponno) {
		this.couponno = couponno;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Integer getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(Integer couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public byte[] getCouponPic() {
		return couponPic;
	}
	public void setCouponPicture(byte[] couponPic) {
		this.couponPic = couponPic;
	}

	@Override
	public String toString() {
		return "CouponVO [couponno=" + couponno + ", couponName=" + couponName + ", couponDiscount=" + couponDiscount
				+ ", couponPic=" + couponPic + "]";
	}
	
}
