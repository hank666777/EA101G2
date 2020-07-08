package com.coupon.model;


import java.util.List;



public class CpService {

		private CouponDAO cdao;
		
		public CpService() {
			cdao=new CouponJNDIDAO();
		}
		
		public CouponVO add(String couponName, Integer couponDiscount,
				byte[] couponPic) {

			CouponVO cp = new CouponVO();

			cp.setCouponName(couponName);
			cp.setCouponDiscount(couponDiscount);
			cp.setCouponPicture(couponPic);
			cdao.insert(cp);

			return cp;
		}

		public CouponVO update(String couponno, String couponName, Integer couponDiscount,
				byte[] couponPic) {

			CouponVO cp = new CouponVO();

			cp.setCouponno(couponno);
			cp.setCouponName(couponName);
			cp.setCouponDiscount(couponDiscount);
			cp.setCouponPicture(couponPic);
			cdao.update(cp);
			
			return cdao.findByPrimaryKey(couponno);
		}
		public CouponVO update(String couponno, String couponName, Integer couponDiscount
				) {

			CouponVO cp = new CouponVO();

			cp.setCouponno(couponno);
			cp.setCouponName(couponName);
			cp.setCouponDiscount(couponDiscount);
			
			cdao.update2(cp);
			
			return cdao.findByPrimaryKey(couponno);
		}
		
		public void delete(String cpno) {
			cdao.delete(cpno);
		}

		public CouponVO getOne(String cpno) {
			return cdao.findByPrimaryKey(cpno);
		}
		public Integer getOne2(String cpname) {
			return cdao.findByPrimaryKey2(cpname);
		}
		public Integer getOne3(String cpname) {
			return cdao.findByPrimaryKey3(cpname);
		}

		public List<CouponVO> getAll() {
			return cdao.getAll();
		}
	}

