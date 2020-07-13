package com.actparticipation.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.actparticipation.model.ParticipationService;
import com.actparticipation.model.ParticipationVO;


public class ActparticipationServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
		try{
			String memno = req.getParameter("memno"); 
		
			String actno = req.getParameter("actno");
			
			Timestamp actPatTime = null;
			try{
			actPatTime = Timestamp.valueOf(req.getParameter("actPatTime").trim());
		}catch(IllegalArgumentException e) {
			actPatTime=new Timestamp(System.currentTimeMillis());
			errorMsgs.add("請輸入參加日期!");				
		}
			Integer actParEnr = null;
			try {
				actParEnr = new Integer(req.getParameter("actParEnr").trim());
			}catch(NumberFormatException e) {
				actParEnr = 0;
				errorMsgs.add("請輸入報名總人數");
			}
			
			Integer actTalFee = null;
			try {
				actTalFee = new Integer(req.getParameter("actTalFee").trim());
			}catch(NumberFormatException e){
				actTalFee = 0;
				errorMsgs.add("請輸入報名總費用");
			}
			ParticipationVO PVO = new ParticipationVO();
			PVO.setMemno(memno);
			PVO.setActno(actno);
			PVO.setActPatTime(actPatTime);
			PVO.setActParEnr(actParEnr);
			PVO.setActTalFee(actTalFee);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("PVO",PVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_participation/addparticipation.jsp");
				failureView.forward(req, res);
				
				return; //程式中斷
			}
			/**2 確認資料**/
			ParticipationService actPvc = new ParticipationService();
			PVO = actPvc.add(memno, actno, actPatTime, actParEnr, actTalFee);
			/**3 傳入資料**/
			String url = "/back-end/activity_participation/listAllActivityparticipation.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			/**其他錯誤**/
		}catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_participation/addparticipation.jsp");
			failureView.forward(req, res);
			}
	}
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("erreorMsgs",errorMsgs);
			try {
				
				String memno = req.getParameter("memno");
				if(memno == null || memno.trim().length() ==0) {
					errorMsgs.add("會員編號請勿空白");
				}
				
			
				String actno = req.getParameter("actno");
				
				Timestamp actPatTime = null;
				try{
				actPatTime = Timestamp.valueOf(req.getParameter("actPatTime").trim());
				}catch(IllegalArgumentException e) {
				actPatTime=new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入參加日期!");				
				}	
				Integer actParEnr = null;
				try {
					actParEnr = new Integer(req.getParameter("actParEnr").trim());
				}catch(NumberFormatException e) {
					actParEnr = 0;
					errorMsgs.add("請輸入報名總人數");
				}				
				Integer actTalFee = null;
				try {
					actTalFee = new Integer(req.getParameter("actTalFee").trim());
				}catch(NumberFormatException e){
					actTalFee = 0;
					errorMsgs.add("請輸入報名總費用");
				}
				String avPartno = new String(req.getParameter("avPartno").trim());
				ParticipationVO PVO = new ParticipationVO();
				PVO.setMemno(memno);
				PVO.setActno(actno);
				PVO.setActPatTime(actPatTime);
				PVO.setActParEnr(actParEnr);
				PVO.setActTalFee(actTalFee);
				PVO.setAvPartno(avPartno);
				if(!errorMsgs.isEmpty()) {
					
					req.setAttribute("PVO", PVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_participation/update_activityparticipation_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/**2 確認資料**/
				ParticipationService actPvc = new ParticipationService();
				PVO = actPvc.update(avPartno, memno, actno, actPatTime, actParEnr, actTalFee);
				/**3 傳入資料**/
				String url = "/back-end/activity_participation/listAllActivityparticipation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/**其他錯誤**/
				}catch (Exception e) {
				errorMsgs.add("修改資料失敗"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/activity_participation/update_activityparticipation_input.jsp");
				failureView.forward(req, res);
					}
				}
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("erreoMsgs",errorMsgs);
			try {
				String avPartno = req.getParameter("avPartno");
				
				/**2**/
				ParticipationService PSvc = new ParticipationService();
				PSvc.delete(avPartno);
				/**3**/
				String url = "/back-end/activity_participation/listAllActivityparticipation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/**其他錯誤處理**/
			}catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_participation/listAllActivityparticipation.jsp");
				failureView.forward(req, res);
			}
		}	
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String str = req.getParameter("avPartno");
				if(str == null || (str.trim()).length()==0){
					errorMsgs.add("請輸入報名編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_participation/activityparticipation_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String avPartno = null;
				try {
					avPartno = str;
				} catch (Exception e) {
					errorMsgs.add("報名編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_participation/activityparticipation_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/**2**/
				ParticipationService PSvc = new ParticipationService();
				ParticipationVO PVO = PSvc.getActivitypost(avPartno);
				if(PVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/activity_participation/activityparticipation_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}				
				/**3**/
				req.setAttribute("PVO",PVO);
				String url = "/back-end/activity_participation/listOneActivityparticipation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_participation/activityparticipation_page.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				String avPartno = new String(req.getParameter("avPartno"));
				/**2**/
				ParticipationService PSvc = new ParticipationService();
				ParticipationVO PVO = PSvc.getActivitypost(avPartno);
				/**3**/
				req.setAttribute("PVO",PVO);
				String url = "/back-end/activity_participation/update_activityparticipation_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update.jsp
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_participation/activityparticipation_page.jsp");
				failureView.forward(req, res);				
			}
		}
	}
}

