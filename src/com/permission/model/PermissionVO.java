package com.permission.model;

import java.io.Serializable;

public class PermissionVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String empno;
	private String feano;
	
	public PermissionVO() {
		super();
	}
	public PermissionVO(String empno, String feano) {
		super();
		this.empno = empno;
		this.feano = feano;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getFeano() {
		return feano;
	}
	public void setFeano(String feano) {
		this.feano = feano;
	}
	@Override
	public String toString() {
		return "permissionVO [empno=" + empno + ", feano=" + feano + "]";
	}
	
}
