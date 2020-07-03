package com.product.model;

import java.util.List;

public class TestProduct {
	public static void main(String[] args) {
		
		ProductJDBCDAO dao = new ProductJDBCDAO();
		
		//�s�W
//		ProductVO pd = new ProductVO();
//		pd.setPname("�A������");
//		pd.setPp(70.0);
//		pd.setPpic(null);
//		pd.setPdes("�n��");
//		pd.setPDoffer(5);
//		pd.setINVstatus(2);
//		pd.setPstatus(2);
//		pd.setPTno("P003");
//		dao.add(pd);
		
		// �ק�
//		ProductVO pd = new ProductVO();
//		pd.setPname("�j������");
//		pd.setPp(1500.0);
//		pd.setPpic(null);
//		pd.setPdes("�W�n��");
//		pd.setPDoffer(45);
//		pd.setINVstatus(1);
//		pd.setPstatus(1);
//		pd.setPTno("P003");
//		pd.setPno("P000000010");
//		dao.add(pd);
		
		// �R��
//		dao.delete("P000000010");
		
		// �d��(��PK�d��)
//		ProductVO pd = dao.findByPK("P000000008");
//		System.out.println(pd.getPno());
//		System.out.println(pd.getPname());
//		System.out.println(pd.getPp());
//		System.out.println(pd.getPpic());
//		System.out.println(pd.getPdes());
//		System.out.println(pd.getPDoffer());
//		System.out.println(pd.getINVstatus());
//		System.out.println(pd.getPstatus());
//		System.out.println(pd.getPTno());
		
		// �d��(�d�ߥ�����T)
		List<ProductVO> list = dao.getAll();
		for(ProductVO pd : list) {
			System.out.println(pd.getpno());
			System.out.println(pd.getpname());
			System.out.println(pd.getpP());
			System.out.println(pd.getpPic());
			System.out.println(pd.getpDes());
			System.out.println(pd.getpDoffer());
			System.out.println(pd.getINVStatus());
			System.out.println(pd.getpStatus());
			System.out.println(pd.getpTno());
		}
	}
}
