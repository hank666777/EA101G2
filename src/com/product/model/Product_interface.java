package com.product.model;

import java.util.List;
import java.util.Map;
import java.util.Set;





public interface Product_interface {
	
	void add(ProductVO productVO);
	void update(ProductVO productVO);
	void delete(String pno);//由PK查詢
	ProductVO findByPK(String pno);
	ProductVO findByName(String pname);
	List<ProductVO> getAll();
	//查詢某類別的商品(一對多)(回傳 Set)
	public Set<ProductVO> getProductBypno(String pno);//讓購買後頁面回到一樣的位置
	List<ProductVO> getProductByStatus(int pStatus); //前台,後台購物車顯示上架商品
	List<ProductVO> getProductByStatusAndType(int pStatus,String pTno);//前台,後台購物車顯示(上架商品及商品類型)
	
	List<ProductVO> getProductByCategory(String pTno);
	List<ProductVO> getAll(Map<String, String[]> map);
	List<ProductVO> getAllselect(String str);
}
