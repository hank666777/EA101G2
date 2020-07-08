package com.onodetail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.liveOrderDetail.model.LiveOrderDetailVO;
import com.product.model.ProductVO;

public class ONODetailService {
	private ONODetailDAO_interface dao;
	
	public ONODetailService() {
		dao = new ONODetailDAO();
	}
	
	public ONODetailVO addONODetail(Integer onoQty, Integer onoPrice) {
		
		ONODetailVO ONODetailVO = new ONODetailVO();
		
		ONODetailVO.setonoQty(onoQty);
		ONODetailVO.setonoQty(onoPrice);
		
		dao.insert(ONODetailVO);
		
		return ONODetailVO;
		
	}
	
	public ONODetailVO updateONODetail(String onono, String pno, Integer onoQty, Integer onoPrice) {
		
		ONODetailVO ONODetailVO = new ONODetailVO();
		
		ONODetailVO.setonono(onono);
		ONODetailVO.setpno(pno);
		ONODetailVO.setonoQty(onoQty);
		ONODetailVO.setonoPrice(onoPrice);
		
		dao.update(ONODetailVO);
		
		return  dao.findByPrimaryKey(onono, pno);
		
	}
	
	public void deleteONODetail(String onono, String pno) {
		dao.delete(onono, pno);
	}
	
	public ONODetailVO getOneONODetail(String onono, String pno) {
		return dao.findByPrimaryKey(onono, pno);
	}
	
	public List<ONODetailVO>getAll() {
		return dao.getAll();
	}
	
	public void insert2(ONODetailVO ondVO, Connection con) {
		dao.insert2(ondVO, con);
	}
	public Set<ProductVO> getProductBypno(String pno){
		return dao.getProductBypno(pno);
	}
}
