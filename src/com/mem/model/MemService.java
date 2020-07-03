package com.mem.model;

import java.sql.Date;
import java.util.List;

public class MemService {
	
	private MemDAO_interface dao;
	
	public MemService() {
		dao = new MemDAO();
	}
  
	public MemVO addMem(String mAccount,String mPw,byte[] mPic,String mName,
			String mGender,String mPhone,String mEmail,Integer mStatus) {
		
		MemVO memVO = new MemVO();
		memVO.setmAccount(mAccount);
		memVO.setmPw(mPw);
		memVO.setmPic(mPic);
		memVO.setmName(mName);
		memVO.setmGender(mGender);
		memVO.setmPhone(mPhone);
		memVO.setmEmail(mEmail);
		memVO.setmStatus(mStatus);
		dao.insert(memVO);
		
		return memVO;	
	}
	
	public MemVO updateMem(String mAccount,String mPw,byte[] mPic,String mName,
			String mGender,String mPhone,String mEmail,Date mRegDate,Integer mStatus,String memno) {
		
		MemVO memVO = new MemVO();	
		memVO.setmAccount(mAccount);
		memVO.setmPw(mPw);
		memVO.setmPic(mPic);
		memVO.setmName(mName);
		memVO.setmGender(mGender);
		memVO.setmPhone(mPhone);
		memVO.setmEmail(mEmail);
		memVO.setmRegDate(mRegDate);
		memVO.setmStatus(mStatus);
		memVO.setMemno(memno);
		dao.update(memVO);
		
		return memVO;
		
	}
	
	public void deleteMem(String memno) {
		dao.delete(memno);
	}
	
	public MemVO getOneMem(String memno) {
		return dao.findByPrimarykey(memno);
	}
	
	public List<MemVO> getAll(){
		return dao.getALL();
	}
	
	public MemVO memberLogin(String mAccount,String mPw) {
		return dao.memberLogin(mAccount,mPw);
	}
	
	public void identify(Integer mStatus,String mAccount) {
		
		MemVO memVO = new MemVO();	
		memVO.setmStatus(mStatus);
		memVO.setmAccount(mAccount);
		dao.identify(memVO);	
	}
}
