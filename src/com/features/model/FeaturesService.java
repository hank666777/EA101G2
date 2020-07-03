package com.features.model;

import java.util.List;

public class FeaturesService {
	private FeaturesDAO_interface dao;
	
	public FeaturesService() {
		dao = new FeaturesDAO();
	}

	public FeaturesVO addFeatures(String feano, String feaname) {
		FeaturesVO feaVO = new FeaturesVO();
		feaVO.setFeano(feano);
		feaVO.setFeaName(feaname);
		dao.insert(feaVO);
		return feaVO;
	}
	
	public FeaturesVO updateFeatures(String feano, String feaname) {
		FeaturesVO feaVO = new FeaturesVO();
		
		feaVO.setFeano(feano);
		feaVO.setFeaName(feaname);
		dao.update(feaVO);
		return feaVO;
	}
	
	public FeaturesVO getOneFeatures(String feano) {
		return dao.findByPrimaryKey(feano);
	}
	
	public List<FeaturesVO> getAll(){
		return dao.getAll();
	}
}
