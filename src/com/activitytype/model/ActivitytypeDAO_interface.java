package com.activitytype.model;

import java.util.List;

public interface ActivitytypeDAO_interface {
	
	void add (ActivitytypeVO actypeVO);
	void update (ActivitytypeVO actypeVO);
	void delete (String acttyNo);
	ActivitytypeVO findByPK(String acttyNo);//PK��
	List<ActivitytypeVO> getAll();
}
