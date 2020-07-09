package com.booking.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class BokService {
	private BokDAO_interface dao ;
	
	public BokService() {
		dao = new BokDAO();
	}
	
	public String insertNewBok(BokVO bokvo) {
		String newbkno ;
		newbkno = dao.insert(bokvo);
		return newbkno ;
	}
	
	public String liveNewBok(BokVO bokvo) {
		String newbkno ;
		newbkno = dao.liveOrder(bokvo);
		return newbkno ;
	}
	
	public void checkin(String bkno) {
		dao.update(bkno);
	}
	
	public void cancelbok(String bkno) {
		dao.cancel(bkno);
	}
	
	public void deleteBok(String bkno) {
		dao.delete(bkno);
	}
	
	public BokVO getBokByBkNo(String bkno) {
		return dao.getBokByBkNo(bkno);		
	}
	
	public List<BokVO> getAll(){
		return dao.getAll();
	}
	
	public List<BokVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	
	public List<BokVO> getBokListByMemNo(String memno){
		return dao.getBokListByMemNo(memno);
	}
	
	public List<BokVO> getBokListByTime(Date bkdate,String bkperiod){
		return dao.getBokListByTime(bkdate, bkperiod);
	}

}
