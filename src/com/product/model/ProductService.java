package com.product.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductService {
	private Product_interface dao;
	
	public  ProductService() {
		dao = new ProductJNDIDAO();
	}
	
	public void addProduct(String pname,Integer pP,byte[] pPic,String pDes,Integer pDoffer,
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
		
		dao.add(pd);
	}
	
	public void updateProduct(String pname,Integer pP,byte[] pPic,String pDes,Integer pDoffer,
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
		
		dao.update(pd);
		
	}
	public void deleteProduct(String pno) {
		dao.delete(pno);
	}
	
	public ProductVO getOneProduct(String pno) {
		return dao.findByPK(pno);
	}
	
	public ProductVO getOneNameProduct(String pname) {
		return dao.findByName(pname);
	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	public List<ProductVO> getProductByStatus(int pStatus){
		return dao.getProductByStatus(pStatus);
	}
	public List<ProductVO> getProductByStatusAndType(int pStatus,String pTno){
		return dao.getProductByStatusAndType(pStatus, pTno);
	}
	public Set<ProductVO> getProductBypno(String pno) {
		return dao.getProductBypno(pno);
	}
	public List<ProductVO> getProductByCategory(String pTno){
		return dao.getProductByCategory(pTno);
	}
	public List<ProductVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	public List<ProductVO> getAllselect(String str){
		return dao.getAllselect(str);
	}
	
	//模糊查詢，依據商品名稱商商品描述，符合即是
	public List<ProductVO> get_by_pname_or_pdes(String pname,String pdes){
		List<ProductVO> searchlist = null;
		searchlist = dao.getAll().stream().filter(p-> p.getpname().indexOf(pname) > -1 || p.getpDes().indexOf(pdes) > -1)
																 			.collect(Collectors.toList());

		
		return searchlist;
	}
}
