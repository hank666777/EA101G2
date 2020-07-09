package com.bookingdetail.model;

import java.util.List;

import com.booking.model.BokVO;

public class testBokdt {

	public static void main(String[] args) {
		BokdtJDBCDAO Bokdt = new BokdtJDBCDAO();
		BokdtVO bokdtVO = new BokdtVO();
		
		String str="2020-06-09";
		java.sql.Date bkDate = java.sql.Date.valueOf(str);
		bokdtVO.setBkDate(bkDate);
		bokdtVO.setBkPeriod("1500-1600");
		bokdtVO.setTableno("T0001");
		bokdtVO.setBkno("20200609-B00005");
		
		//Bokdt.insert(bokdtVO);
		
		List<BokdtVO> Bokdtlist = Bokdt.getAll();
		for(BokdtVO bkdt : Bokdtlist) {
			System.out.println("訂位明細編號 :"+bkdt.getBkDetailno());
			System.out.println("訂位日期 :"+bkdt.getBkDate());
			System.out.println("訂位時段 :"+bkdt.getBkPeriod());
			System.out.println("訂位桌號 :"+bkdt.getTableno());
			System.out.println("訂位編號 :"+bkdt.getBkno());
			System.out.println("===========================");
		}
		
		Bokdtlist = Bokdt.getBokdtListByBkNo("20200609-B00006");
		for(BokdtVO bkdt : Bokdtlist) {
			System.out.println("訂位明細編號 :"+bkdt.getBkDetailno());
			System.out.println("訂位日期 :"+bkdt.getBkDate());
			System.out.println("訂位時段 :"+bkdt.getBkPeriod());
			System.out.println("訂位桌號 :"+bkdt.getTableno());
			System.out.println("訂位編號 :"+bkdt.getBkno());
			System.out.println("===========================");
		}
		
		Bokdtlist = Bokdt.getBokdtListByTime(bkDate, "1600-1700");
		for(BokdtVO bkdt : Bokdtlist) {
			System.out.println("訂位明細編號 :"+bkdt.getBkDetailno());
			System.out.println("訂位日期 :"+bkdt.getBkDate());
			System.out.println("訂位時段 :"+bkdt.getBkPeriod());
			System.out.println("訂位桌號 :"+bkdt.getTableno());
			System.out.println("訂位編號 :"+bkdt.getBkno());
			System.out.println("===========================");
		}
		
		//Bokdt.deleteByBkNo("20200607-B00001");

	}

}
