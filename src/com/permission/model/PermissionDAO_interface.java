package com.permission.model;

import java.util.List;

public interface PermissionDAO_interface {
	public void insert(PermissionVO permissionVO);
	public void delete(String empno,String feano);
	//取得一位員工所有的權限
	public List<PermissionVO> findBy_empno(String empno);
	public List<PermissionVO> getAll();
	
	//同時新增permission 與 employee
	public void insert2(PermissionVO PermissionVO, java.sql.Connection con);
}
