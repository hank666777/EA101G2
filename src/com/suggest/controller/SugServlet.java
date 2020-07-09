package com.suggest.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.*;
import com.suggest.model.SugService;
import com.suggest.model.SugestVO;

public class SugServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
       
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {			

				//Timestamp suggestDate = Timestamp.valueOf(req.getParameter("suggestDate"));
                String suggestno = req.getParameter("suggestno");
		    	
				String suggestDetail = req.getParameter("suggestDetail");		
				if(suggestDetail==null) {
					errorMsgs.add("內容請勿空白");
				}
				
				Integer resStatus = 0 ;
				
				String responseDetail = "null";
				
					
				//將會員登入的seesion拿來用
				MemVO mem = (MemVO) session.getAttribute("memVO");
//				String mName = mem.getmName();
				String memno = mem.getMemno();
						
				SugestVO sugestVO = new SugestVO();				
				sugestVO.setSuggestDetail(suggestDetail);
				sugestVO.setResStatus(resStatus);
				sugestVO.setResponseDetail(responseDetail);
                sugestVO.setMemno(memno);
                
                
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sugestVO", sugestVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/suggest/addSuggest.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//進資料庫
				SugService sugSvc = new SugService();
                sugSvc.addSug(suggestDetail, resStatus, responseDetail, memno);
                                       
                req.setAttribute("sugestVO", sugestVO);	

                
				String url = "/front-end/suggest/listMySuggest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllsugest.jsp
				successView.forward(req, res);
				
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/suggest/listMySuggest.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if("getOne_For_Display".equals(action)) {			
				System.out.println("===========================");	
			String memno = req.getParameter("memno");

//			Timestamp suggestDate = Timestamp.valueOf(req.getParameter("suggestDate"));
//			String suggestDetail = req.getParameter("suggestDetail");
//			Integer resStatus = Integer.parseInt(req.getParameter("resStatus"));
//			String responseDetail = req.getParameter("responseDetail");
//            String suggestno = req.getParameter("suggestno");
						           
            SugService listSvc = new SugService();
            List<SugestVO> sugestVO = listSvc.getmySug(memno);    
            
            req.setAttribute("sugestVO",sugestVO);
            String url = "/front-end/suggest/listMySuggest.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
//------------------------------後台控制器-------------------------------------------- 
		
		if("getOne_For_Update".equals(action)) {
			System.out.println("=========選擇一個反應單=================");	
	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try{
				
				String suggestno = new String(req.getParameter("suggestno"));
				
				SugService sugSvc = new SugService();
				SugestVO sugestVO = sugSvc.getoneSug(suggestno);
			
				req.setAttribute("sugestVO", sugestVO);
				String url = "/back-end/suggest/update_sug_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
//			}catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/suggest/listAllSug.jsp");
//				failureView.forward(req, res);
//			}		
		}
        
		if ("back_update".equals(action)) {
		System.out.println("===========修改反應單=========");	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//		  try{
				String suggestno = req.getParameter("suggestno");
				Timestamp suggestDate = Timestamp.valueOf(req.getParameter("suggestDate"));
				String suggestDetail = req.getParameter("suggestDetail");
				Integer resStatus = Integer.parseInt(req.getParameter("resStatus"));
				String responseDetail = req.getParameter("responseDetail");
				String memno = req.getParameter("memno");
			
				SugestVO sugestVO = new SugestVO();
				sugestVO.setSuggestno(suggestno);
				sugestVO.setSuggestDate(suggestDate);
				sugestVO.setSuggestDetail(suggestDetail);
				sugestVO.setResStatus(resStatus);
				sugestVO.setResponseDetail(responseDetail);
				sugestVO.setMemno(memno);
			
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("sugestVO", sugestVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/suggest/update_sug_input.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}

			/*************************** 2.開始修改資料 *****************************************/
				SugService sugSvc = new SugService();
				sugestVO = sugSvc.updateSug(suggestDate, suggestDetail, resStatus, responseDetail, memno, suggestno);
				
				resStatus = 1; //修改後狀態變成1 表示已完成回覆
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sugestVO", sugestVO); 
				String url = "/back-end/suggest/listOneSug.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//		   }catch (Exception e) {
//			errorMsgs.add("修改資料失敗:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/back-end/suggest/update_sug_input.jsp");
//			failureView.forward(req, res);
//		 }
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    
    
    
    
    }
}