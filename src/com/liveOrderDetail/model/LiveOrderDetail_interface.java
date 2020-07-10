package com.liveOrderDetail.model;

import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;



public interface LiveOrderDetail_interface {
	void add(LiveOrderDetailVO liveOrderDetailVO);
	void update(LiveOrderDetailVO liveOrderDetailVO);
	void delete(String liveOrderno);//����PK
	LiveOrderDetailVO findByPK(String liveOrderno);//�d�߲{���q���T,�^�ǲ{���q�檫��
	List<LiveOrderDetailVO> getAll();
	
	 //查詢某類別的商品(一對多)(回傳 Set)
 	public Set<ProductVO> getProductBypno(String pno);//讓購買後頁面回到一樣的位置
 	
	//同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insert2 (LiveOrderDetailVO liveOrderDetailVO , java.sql.Connection con);
}
