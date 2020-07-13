package com.activitypost.model;

import java.util.List;

public interface ActivitypostDAO_interface {

	 void add (ActivitypostVO ActivitypostVO);
	 void update (ActivitypostVO ActivitypostVO);
	 void delete (String actPostno);
	 ActivitypostVO findByPK (String actPostno);
	 List<ActivitypostVO> getAll();
}
