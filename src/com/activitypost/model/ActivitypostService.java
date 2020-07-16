package com.activitypost.model;

import java.sql.Timestamp;
import java.util.List;

public class ActivitypostService {
	
	private ActivitypostDAO_interface AVO;
	
	public ActivitypostService() {
		AVO = new ActivitypostDAO();
	}
	public ActivitypostVO add(String ACTNO, String MEMNO,
			Timestamp ACTPOSTDATE, String ACTPOSTCON, byte[] ACTPOSTPIC) 
	{
		
		ActivitypostVO APVO = new ActivitypostVO();
		
		
		APVO.setActno(ACTNO);
		APVO.setMemno(MEMNO);
		APVO.setActPostDate(ACTPOSTDATE);
		APVO.setActPostCon(ACTPOSTCON);
		APVO.setActPostPic(ACTPOSTPIC);
		
		AVO.add(APVO);
		
		return APVO;
		
	}
	public ActivitypostVO update(String ACTPOSTNO, String ACTNO, String MEMNO,
			Timestamp ACTPOSTDATE, String ACTPOSTCON, byte[] ACTPOSTPIC) {
		
		ActivitypostVO APVO = new ActivitypostVO();
		
		APVO.setActPostno(ACTPOSTNO);
		APVO.setActno(ACTNO);
		APVO.setMemno(MEMNO);
		APVO.setActPostDate(ACTPOSTDATE);
		APVO.setActPostCon(ACTPOSTCON);
		APVO.setActPostPic(ACTPOSTPIC);
		
		AVO.update(APVO);
		
		return APVO;
	}
	public void delete(String actPostno) {
		AVO.delete(actPostno);
	}
	public ActivitypostVO getActivitypost(String actPostno) {
		return AVO.findByPK(actPostno);
	}
	public List<ActivitypostVO> getAll() {
		return AVO.getAll();
	}
}
