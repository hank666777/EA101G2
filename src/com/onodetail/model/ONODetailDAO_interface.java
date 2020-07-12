package com.onodetail.model;

import java.util.*;

import com.product.model.ProductVO;

public interface ONODetailDAO_interface {
	 public void insert(ONODetailVO ondVO);
     public void update(ONODetailVO ondVO);
     public void delete(String onono, String pno);
     public ONODetailVO findByPrimaryKey(String onono, String pno);
     public List<ONODetailVO> getAll();
   //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
     public void insert2(ONODetailVO ondVO,java.sql.Connection con);
   //查詢某類別的商品(一對多)(回傳 Set)
 	public Set<ProductVO> getProductBypno(String pno);//讓購買後頁面回到一樣的位置
 	
 	//取得一筆訂單的所有明細
 	public List<ONODetailVO> getONODetails(String onono);
}
