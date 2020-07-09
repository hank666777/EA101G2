package com.booking.model;

import java.util.List;

public class testBok {

	public static void main(String[] args) {
		BokJDBCDAO Bok = new BokJDBCDAO();
		BokVO bokvo = new BokVO();
		String str="2020-06-16";
		java.sql.Date bkDate = java.sql.Date.valueOf(str);
		bokvo.setBkDate(bkDate);
		bokvo.setBkPeriod("1400-1500");
		bokvo.setNumOfPeople(7);
		bokvo.setBkPrice(1400);
		bokvo.setMemno("M000000006");
		
		
		String newbkno ;
		newbkno = Bok.insert(bokvo);
		System.out.println(newbkno);
		//Bok.update("20200608-B00002");
		
		List<BokVO> Boklist = Bok.getAll();
		for(BokVO bk : Boklist) {
			System.out.println("訂位編號 :"+bk.getBkno());
			System.out.println("訂位日期 :"+bk.getBkDate());
			System.out.println("訂位時段 :"+bk.getBkPeriod());
			System.out.println("訂位人數 :"+bk.getNumOfPeople());
			System.out.println("預付訂金 :"+bk.getBkPrice());
			System.out.println("訂單狀態 :"+bk.getBkStatus());
			System.out.println("會員編號 :"+bk.getMemno());
			System.out.println("===========================");
		}
		
		Boklist = Bok.getBokListByMemNo("M000000001");
		for(BokVO bk : Boklist) {
			System.out.println("訂位編號 :"+bk.getBkno());
			System.out.println("訂位日期 :"+bk.getBkDate());
			System.out.println("訂位時段 :"+bk.getBkPeriod());
			System.out.println("訂位人數 :"+bk.getNumOfPeople());
			System.out.println("預付訂金 :"+bk.getBkPrice());
			System.out.println("訂單狀態 :"+bk.getBkStatus());
			System.out.println("會員編號 :"+bk.getMemno());
			System.out.println("===========================");
		}
		
		Boklist = Bok.getBokListByTime(bkDate, "1400-1500");
		for(BokVO bk : Boklist) {
			System.out.println("訂位編號 :"+bk.getBkno());
			System.out.println("訂位日期 :"+bk.getBkDate());
			System.out.println("訂位時段 :"+bk.getBkPeriod());
			System.out.println("訂位人數 :"+bk.getNumOfPeople());
			System.out.println("預付訂金 :"+bk.getBkPrice());
			System.out.println("訂單狀態 :"+bk.getBkStatus());
			System.out.println("會員編號 :"+bk.getMemno());
			System.out.println("===========================");
		}
		
		//Bok.delete("20200608-B00002");
		//System.out.println("刪除完成");
	}

}
