package com.booking.model;

import java.sql.Date;
import java.util.*;

import com.booking.model.*;

public interface BokDAO_interface {
	public String insert(BokVO bokvo);
	public String liveOrder(BokVO bokvo);
	public void update(String bkno);
	public void cancel(String bkno);
	public void delete(String bkno);
	public BokVO getBokByBkNo(String bkno);
	public List<BokVO> getAll();
	public List<BokVO> getAll(Map<String, String[]> map);
	public List<BokVO> getBokListByMemNo(String memno);
	public List<BokVO> getBokListByTime(Date bkdate,String bkperiod);
}
