package com.employee.model;

import java.util.List;

import com.permission.model.PermissionVO;

public interface EmployeeDAO_interface {
  public void insert(EmployeeVO employeeVO);

  public void update(EmployeeVO employeeVO);

  public void delete(String empno);

  public EmployeeVO findByPrimaryKey(String empno);

  public List<EmployeeVO> getAll();

  // for login
  public EmployeeVO findBy_eAccount_ePw(String eAccount, String ePw);

  // 同時新增permission 和 employee
  public void insertWithPermission(EmployeeVO employeeVO, List<PermissionVO> list);

}
