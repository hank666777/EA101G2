package com.tabless.model;

public class TableVO implements java.io.Serializable{
	
	private String tableno;
	private String tableType;
	public TableVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TableVO(String tableno, String tableType) {
		super();
		this.tableno = tableno;
		this.tableType = tableType;
	}
	public String getTableno() {
		return tableno;
	}
	public void setTableno(String tableno) {
		this.tableno = tableno;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
	
	
	

}
