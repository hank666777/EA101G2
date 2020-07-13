package com.activity.model;

import java.util.List;

public interface ActivityDAO_interface {
	
	void add (ActivityVO ActivityVO);
	void update (ActivityVO ActivityVO);
	void delete (String actno);
	ActivityVO findByPK(String actno);
	List<ActivityVO> getAll();
}
