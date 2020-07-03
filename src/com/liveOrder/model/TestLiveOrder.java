package com.liveOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class TestLiveOrder {
	public static void main(String[] args) throws ParseException {
		
		LiveOrderJDBCDAO dao = new LiveOrderJDBCDAO();
		
		// 穝糤
//		LiveOrderVO lod = new LiveOrder();		
//		Timestamp d = new Timestamp(System.currentTimeMillis());	
//		lod.setEmpno(2);
//		lod.setTableno(10);
//		lod.setLiveOrderTime(d);
//		lod.setLiveOrderTotal(new Double(10000));
//		lod.setLiveOrderPayment(0);
//		lod.setLiveOrderStatus(1);
//		dao.add(lod);
		
		// э
//		LiveOrderVO lod = new LiveOrderVO();
//		Timestamp d = new Timestamp(System.currentTimeMillis());
//		
//		lod.setEmpno(3);
//		lod.setTableno(5);
//		lod.setLiveOrderTime(d);
//		lod.setLiveOrderTotal(new Double(30000));
//		lod.setLiveOrderPayment(1);
//		lod.setLiveOrderStatus(0);
//		lod.setLiveOrderno(4);
//		dao.update(lod);
		
		// 埃
//		dao.delete(3);
		
		// 琩高(パPK琩高)
//		LiveOrderVO lod = dao.findByPK(1);
//		System.out.println(lod.getLiveOrderno()+ ",");
//		System.out.println(lod.getEmpno()+ ",");
//		System.out.println(lod.getTableno()+ ",");
//		System.out.println(lod.getLiveOrderTime()+ ",");
//		System.out.println(lod.getLiveOrderTotal()+ ",");
//		System.out.println(lod.getLiveOrderPayment()+ ",");
//		System.out.println(lod.getLiveOrderStatus());
//		System.out.println("---------------------");
	
		
		// 琩高(琩高场戈癟)
		List<LiveOrderVO> list= dao.getAll();
		for(LiveOrderVO lod: list) {
			System.out.println(lod.getLiveOrderno()+ ",");
			System.out.println(lod.getEmpno()+ ",");
			System.out.println(lod.getTableno()+ ",");
			System.out.println(lod.getLiveOrderTime()+ ",");
			System.out.println(lod.getLiveOrderTotal()+ ",");
			System.out.println(lod.getLiveOrderPayment()+ ",");
			System.out.println(lod.getLiveOrderStatus());
			System.out.println();
		}
		
	}
}