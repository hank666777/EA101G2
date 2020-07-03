package com.employee.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.permission.model.PermissionVO;

public class EmployeeService {
	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeDAO();
	}

	public EmployeeVO addEmployee(String eAccount,String ePw, String eName, String ePhone, String eEmail, byte[] ePic, String eTitle,
	                              Integer eStatus) {

		EmployeeVO empVO = new EmployeeVO();

		empVO.seteAccount(eAccount);
		empVO.setePw(ePw);
		empVO.seteName(eName);
		empVO.setePhone(ePhone);
		empVO.seteEmail(eEmail);
		empVO.setePic(ePic);
		empVO.seteTitle(eTitle);
		empVO.seteStatus(eStatus);
		dao.insert(empVO);

		return empVO;
	}

	public void updateEmployee(String eAccount, String ePw, String eName, String ePhone, String eEmail, byte[] ePic,
	                           String eTitle, Integer eStatus, String empno) {

		EmployeeVO empVO = new EmployeeVO();

		empVO.seteAccount(eAccount);
		empVO.setePw(ePw);
		empVO.seteName(eName);
		empVO.setePhone(ePhone);
		empVO.seteEmail(eEmail);
		empVO.setePic(ePic);
		empVO.seteTitle(eTitle);
		empVO.seteStatus(eStatus);
		empVO.setEmpno(empno);
		dao.update(empVO);

//		return empVO;
	}

	public void deleteEmployee(String empno) {
		dao.delete(empno);
	}

	public EmployeeVO getOneEmployee(String empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}

	// 登入用
	public EmployeeVO getOneBy_eAccount_ePw(String eAccount, String ePw) {
		return dao.findBy_eAccount_ePw(eAccount, ePw);
	}

	// 新增員工同時新增權限
	public EmployeeVO addEmployeeWithPermission(String eAccount, String ePw, String eName, String ePhone, String eEmail, byte[] ePic,
	                                            String eTitle, Integer eStatus, List<PermissionVO> list) {

		EmployeeVO empVO = new EmployeeVO();

		empVO.seteAccount(eAccount);
		empVO.setePw(ePw);
		empVO.seteName(eName);
		empVO.setePhone(ePhone);
		empVO.seteEmail(eEmail);
		empVO.setePic(ePic);
		empVO.seteTitle(eTitle);
		empVO.seteStatus(eStatus);

		dao.insertWithPermission(empVO, list);

		return empVO;
	}

	// 用詞篩選，可依據編號、姓名、職稱、狀態查詢員工
	public List<EmployeeVO> getAllbyKeyWord(String empno, String eName, String eTitle, Integer eStatus) {
		List<EmployeeVO> emplist = new ArrayList<>();
		emplist = dao.getAll();

		List<EmployeeVO> empfilter = emplist.stream()
						.filter(emp ->
//							System.out.println(emp.getEmpno().indexOf(empno)  + " " +
//											emp.geteName().indexOf(eName)  + " " +
//											emp.geteTitle().equals(eTitle) + " " +
//											emp.geteStatus().equals(eStatus));
								(empno.equals("") || emp.getEmpno().contains(empno)) &&
								(eName.equals("") || emp.geteName().contains(eName)) &&
								(eTitle.equals("") || emp.geteTitle().equals(eTitle)) &&
								eStatus == 99 || emp.geteStatus().equals(eStatus)) //包裝類重寫equals方法
						.collect(Collectors.toList());

//列印測試
//		for (EmployeeVO aemp : empfilter) {
//			System.out.println(aemp.toString());
//			
//		}
		System.out.println("employeeVOService");

		return empfilter;
	}
}
