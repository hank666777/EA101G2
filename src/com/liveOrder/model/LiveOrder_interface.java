package com.liveOrder.model;

import java.util.List;
import java.util.Map;

import com.liveOrderDetail.model.LiveOrderDetailVO;

public interface LiveOrder_interface {

	void add(LiveOrderVO liveOrderVO);
	void update(LiveOrderVO liveOrderVO);
	void delete(String liveOrderno);//����PK
	LiveOrderVO findByPK(String liveOrderno);//�d�߲{���q���T,�^�ǲ{���q�檫��
	List<LiveOrderVO> getAll();
	 //同時新增訂單與訂單明細 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
	public LiveOrderVO insertWithLiveOrderDetail(LiveOrderVO liveOrderVO , List<LiveOrderDetailVO> list);
	
	 //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<LiveOrderVO> getAll(Map<String, String[]> map); 
    
    
}
