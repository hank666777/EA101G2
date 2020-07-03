package com.liveOrderDetail.model;

import java.util.List;

public class TestLiveOrderDetail {
	public static void main(String[] args) {
		
		LiveOrderDetailJDBCDAO dao = new LiveOrderDetailJDBCDAO();
		
		// �s�W
//		LiveOrderDetail lodd = new LiveOrderDetail();
//		lodd.setLiveOrderno(2);
//		lodd.setPno(5);
//		lodd.setPp(8000);
//		lodd.setLiveOrderQty(7);
//		dao.add(lodd);
		
		//�ק�
//		LiveOrderDetail lodd = new LiveOrderDetail();
//		lodd.setPno(6);
//		lodd.setPp(9000);
//		lodd.setLiveOrderQty(8);
//		lodd.setLiveOrderno(2);
//		dao.update(lodd);
		
		// �R��
//		dao.delete(2);
		
		// �d��(��PK�d��)
//		LiveOrderDetail lodd = dao.findByPK(1);
//		System.out.println(lodd.getLiveOrderno()+ ",");
//		System.out.println(lodd.getPno()+ ",");
//		System.out.println(lodd.getPp()+ ",");
//		System.out.println(lodd.getLiveOrderQty());
//		System.out.println("---------------------");
		
		// �d��(�d�ߥ�����T)
		List<LiveOrderDetailVO> list = dao.getAll();
		for(LiveOrderDetailVO lodd : list) {
			System.out.println(lodd.getLiveOrderno()+ ",");
			System.out.println(lodd.getPno()+ ",");
			System.out.println(lodd.getPp()+ ",");
			System.out.println(lodd.getLiveOrderQty());
			System.out.println();
		}
		
	}
}
