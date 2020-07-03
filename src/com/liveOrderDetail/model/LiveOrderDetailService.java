package com.liveOrderDetail.model;

import java.util.List;

public class LiveOrderDetailService {
	private LiveOrderDetail_interface dao;
	
	public LiveOrderDetailService() {
		
	}
	
	public LiveOrderDetailVO addLiveOrderDetail(String pno,
			Integer pp,Integer liveOrderQty) {
		
		LiveOrderDetailVO loddVO = new LiveOrderDetailVO();
		loddVO.setPno(pno);
		loddVO.setPp(pp);
		loddVO.setLiveOrderQty(liveOrderQty);
		
		return loddVO;
	}
	
	public LiveOrderDetailVO updateLiveOrderDetail(String liveOrderno,String pno,
			Integer pp,Integer liveOrderQty) {
		
		LiveOrderDetailVO loddVO = new LiveOrderDetailVO();
		loddVO.setLiveOrderno(liveOrderno);
		loddVO.setPno(pno);
		loddVO.setPp(pp);
		loddVO.setLiveOrderQty(liveOrderQty);
		
		return loddVO;
	}
	
	public void deleteLiveOrderDetail(String liveOrderno) {
		dao.delete(liveOrderno);
	}
	
	public LiveOrderDetailVO getOneLiveOrder(String liveOrderno) {
		return dao.findByPK(liveOrderno);
	}
	
	public List<LiveOrderDetailVO> getAll(){
		return dao.getAll();
	}
	
}
