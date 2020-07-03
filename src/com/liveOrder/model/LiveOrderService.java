package com.liveOrder.model;

import java.sql.Timestamp;
import java.util.List;

public class LiveOrderService {
	private LiveOrder_interface dao;
	
	public LiveOrderService() {
		
	}
	
	public LiveOrderVO addLiveOrder(String empno,String tableno,Timestamp liveOrderTime,
			Double liveOrderTotal,Integer liveOrderPayment,Integer liveOrderStatus) {
		
		LiveOrderVO lodVO = new LiveOrderVO();
		lodVO.setEmpno(empno);
		lodVO.setTableno(tableno);
		lodVO.setLiveOrderTime(liveOrderTime);
		lodVO.setLiveOrderTotal(liveOrderTotal);
		lodVO.setLiveOrderPayment(liveOrderPayment);
		lodVO.setLiveOrderStatus(liveOrderStatus);
		
		return lodVO;	
	}
	
	public LiveOrderVO updateLiveOrder(String liveOrderno,String empno,String tableno,Timestamp liveOrderTime,
			Double liveOrderTotal,Integer liveOrderPayment,Integer liveOrderStatus) {
		
		LiveOrderVO lodVO = new LiveOrderVO();
		lodVO.setLiveOrderno(liveOrderno);
		lodVO.setEmpno(empno);
		lodVO.setTableno(tableno);
		lodVO.setLiveOrderTime(liveOrderTime);
		lodVO.setLiveOrderTotal(liveOrderTotal);
		lodVO.setLiveOrderPayment(liveOrderPayment);
		lodVO.setLiveOrderStatus(liveOrderStatus);
		
		return lodVO;
	}
	
	public void deleteLiveOrder(String liveOrderno) {
		dao.delete(liveOrderno);
	}
	
	public LiveOrderVO getOneLiveOrder(String liveOrderno) {
		return dao.findByPK(liveOrderno);
	}
	
	public List<LiveOrderVO> getAll(){
		return dao.getAll();
	}	
}
