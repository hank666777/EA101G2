package com.actparticipation.model;

import java.util.List;

public interface ParticipationDAO_interface {
	
	void add (ParticipationVO ParticipationVO);
	void update (ParticipationVO ParticipationVO);
	void delete (String avPartno);
	ParticipationVO findByPK (String avPartno);
	List<ParticipationVO> getAll();
	List<ParticipationVO> getAllByMemno();
	List<String> getAllMemno();
}
