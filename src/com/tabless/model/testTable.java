package com.tabless.model;

import java.util.List;

public class testTable {

	public static void main(String[] args) {
		TableJDBCDAO Table = new TableJDBCDAO();
//		String tableNo = "T0001";
		
		List<TableVO> Tablelist = Table.getAll();
		for(TableVO t : Tablelist) {
			System.out.print("桌子編號 :"+t.getTableno());
			System.out.println(" 桌子類型 :"+t.getTableType());
		}
		System.out.println("----------------------------");
//		TableVO t = Table.getOne(tableNo);
//		System.out.print("桌子編號 :"+t.getTableNo());
//		System.out.println(" 桌子類型 :"+t.getTableType());
	}

}
