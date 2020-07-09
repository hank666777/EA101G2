package com.mycoupon.model;


import java.util.List;


public class MyCpService {

		private MyCouponDAO mdao;
		
		public MyCpService() {
			mdao=new MyCouponJNDIDAO();
		}
		
		public MyCouponVO add(String couponno, String memno) {

			MyCouponVO mcvo = new MyCouponVO();
			mcvo.setCouponno(couponno);
			mcvo.setMemno(memno);			
			mdao.insert(mcvo);

			return mcvo;
		}
		
		public void update(String csno) {
		mdao.update(csno);
		}
		
		public List<MyCouponVO> getAll() {
			return mdao.getAll();
		}

	
		public byte[] getPic(String couponno) {
			return mdao.getOnePic(couponno);
		}
		
		public List<MyCouponVO> getMyCoupon(String memno){
			return mdao.getMyCoupon(memno);
			
		}
		public List<MyCouponVO> getMyCoupon2(String memno){
			return mdao.getMyCoupon2(memno);
			
		}
	}

