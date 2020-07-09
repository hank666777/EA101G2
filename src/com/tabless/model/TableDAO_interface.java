package com.tabless.model;

import java.util.List;

public interface TableDAO_interface {
	public void insert(TableVO tableVO);
	public void update(TableVO tableVO);
	public void delete(String tableNo);
	public TableVO getOne(String tableNo);
	public List<TableVO> getAll();
}
