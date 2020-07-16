package com.activity.model;

import java.sql.Timestamp;
import java.util.List;




public class ActivityService {
	
	private ActivityDAO_interface AVO;
	
	public ActivityService() {
		AVO = new ActivityDAO();
	}
	
	public ActivityVO add(String ACTTYNO,String ACTNAME,String ACTDES,byte[] ACTPIC,
		Integer ACTTALPEO, Timestamp ACTHODATE, Timestamp ACTSTDATE, Timestamp ACTEDDATE,
		Integer ACTFEE,Integer ACTMODE,Integer ACTUPPER,Integer ACTLOWER) {
		ActivityVO ACVO = new ActivityVO();
		
		
		ACVO.setActTyno(ACTTYNO);
		ACVO.setActName(ACTNAME);
		ACVO.setActDes(ACTDES);
		ACVO.setActPic(ACTPIC);
		ACVO.setActTalPeo(ACTTALPEO);
		ACVO.setActHoDate(ACTHODATE);
		ACVO.setActStDate(ACTSTDATE);
		ACVO.setActEdDate(ACTEDDATE);
		ACVO.setActFee(ACTFEE);
		ACVO.setActMode(ACTMODE);
		ACVO.setActUpper(ACTUPPER);
		ACVO.setActLower(ACTLOWER);
		
		AVO.add(ACVO);
		
		return ACVO;
 	}
	public ActivityVO update(String ACTNO,  String ACTTYNO,String ACTNAME,String ACTDES,byte[] ACTPIC,
			Integer ACTTALPEO, Timestamp ACTHODATE, Timestamp ACTSTDATE, Timestamp ACTEDDATE,
			Integer ACTFEE,Integer ACTMODE,Integer ACTUPPER,Integer ACTLOWER) {
			ActivityVO ACVO = new ActivityVO();
			
			ACVO.setActno(ACTNO);
			ACVO.setActTyno(ACTTYNO);
			ACVO.setActName(ACTNAME);
			ACVO.setActDes(ACTDES);
			ACVO.setActPic(ACTPIC);
			ACVO.setActTalPeo(ACTTALPEO);
			ACVO.setActHoDate(ACTHODATE);
			ACVO.setActStDate(ACTSTDATE);
			ACVO.setActEdDate(ACTEDDATE);
			ACVO.setActFee(ACTFEE);
			ACVO.setActMode(ACTMODE);
			ACVO.setActUpper(ACTUPPER);
			ACVO.setActLower(ACTLOWER);
			
			AVO.update(ACVO);
			
			return ACVO;
	 	}
	public void delete(String actno) {
		AVO.delete(actno);
	}
	public ActivityVO getactinity(String actno) {
		return AVO.findByPK(actno);
	}
	public List<ActivityVO> getAll(){
		return AVO.getAll();
	}
	public ActivityVO updateTotal(Integer ACTTALPEO,String actno) {
		ActivityVO ACVO = new ActivityVO();
		ACVO.setActTalPeo(ACTTALPEO);
		ACVO.setActno(actno);
		return ACVO;
	}
}
