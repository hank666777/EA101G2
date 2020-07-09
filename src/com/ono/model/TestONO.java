package com.ono.model;

import java.util.List;
import java.sql.Timestamp;

public class TestONO {
	public static void main(String[] args) {
		ONOJDBCDAO dao = new ONOJDBCDAO();
		Timestamp d = new Timestamp(System.currentTimeMillis());
		
		//新增
//		ONOVO ONOVO1 = new ONOVO();
//		ONOVO1.setmemno("M000000001");
//		ONOVO1.setcouponSno("20200612-CS0100");
//		ONOVO1.setonoTime(d);
//		ONOVO1.setonoTotal(1500);
//		ONOVO1.setonoStatus(1);
//		ONOVO1.setonoPay(1);
//
//		dao.insert(ONOVO1);

        //修改
//		ONOVO ONOVO2 = new ONOVO();
//		ONOVO2.setmemno("M000000001");
//		ONOVO2.setcouponSno("20200612-CS0102");
//		ONOVO2.setonoTime(d);
//		ONOVO2.setonoTotal(2250);
//		ONOVO2.setonoStatus(0);
//		ONOVO2.setonoPay(0);
//		ONOVO2.setonono("20200620-O00010");
//
//		dao.update(ONOVO2);
		
		//刪除
//		dao.delete("20200620-O00001");

		//查詢
//		ONOVO ONOVO3 = dao.findByPrimaryKey("20200612-O00002");
//		System.out.print(ONOVO3.getonono() + ",");
//		System.out.print(ONOVO3.getmemno() + ",");
//		System.out.print(ONOVO3.getcouponSno() + ",");
//		System.out.print(ONOVO3.getonoTime() + ",");
//		System.out.print(ONOVO3.getonoTotal() + ",");
//		System.out.print(ONOVO3.getonoStatus() + ",");
//		System.out.print(ONOVO3.getonoPay() + ",");
//		
//		System.out.println();

		//查詢全部
		List<ONOVO>  list=dao.getAll();
		for(ONOVO aONO: list ) {
			System.out.print(aONO.getonono() + ",");
			System.out.print(aONO.getmemno() + ",");
			System.out.print(aONO.getcouponSno() + ",");
			System.out.print(aONO.getonoTime() + ",");
			System.out.print(aONO.getonoTotal() + ",");
			System.out.print(aONO.getonoStatus() + ",");
			System.out.print(aONO.getonoPay() + ",");
			
	

			System.out.println();
		}

	}
}
