package com.suggest.model;
import java.sql.Timestamp;
import java.util.List;

public class SugService {
	
   private SugestDAO_interface dao;
   
   public SugService() {
	   dao = new SugestDAO();
   }
   
   public void addSug(String suggestDetail,Integer resStatus,String responseDetail,String memno) {
	   
	   SugestVO sugestVO = new SugestVO();
	   sugestVO.setSuggestDetail(suggestDetail);
	   sugestVO.setResStatus(resStatus);
	   sugestVO.setResponseDetail(responseDetail);
	   sugestVO.setMemno(memno);
	   dao.insert(sugestVO);
	   
//	   return sugestVO;
	   
   }
   
   public SugestVO updateSug (Timestamp suggestDate,String suggestDetail,
		   Integer resStatus,String responseDetail ,String memno,String suggestno) {
	   
	   SugestVO sugestVO = new SugestVO();
	   sugestVO.setSuggestDate(suggestDate);
	   sugestVO.setSuggestDetail(suggestDetail);
	   sugestVO.setResStatus(resStatus);
	   sugestVO.setResponseDetail(responseDetail);
	   sugestVO.setMemno(memno);
	   sugestVO.setSuggestno(suggestno);
	   dao.update(sugestVO);
	   
	   return sugestVO;
   }
   
   public void deleteSug(String suggestno ) {
	       dao.delete(suggestno);
   }
   
   public SugestVO getoneSug(String suggestno) {
	   return dao.findbyPrimarykey(suggestno);
   }
   
   public List<SugestVO> getmySug(String memno) {
	   return dao.findbyMemno(memno);
   }
   
   public List<SugestVO> getAll(){
	   return dao.getAll();   
	   }
   
   
}
