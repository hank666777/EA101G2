package com.activity.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.actparticipation.model.ParticipationService;
import com.actparticipation.model.ParticipationVO;
import com.mem.model.MemVO;



@MultipartConfig
public class ActivityShop extends HttpServlet {
	//private static final long serialVersionUID = 1L;
  

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
		try{
			/** 取值  **/
			String actno = req.getParameter("actno");
			req.setAttribute("actno",actno);
			/** 進入 **/ 
			String url = "/front-end/activity/activitycart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activityshop.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("check".equals(action)) { //getOne_For_Display
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String str = req.getParameter("actno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activityshop2.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String actno = null;
				try {
					actno = str;
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activityshop2.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/** 2 **/
				ActivityService actSvc = new ActivityService();
				ActivityVO actVO = actSvc.getactinity(actno);
				if(actVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/activityshop2.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/** 3 **/
				req.setAttribute("actVO",actVO);
				String url = "/front-end/activity/activityshop2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activityshop2.jsp");
				failureView.forward(req, res);
			}

		}
		
		
		if("in".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);

			try {
			/**取值**/
			MemVO memVO = (MemVO) session.getAttribute("memVO"); 
			String memno = memVO.getMemno();

			String actno = req.getParameter("actno");

			Timestamp actPatDate = null;
			
			try {
				actPatDate = Timestamp.valueOf(req.getParameter("actPatDate").trim());
			}catch(Exception e) {
				actPatDate=new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入報名時間");
			}

			Integer actParEnr = null;
			try {
				actParEnr = new Integer(req.getParameter("actParEnr").trim());
			}catch(NumberFormatException e) {
				actParEnr = 0;
				errorMsgs.add("請輸入報名人數");
			}
			Integer actFee = null;
			try {
				actFee = new Integer(req.getParameter("actTalFee").trim());
			}catch(NumberFormatException e){
				actFee = 0;
				errorMsgs.add("請輸入報名費用");
			}
//			Integer actTotal = null;
//			try {
//				actTotal = new Integer(req.getParameter("actTotal").trim());
//			}catch(NumberFormatException e){
//				actTotal = 0;
//				errorMsgs.add("請輸入報名費用");
//			}
//			
//			actTotal += actParEnr;
//			Integer actTalPeo = Integer.valueOf(actTotal);

			//取得總價格
			actFee *= actParEnr;
			Integer actTalFee = Integer.valueOf(actFee);
			
			
//			ActivityService actSvc = new ActivityService();
//			ActivityVO acttVO = actSvc.updateTotal(actTalPeo,actno);
			
//			req.setAttribute("acttVO", acttVO);
			
			
			//====================================================
			
			ParticipationVO PVO = new ParticipationVO();
			PVO.setMemno(memno);
			PVO.setActno(actno);
			PVO.setActPatTime(actPatDate);
			PVO.setActParEnr(actParEnr);
			PVO.setActTalFee(actTalFee);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("PVO",PVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activitycart.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}

			/**2 確認資料**/
			ParticipationService actPvc = new ParticipationService();
			actPvc.add(memno, actno, actPatDate, actParEnr, actTalFee);
//			req.setAttribute("acttVO", acttVO);
//			req.setAttribute("actTalFee", actTalFee);
			/**3 傳入資料**/
			String url = "/front-end/activity/activitythx.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交jsp
			successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/avtivitycart.jsp");
				failureView.forward(req,res);
			}			

		}
		
	}

}
