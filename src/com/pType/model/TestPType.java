package com.pType.model;

import java.util.List;

public class TestPType {
	public static void main(String[] args) {
		PTypeJDBCDAO dao = new PTypeJDBCDAO();
		
//	//新增
//		PTypeVO PTypeVO1 = new PTypeVO();
//		PTypeVO1.setpTName("黑暗料理");
//		
//		dao.insert(PTypeVO1);

//	//修改
//		PTypeVO PTypeVO2 = new PTypeVO();
//		PTypeVO2.setpTno("PT006");
//		PTypeVO2.setpTName("真黑暗料理");
//
//		dao.update(PTypeVO2);
//		
//	//刪除
//		dao.delete("PT006");
//	
//	//查詢
//		PTypeVO PTypeVO3 = dao.findByPrimaryKey("PT001");
//		System.out.print(PTypeVO3.getpTno() + ",");
//		System.out.print(PTypeVO3.getpTName() + ",");
//
//		System.out.println();
		
	
	//查詢全部
		List<PTypeVO>  list=dao.getAll();
		for(PTypeVO aPType: list ) {
			System.out.print(aPType.getpTno() + ",");
			System.out.print(aPType.getpTName() + ",");

			System.out.println();
		}

	}
}
