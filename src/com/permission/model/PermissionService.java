package com.permission.model;

import java.util.List;

public class PermissionService {
  private PermissionDAO_interface dao;

  public PermissionService() {
	dao = new PermissionDAO();
  }

  public void addPermission(String empno, String feano) {
	PermissionVO perVO = new PermissionVO();
	perVO.setEmpno(empno);
	perVO.setFeano(feano);
	dao.insert(perVO);

//		return perVO;
  }

  public List<PermissionVO> getAll() {
	return dao.getAll();
  }

  public List<PermissionVO> getOnePermission(String empno) {
	return dao.findBy_empno(empno);
  }

  public void deletePermission(String empno, String feano) {
	dao.delete(empno, feano);
  }

}
