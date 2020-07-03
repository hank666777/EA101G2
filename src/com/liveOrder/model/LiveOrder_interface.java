package com.liveOrder.model;

import java.util.List;

public interface LiveOrder_interface {

	void add(LiveOrderVO liveOrderVO);
	void update(LiveOrderVO liveOrderVO);
	void delete(String liveOrderno);//����PK
	LiveOrderVO findByPK(String liveOrderno);//�d�߲{���q���T,�^�ǲ{���q�檫��
	List<LiveOrderVO> getAll();
}
