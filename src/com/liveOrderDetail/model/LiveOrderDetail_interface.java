package com.liveOrderDetail.model;

import java.util.List;

public interface LiveOrderDetail_interface {
	void add(LiveOrderDetailVO liveOrderDetailVO);
	void update(LiveOrderDetailVO liveOrderDetailVO);
	void delete(String liveOrderno);//����PK
	LiveOrderDetailVO findByPK(String liveOrderno);//�d�߲{���q���T,�^�ǲ{���q�檫��
	List<LiveOrderDetailVO> getAll();
}
