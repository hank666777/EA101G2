package com.actparticipation.model;

import java.sql.Timestamp;
import java.util.List;

public class ParticipationService {
	
	private ParticipationDAO_interface PDAO;
	
	public ParticipationService() {
		PDAO = new ParticipationDAO();
	}
	public ParticipationVO add(String MEMNO, String ACTNO,
			Timestamp ACTPARTDATE, Integer ACTPARTENR,Integer ACTTALFEE) {
		
		ParticipationVO PVO = new ParticipationVO();
		
		PVO.setActno(ACTNO);
		PVO.setMemno(MEMNO);
		PVO.setActPatTime(ACTPARTDATE);
		PVO.setActParEnr(ACTPARTENR);
		PVO.setActTalFee(ACTTALFEE);
		
		PDAO.add(PVO);
		
		return PVO;
		
	}
	public ParticipationVO update(String AVTPARTNO, String MEMNO, String ACTNO,
			Timestamp ACTPARTDATE, Integer ACTPARTENR,Integer ACTTALFEE) {
		
		ParticipationVO PVO = new ParticipationVO();
		
		PVO.setAvPartno(AVTPARTNO);
		PVO.setMemno(MEMNO);
		PVO.setActno(ACTNO);
		PVO.setActPatTime(ACTPARTDATE);
		PVO.setActParEnr(ACTPARTENR);
		PVO.setActTalFee(ACTTALFEE);
		
		PDAO.update(PVO);
		
		return PVO;
	}
	public void delete(String avPartno) {
		PDAO.delete(avPartno);
	}
	public ParticipationVO getActivitypost(String avPartno) {
		return PDAO.findByPK(avPartno);
	}
	public List<ParticipationVO> getAll() {
		return PDAO.getAll();
	}
	public List<String> getAllMemno() {
		return PDAO.getAllMemno();
	}
	public List<ParticipationVO> getAllByMemno(String name) {
		return PDAO.getAllByMemno();
	}
}
