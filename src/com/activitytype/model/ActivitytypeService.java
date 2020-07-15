package com.activitytype.model;

import java.util.List;

public class ActivitytypeService {
	
	private ActivitytypeDAO_interface AVO;
	
	public ActivitytypeService() {
		AVO = new ActivitypeJNDIDAO();
	}
	
	public ActivitytypeVO add(String ACTTYNO, String ACTTYNAME) {
		
		ActivitytypeVO ACTVO = new ActivitytypeVO();
		
		ACTVO.setActtyNo(ACTTYNO);
		ACTVO.setActTyName(ACTTYNAME);
		
		AVO.add(ACTVO);
		
		return ACTVO;
		
	}
	public ActivitytypeVO update(String ACTTYNO, String ACTTYNAME) {
		
		ActivitytypeVO ACTVO = new ActivitytypeVO();
		
		ACTVO.setActtyNo(ACTTYNO);
		ACTVO.setActTyName(ACTTYNAME);
		
		AVO.update(ACTVO);
		
		return ACTVO;
		
	}
	public void delete(String ACTTYNO) {
		AVO.delete(ACTTYNO);
	}
	public ActivitytypeVO getActivitytype(String ActtyNo) {
		return AVO.findByPK(ActtyNo);	
	}
	public List<ActivitytypeVO> getAll(){
		return AVO.getAll();
	}
}
