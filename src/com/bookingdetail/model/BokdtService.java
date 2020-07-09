package com.bookingdetail.model;

import java.util.List;
import java.util.Map;
import java.sql.Date;

public class BokdtService {
	private BokdtDAO_interface dao ;
	
	public BokdtService() {
		dao = new BokdtDAO();
	}
	
	public void insertOneBokdt(BokdtVO bokdtVO) {
		dao.insert(bokdtVO);
	}
	
	public void deleteByBkNo(String bkNo) {
		dao.deleteByBkNo(bkNo);
	}
	
	public List<BokdtVO> getAll(){
		return dao.getAll();
	}
	
	public List<BokdtVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	
	public List<BokdtVO> getBokdtListByBkNo(String bkNo){
		return dao.getBokdtListByBkNo(bkNo);
	}
	
	public List<BokdtVO> getBokdtListByTime(Date bkDate,String bkPeriod){
		return dao.getBokdtListByTime(bkDate, bkPeriod);
	}
	
	public void changeOrderStatus(String bkdtNo) {
		dao.update(bkdtNo);
	}

}
