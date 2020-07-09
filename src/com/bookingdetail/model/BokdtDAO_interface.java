package com.bookingdetail.model;

import java.sql.Date;
import java.util.*;

import com.bookingdetail.model.BokdtVO;

public interface BokdtDAO_interface {
	
	public void insert(BokdtVO bokdtVO);
	public void deleteByBkNo(String bkNo);
	public void update(String bkdtNo);
	public List<BokdtVO> getAll();
	public List<BokdtVO> getAll(Map<String, String[]> map);
	public List<BokdtVO> getBokdtListByBkNo(String bkNo);
	public List<BokdtVO> getBokdtListByTime(Date bkDate,String bkPeriod);
	
}
