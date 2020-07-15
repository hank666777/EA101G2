package com.activity.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ActivityVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String actno;
	private String actTyno;
	private String actName;
	private String actDes;
	private byte[] actPic;
	private Integer actTalPeo;
	private Timestamp actHoDate;
	private Timestamp actStDate;
	private Timestamp actEdDate;
	private Integer actFee;
	private Integer actMode;
	private Integer actUpper;
	private Integer actLower;
	
	public ActivityVO() {
		super();
	}
	public ActivityVO(String actno, String actTyno, String actName, String actDes, byte[] actPic, Integer actTalPeo,
			Timestamp actHoDate, Timestamp actStDate, Timestamp actEdDate, Integer actFee, Integer actMode, Integer actUpper,
			Integer actLower) {
		super();
		this.actno = actno;
		this.actTyno = actTyno;
		this.actName = actName;
		this.actDes = actDes;
		this.actPic = actPic;
		this.actTalPeo = actTalPeo;
		this.actHoDate = actHoDate;
		this.actStDate = actStDate;
		this.actEdDate = actEdDate;
		this.actFee = actFee;
		this.actMode = actMode;
		this.actUpper = actUpper;
		this.actLower = actLower;
	}
	public String getActno() {
		return actno;
	}
	public void setActno(String actno) {
		this.actno = actno;
	}
	public String getActTyno() {
		return actTyno;
	}
	public void setActTyno(String actTyno) {
		this.actTyno = actTyno;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActDes() {
		return actDes;
	}
	public void setActDes(String actDes) {
		this.actDes = actDes;
	}
	public byte[] getActPic() {
		return actPic;
	}
	public void setActPic(byte[] actPic) {
		this.actPic = actPic;
	}
	public Integer getActTalPeo() {
		return actTalPeo;
	}
	public void setActTalPeo(Integer actTalPeo) {
		this.actTalPeo = actTalPeo;
	}
	public Timestamp getActHoDate() {
		return actHoDate;
	}
	public void setActHoDate(Timestamp actHoDate) {
		this.actHoDate = actHoDate;
	}
	public Timestamp getActStDate() {
		return actStDate;
	}
	public void setActStDate(Timestamp actStDate) {
		this.actStDate = actStDate;
	}
	public Timestamp getActEdDate() {
		return actEdDate;
	}
	public void setActEdDate(Timestamp actEdDate) {
		this.actEdDate = actEdDate;
	}
	public Integer getActFee() {
		return actFee;
	}
	public void setActFee(Integer actFee) {
		this.actFee = actFee;
	}
	public Integer getActMode() {
		return actMode;
	}
	public void setActMode(Integer actMode) {
		this.actMode = actMode;
	}
	public Integer getActUpper() {
		return actUpper;
	}
	public void setActUpper(Integer actUpper) {
		this.actUpper = actUpper;
	}
	public Integer getActLower() {
		return actLower;
	}
	public void setActLower(Integer actLower) {
		this.actLower = actLower;
	}
	
	


	

}
