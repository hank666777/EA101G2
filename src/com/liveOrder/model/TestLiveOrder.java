package com.liveOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class TestLiveOrder {
	public static void main(String[] args) throws ParseException {
		
		LiveOrderJDBCDAO dao = new LiveOrderJDBCDAO();
		
		// �s�W
//		LiveOrderVO lod = new LiveOrderVO();		
//		Timestamp d = new Timestamp(System.currentTimeMillis());	
//		lod.setEmpno("E0000004");
//		lod.setTableno("T0001");
//		lod.setLiveOrderTime(d);
//		lod.setLiveOrderTotal(new Double(10000));
//		lod.setLiveOrderPayment(0);
//		lod.setLiveOrderStatus(1);
//		dao.add(lod);
		
		// �ק�
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
		
		// �R��
//		dao.delete("20200401-L00003");
		
		// �d��(��PK�d��)
//		LiveOrderVO lod = dao.findByPK(1);
//		System.out.println(lod.getLiveOrderno()+ ",");
//		System.out.println(lod.getEmpno()+ ",");
//		System.out.println(lod.getTableno()+ ",");
//		System.out.println(lod.getLiveOrderTime()+ ",");
//		System.out.println(lod.getLiveOrderTotal()+ ",");
//		System.out.println(lod.getLiveOrderPayment()+ ",");
//		System.out.println(lod.getLiveOrderStatus());
//		System.out.println("---------------------");
	
		
		// �d��(�d�ߥ�����T)
//		List<LiveOrderVO> list= dao.getAll();
//		for(LiveOrderVO lod: list) {
//			System.out.println(lod.getLiveOrderno()+ ",");
//			System.out.println(lod.getEmpno()+ ",");
//			System.out.println(lod.getTableno()+ ",");
//			System.out.println(lod.getLiveOrderTime()+ ",");
//			System.out.println(lod.getLiveOrderTotal()+ ",");
//			System.out.println(lod.getLiveOrderPayment()+ ",");
//			System.out.println(lod.getLiveOrderStatus());
//			System.out.println();
//		}
		
	}
}