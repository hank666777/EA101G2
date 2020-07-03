package com.employee.model;

import java.io.Serializable;
import java.util.Arrays;

public class EmployeeVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String empno;
	private String eAccount;
	private String ePw;
	private String eName;
	private String ePhone;
	private String eEmail;
	private byte[] ePic;
	private String eTitle;
	private Integer eStatus;
	
	public EmployeeVO() {
		super();
	}

	public EmployeeVO(String empno, String eAccount, String ePw, String eName, String ePhone, String eEmail,
			byte[] ePic, String eTitle, Integer eStatus) {
		super();
		this.empno = empno;
		this.eAccount = eAccount;
		this.ePw = ePw;
		this.eName = eName;
		this.ePhone = ePhone;
		this.eEmail = eEmail;
		this.ePic = ePic;
		this.eTitle = eTitle;
		this.eStatus = eStatus;
	}

	@Override
	public String toString() {
		return "employeeVO [empno=" + empno + ", eAccount=" + eAccount + ", ePw=" + ePw + ", eName=" + eName
				+ ", ePhone=" + ePhone + ", eEmail=" + eEmail + ", ePic=" + Arrays.toString(ePic) + ", eTitle=" + eTitle
				+ ", eStatus=" + eStatus + "]";
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String geteAccount() {
		return eAccount;
	}

	public void seteAccount(String eAccount) {
		this.eAccount = eAccount;
	}

	public String getePw() {
		return ePw;
	}

	public void setePw(String ePw) {
		this.ePw = ePw;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getePhone() {
		return ePhone;
	}

	public void setePhone(String ePhone) {
		this.ePhone = ePhone;
	}

	public String geteEmail() {
		return eEmail;
	}

	public void seteEmail(String eEmail) {
		this.eEmail = eEmail;
	}

	public byte[] getePic() {
		return ePic;
	}

	public void setePic(byte[] ePic) {
		this.ePic = ePic;
	}

	public String geteTitle() {
		return eTitle;
	}

	public void seteTitle(String eTitle) {
		this.eTitle = eTitle;
	}

	public Integer geteStatus() {
		return eStatus;
	}

	public void seteStatus(Integer eStatus) {
		this.eStatus = eStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eAccount == null) ? 0 : eAccount.hashCode());
		result = prime * result + ((eEmail == null) ? 0 : eEmail.hashCode());
		result = prime * result + ((eName == null) ? 0 : eName.hashCode());
		result = prime * result + ((ePhone == null) ? 0 : ePhone.hashCode());
		result = prime * result + Arrays.hashCode(ePic);
		result = prime * result + ((ePw == null) ? 0 : ePw.hashCode());
		result = prime * result + ((eStatus == null) ? 0 : eStatus.hashCode());
		result = prime * result + ((eTitle == null) ? 0 : eTitle.hashCode());
		result = prime * result + ((empno == null) ? 0 : empno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeVO other = (EmployeeVO) obj;
		if (eAccount == null) {
			if (other.eAccount != null)
				return false;
		} else if (!eAccount.equals(other.eAccount))
			return false;
		if (eEmail == null) {
			if (other.eEmail != null)
				return false;
		} else if (!eEmail.equals(other.eEmail))
			return false;
		if (eName == null) {
			if (other.eName != null)
				return false;
		} else if (!eName.equals(other.eName))
			return false;
		if (ePhone == null) {
			if (other.ePhone != null)
				return false;
		} else if (!ePhone.equals(other.ePhone))
			return false;
		if (!Arrays.equals(ePic, other.ePic))
			return false;
		if (ePw == null) {
			if (other.ePw != null)
				return false;
		} else if (!ePw.equals(other.ePw))
			return false;
		if (eStatus == null) {
			if (other.eStatus != null)
				return false;
		} else if (!eStatus.equals(other.eStatus))
			return false;
		if (eTitle == null) {
			if (other.eTitle != null)
				return false;
		} else if (!eTitle.equals(other.eTitle))
			return false;
		if (empno == null) {
			if (other.empno != null)
				return false;
		} else if (!empno.equals(other.empno))
			return false;
		return true;
	}
	
	
}
