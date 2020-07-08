package com.product.model;

import java.io.Serializable;
import java.util.Arrays;

public class ProductVO implements Serializable{
	private String pno;
	private String pname;
	private	Integer pP;
	private byte[] pPic;
	private	String pDes;
	private	Integer pDoffer;
	private Integer invStatus;
	private Integer pStatus;
	private String pTno;
	
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductVO(String pno, String pname, Integer pP, byte[] pPic, String pDes, Integer pDoffer, Integer invStatus,
			Integer pStatus, String pTno) {
		super();
		this.pno = pno;
		this.pname = pname;
		this.pP = pP;
		this.pPic = pPic;
		this.pDes = pDes;
		this.pDoffer = pDoffer;
		this.invStatus = invStatus;
		this.pStatus = pStatus;
		this.pTno = pTno;
	}

	public String getpno() {
		return pno;
	}

	public void setpno(String pno) {
		this.pno = pno;
	}

	public String getpname() {
		return pname;
	}

	public void setpname(String pname) {
		this.pname = pname;
	}

	public Integer getpP() {
		return pP;
	}

	public void setpP(Integer pP) {
		this.pP = pP;
	}

	public byte[] getpPic() {
		return pPic;
	}

	public void setpPic(byte[] pPic) {
		this.pPic = pPic;
	}

	public String getpDes() {
		return pDes;
	}

	public void setpDes(String pDes) {
		this.pDes = pDes;
	}

	public Integer getpDoffer() {
		return pDoffer;
	}

	public void setpDoffer(Integer pDoffer) {
		this.pDoffer = pDoffer;
	}

	public Integer getINVStatus() {
		return invStatus;
	}

	public void setINVStatus(Integer invStatus) {
		this.invStatus = invStatus;
	}

	public Integer getpStatus() {
		return pStatus;
	}

	public void setpStatus(Integer pStatus) {
		this.pStatus = pStatus;
	}

	public String getpTno() {
		return pTno;
	}

	public void setpTno(String pTno) {
		this.pTno = pTno;
	}
	
	

	@Override
	public String toString() {
		return "ProductVO [pno=" + pno + ", pname=" + pname + ", pP=" + pP + ", pPic=" + Arrays.toString(pPic)
				+ ", pDes=" + pDes + ", pDoffer=" + pDoffer + ", invStatus=" + invStatus + ", pStatus=" + pStatus
				+ ", pTno=" + pTno + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;	                //仍要用==來判斷,也許要比較的"物件參考變數",其實指的是同一個  
		if (getClass() != obj.getClass())	//用getclass()來判斷是否屬於同一class	
			return false;
		ProductVO other = (ProductVO) obj;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		return true;
	}

	



	
	
}
