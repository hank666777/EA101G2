package com.product.model;

import java.util.List;

public class TestProduct {
	public static void main(String[] args) {
		
		ProductJDBCDAO dao = new ProductJDBCDAO();
		
		//�s�W
//		ProductVO pd = new ProductVO();
//		pd.setpname("臭屌");
//		pd.setpP(70);
//		pd.setpPic(null);
//		pd.setpDes("888");
//		pd.setpDoffer(5);
//		pd.setINVStatus(1);
//		pd.setpStatus(1);
//		pd.setpTno("PT003");
//		dao.add(pd);
		
		// �ק�
//		ProductVO pd = new ProductVO();
//		pd.setpname("大雞雞");
//		pd.setpP(1500);
//		pd.setpPic(null);
//		pd.setpDes("5555");
//		pd.setpDoffer(200);
//		pd.setINVStatus(1);
//		pd.setpStatus(1);
//		pd.setpTno("PT001");
//		pd.setpno("P0001");
//		dao.update(pd);
		
		// �R��
//		dao.delete("P000000010");
		
		// �d��(��PK�d��)
//		ProductVO pd = dao.findByPK("P0001");
//		System.out.println(pd.getpno());
//		System.out.println(pd.getpname());
//		System.out.println(pd.getpP());
//		System.out.println(pd.getpPic());
//		System.out.println(pd.getpDes());
//		System.out.println(pd.getpDoffer());
//		System.out.println(pd.getINVStatus());
//		System.out.println(pd.getpStatus());
//		System.out.println(pd.getpTno());
		
		// �d��(�d�ߥ�����T)
//		List<ProductVO> list = dao.getAll();
//		for(ProductVO pd : list) {
//			System.out.println(pd.getpno());
//			System.out.println(pd.getpname());
//			System.out.println(pd.getpP());
//			System.out.println(pd.getpPic());
//			System.out.println(pd.getpDes());
//			System.out.println(pd.getpDoffer());
//			System.out.println(pd.getINVStatus());
//			System.out.println(pd.getpStatus());
//			System.out.println(pd.getpTno());
//		}
	}
}
