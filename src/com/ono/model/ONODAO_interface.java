package com.ono.model;

import java.util.*;

import com.onodetail.model.ONODetailVO;

public interface ONODAO_interface {
		public void insert(ONOVO ONOVO);
		public void update(ONOVO ONOVO);
		public void delete(String onono);
		public ONOVO findByPrimaryKey(String onono);
		public List<ONOVO>getAll();
		
		public Set<ONODetailVO> getONODetailByOnono(String onono);
		//同時新增訂單與訂單明細 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
		public ONOVO insertWithONODetail(ONOVO onoVO, List<ONODetailVO> list);
		
		 //萬用複合查詢(傳入參數型態Map)(回傳 List)
	    public List<ONOVO> getAll(Map<String, String[]> map); 
		
}
