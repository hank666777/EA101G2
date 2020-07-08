package com.coupon.model;

import java.util.List;

public interface CouponDAO {
	
	public void update(CouponVO cvo);
	public void update2(CouponVO cvo);
	public void insert(CouponVO cvo);
	public void delete(String cno);
	public CouponVO findByPrimaryKey(String cno);
	public List<CouponVO> getAll();
	public Integer findByPrimaryKey2(String cpname);
	public Integer findByPrimaryKey3(String cpsno);
}
