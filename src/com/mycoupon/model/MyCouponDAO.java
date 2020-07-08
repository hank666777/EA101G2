package com.mycoupon.model;

import java.util.List;

public interface MyCouponDAO {
	
	public void insert(MyCouponVO cvo);
	public void update(String csno);
	public List<MyCouponVO> getAll();
	public byte[] getOnePic(String couponno);
	public List<MyCouponVO> getMyCoupon(String memno);
	public List<MyCouponVO> getMyCoupon2(String memno);
}
