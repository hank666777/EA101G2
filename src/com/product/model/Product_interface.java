package com.product.model;

import java.util.List;



public interface Product_interface {
	
	void add(ProductVO productVO);
	void update(ProductVO productVO);
	void delete(String pno);//由PK查詢
	ProductVO findByPK(String pno);
	List<ProductVO> getAll();
	List<ProductVO> getProductByStatus(int pStatus); //前台,後台購物車顯示上架商品
}
