package com.liveOrder.model;

import java.util.List;

public interface LiveOrder_interface {

	void add(LiveOrderVO liveOrderVO);
	void update(LiveOrderVO liveOrderVO);
	void delete(String liveOrderno);//提拱PK
	LiveOrderVO findByPK(String liveOrderno);//查詢現場訂單資訊,回傳現場訂單物件
	List<LiveOrderVO> getAll();
}
