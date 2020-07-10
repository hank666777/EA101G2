package com.liveOrderDetail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;

public class LiveOrderDetailService {
	private LiveOrderDetail_interface dao;
	
	public LiveOrderDetailService() {
		dao = new LiveOrderDetailJNDIDAO();
	}
	
	public LiveOrderDetailVO addLiveOrderDetail(String liveOrderno,String pno,
			Integer pp,Integer liveOrderQty) {
		
		LiveOrderDetailVO loddVO = new LiveOrderDetailVO();
		loddVO.setLiveOrderno(liveOrderno);
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
	
	public void insert2(LiveOrderDetailVO liveOrderDetailVO, Connection con) {
		dao.insert2(liveOrderDetailVO, con);
	}
	public Set<ProductVO> getProductBypno(String pno){
		return dao.getProductBypno(pno);
	}
}
