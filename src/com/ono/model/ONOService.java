package com.ono.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.onodetail.model.ONODetailVO;

public class ONOService {
	
	private ONODAO_interface dao;
	
	public ONOService() {
		dao = new ONODAO();
	}
	
public void addONO(String memno, String couponSno,Timestamp onoTime, Integer onoTotal, Integer onoStatus, Integer onoPay) {
		
		ONOVO onVO = new ONOVO();
		
		onVO.setmemno(memno);
		onVO.setcouponSno(couponSno);
		onVO.setonoTime(onoTime);
		onVO.setonoTotal(onoTotal);
		onVO.setonoStatus(onoStatus);
		onVO.setonoPay(onoPay);
	
		dao.insert(onVO);
	}
	
	public void updateProduct(String memno, String couponSno,Timestamp onoTime, Integer onoTotal, Integer onoStatus, Integer onoPay,String onono) {
		
		ONOVO onVO = new ONOVO();
		
		onVO.setmemno(memno);
		onVO.setcouponSno(couponSno);	
		onVO.setonoTime(onoTime);
		onVO.setonoTotal(onoTotal);
		onVO.setonoStatus(onoStatus);
		onVO.setonoPay(onoPay);
		onVO.setonono(onono);

		dao.update(onVO);
	
	}
	
	public void deleteProduct(String onono) {
		dao.delete(onono);
	}
	
	public ONOVO getOneProduct(String onono) {
		return dao.findByPrimaryKey(onono);
	}
	
	public List<ONOVO>getAll() {
		return dao.getAll();
	}
	
	public ONOVO insertWithONODetail(ONOVO onVO,List<ONODetailVO> list) {
		return dao.insertWithONODetail(onVO, list);
	}
	
	public List<ONOVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
}
