package com.mem.model;
import java.sql.Date;

public class MemVO  {
    private String memno;
    private String mAccount;
    private String mPw;
    private byte[] mPic;
    private String mName;
    private String mGender;
    private String mPhone;
    private String mEmail;
    private Date mRegDate;
    private Integer mStatus;
    
	public MemVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemVO(String memno, String mAccount, String mPw, byte[] mPic, String mName, String mGender, String mPhone,
			String mEmail, Date mRegDate, Integer mStatus) {
		super();
		this.memno = memno;
		this.mAccount = mAccount;
		this.mPw = mPw;
		this.mPic = mPic;
		this.mName = mName;
		this.mGender = mGender;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mRegDate = mRegDate;
		this.mStatus = mStatus;
	}

	public String getMemno() {
		return memno;
	}

	public void setMemno(String memno) {
		this.memno = memno;
	}

	public String getmAccount() {
		return mAccount;
	}

	public void setmAccount(String mAccount) {
		this.mAccount = mAccount;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public byte[] getmPic() {
		return mPic;
	}

	public void setmPic(byte[] mPic) {
		this.mPic = mPic;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String mGender) {
		this.mGender = mGender;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public Date getmRegDate() {
		return mRegDate;
	}

	public void setmRegDate(Date mRegDate) {
		this.mRegDate = mRegDate;
	}

	public Integer getmStatus() {
		return mStatus;
	}

	public void setmStatus(Integer mStatus) {
		this.mStatus = mStatus;
	}




	
    
    
}
