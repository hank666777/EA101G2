package com.liveOrder.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.liveOrderDetail.model.LiveOrderDetailVO;

public class LiveOrderService {
	
	private LiveOrder_interface dao;
	
	public LiveOrderService() {
		dao = new LiveOrderJNDIDAO();
	}
	
	public void addLiveOrder(String empno,String tableno,Timestamp liveOrderTime,
			Double liveOrderTotal,Integer liveOrderPayment,Integer liveOrderStatus) {
		
		LiveOrderVO lodVO = new LiveOrderVO();
		lodVO.setEmpno(empno);
		lodVO.setTableno(tableno);
		lodVO.setLiveOrderTime(liveOrderTime);
		lodVO.setLiveOrderTotal(liveOrderTotal);
		lodVO.setLiveOrderPayment(liveOrderPayment);
		lodVO.setLiveOrderStatus(liveOrderStatus);
		
		dao.add(lodVO);	
	}
	
	public void updateLiveOrder(String empno,String tableno,Timestamp liveOrderTime,
			Double liveOrderTotal,Integer liveOrderPayment,Integer liveOrderStatus,String liveOrderno) {
		
		LiveOrderVO lodVO = new LiveOrderVO();
		lodVO.setEmpno(empno);
		lodVO.setTableno(tableno);
		lodVO.setLiveOrderTime(liveOrderTime);
		lodVO.setLiveOrderTotal(liveOrderTotal);
		lodVO.setLiveOrderPayment(liveOrderPayment);
		lodVO.setLiveOrderStatus(liveOrderStatus);
		lodVO.setLiveOrderno(liveOrderno);
		
		dao.update(lodVO);
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
	
	public LiveOrderVO insertWithLiveOrderDetail(LiveOrderVO liveOrderVO , List<LiveOrderDetailVO> list){
		return dao.insertWithLiveOrderDetail(liveOrderVO, list);
	}
	public List<LiveOrderVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
}
