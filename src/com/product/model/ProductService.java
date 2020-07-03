package com.product.model;

import java.util.List;

public class ProductService {
	private Product_interface dao;
	
	public  ProductService() {
		dao = new ProductJNDIDAO();
	}
	
	public ProductVO addProduct(String pname,Integer pP,byte[] pPic,String pDes,Integer pDoffer,
			Integer invStatus,Integer pStatus,String pTno) {
		
		ProductVO pd = new ProductVO();
		pd.setpname(pname);
		pd.setpP(pP);
		pd.setpPic(pPic);
		pd.setpDes(pDes);
		pd.setpDoffer(pDoffer);
		pd.setINVStatus(invStatus);
		pd.setpStatus(pStatus);
		pd.setpTno(pTno);
		return pd;
	}
	
	public ProductVO updateProduct(String pname,Integer pP,byte[] pPic,String pDes,Integer pDoffer,
			Integer invStatus,Integer pStatus,String pTno,String pno) {
		
		ProductVO pd = new ProductVO();	
		pd.setpname(pname);
		pd.setpP(pP);
		pd.setpPic(pPic);
		pd.setpDes(pDes);
		pd.setpDoffer(pDoffer);
		pd.setINVStatus(invStatus);
		pd.setpStatus(pStatus);
		pd.setpTno(pTno);
		pd.setpno(pno);
		return pd;
		
	}
	public void deleteProduct(String pno) {
		dao.delete(pno);
	}
	
	public ProductVO getOneProduct(String pno) {
		return dao.findByPK(pno);
	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	public List<ProductVO> getProductByStatus(int pStatus){
		return dao.getProductByStatus(pStatus);
	}
}
