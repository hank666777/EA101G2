package com.tabless.model;

import java.util.List;

public class TableService {
	private TableDAO_interface dao ;
	
	public TableService() {
		dao = new TableDAO();
	}
	
	public void insert(TableVO tableVO) {
		dao.insert(tableVO);
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}
	
	public List<TableVO> getAll(){
		return dao.getAll();
	}
	
	public TableVO getOne(String tableno) {
		return dao.getOne(tableno);
	}
	
	
}
