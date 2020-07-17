package com.ptype.model;

import java.util.List;

public class PTypeService {
	private PTypeDAO_interface dao;
	
	public PTypeService() {
		dao = new PTypeJNDIDAO();
	}
	
	public PTypeVO addPType(String pTName) {
		
		PTypeVO PTypeVO = new PTypeVO();
		
		PTypeVO.setpTName(pTName);
		
		dao.insert(PTypeVO);
		
		return PTypeVO;
		
	}
	
	public PTypeVO updatePType(String pTno, String pTName) {
		
		PTypeVO PTypeVO = new PTypeVO();
		
		PTypeVO.setpTno(pTno);
		PTypeVO.setpTName(pTName);
		
		dao.update(PTypeVO);
		
		return  dao.findByPrimaryKey(pTno);
		
	}
	
	public void deletePType(String pTno) {
		dao.delete(pTno);
	}
	
	public PTypeVO getOnePType(String pTno) {
		return dao.findByPrimaryKey(pTno);
	}
	
	public List<PTypeVO>getAll() {
		return dao.getAll();
	}
	
}
