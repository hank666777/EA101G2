package com.liveOrderDetail.model;

import java.util.List;

public interface LiveOrderDetail_interface {
	void add(LiveOrderDetailVO liveOrderDetailVO);
	void update(LiveOrderDetailVO liveOrderDetailVO);
	void delete(String liveOrderno);//提拱PK
	LiveOrderDetailVO findByPK(String liveOrderno);//查詢現場訂單資訊,回傳現場訂單物件
	List<LiveOrderDetailVO> getAll();
}
