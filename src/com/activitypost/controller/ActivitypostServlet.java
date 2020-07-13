package com.activitypost.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.activitypost.model.ActivitypostService;
import com.activitypost.model.ActivitypostVO;

@MultipartConfig
public class ActivitypostServlet extends HttpServlet {
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
			
			try {
				String actno = req.getParameter("actno");
				
				String memno = req.getParameter("memno");
				if(memno == null || memno.trim().length() ==0) {					
					errorMsgs.add("會員號碼請勿空白");
				}
				Timestamp actPostDate = null;
				try {
					actPostDate = Timestamp.valueOf(req.getParameter("actPostDate").trim());
				}catch(IllegalArgumentException e) {
					actPostDate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入推文日期!");				
				}
				String actPostCon = req.getParameter("actPostCon");
				if(actPostCon == null || actPostCon.trim().length() ==0) {
					errorMsgs.add("活動內容請勿空白");
					
				}else if((actPostCon.trim().length())<=10 || (actPostCon.trim().length()) >=3000) {
					errorMsgs.add("活動推文描述請大於10的長度且不得超過3000");
				
				}
				Part part = req.getPart("actPostPic");
			    InputStream in =  part.getInputStream();
			    byte[] actPostPic = new byte[in.available()];
//				if (actPostPic.length == 0) {
//					errorMsgs.add("請上傳圖片");							
//				}	
				in.read(actPostPic);
				
						
				
			ActivitypostVO actPVO = new ActivitypostVO();
			actPVO.setActno(actno);
			actPVO.setMemno(memno);
			actPVO.setActPostDate(actPostDate);
			actPVO.setActPostCon(actPostCon);
			actPVO.setActPostPic(actPostPic);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("actPVO", actPVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_post/addactivitypost.jsp");
				failureView.forward(req, res);
				
				return; //程式中斷
			}
			/**2**/
			ActivitypostService actPSvc =new ActivitypostService();
			actPVO = actPSvc.add(actno,memno,actPostDate,actPostCon,actPostPic);
			/**3**/
			String url = "/back-end/activity_post/listAllActivitypost.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			in.close();	
			/**其他錯誤**/
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activitypost/addactivitypost.jsp");
				failureView.forward(req, res);
				}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				String actno = req.getParameter("actno");
				String memno = req.getParameter("memno");
				if(memno == null || memno.trim().length() ==0) {
					errorMsgs.add("會員編號請勿空白");
				}
				Timestamp actPostDate = null;
				try {
					actPostDate = Timestamp.valueOf(req.getParameter("actPostDate").trim());
				}catch(IllegalArgumentException e) {
					actPostDate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入舉辦日期!");				
				}
				String actPostCon = req.getParameter("actPostCon");
				if(actPostCon == null || actPostCon.trim().length() ==0) {
					errorMsgs.add("活動內容請勿空白");
				}else if(actPostCon.trim().length()<=10 || actPostCon.trim().length() >=3000) {
					errorMsgs.add("活動推文描述請大於10的長度且不得超過3000");
				}
				Part actPostPicArr = req.getPart("actPostPic");
				InputStream in = actPostPicArr.getInputStream();
				byte[] actPostPic = new byte[in.available()];
				if(actPostPic.length == 0) {
					errorMsgs.add("請上傳圖片");
				}
				in.read(actPostPic);
				in.close();
			String actPostno = new String(req.getParameter("actPostno").trim());
			ActivitypostVO actPVO = new ActivitypostVO();
			actPVO.setActno(actno);
			actPVO.setMemno(memno);
			actPVO.setActPostDate(actPostDate);
			actPVO.setActPostCon(actPostCon);
			actPVO.setActPostPic(actPostPic);
			actPVO.setActPostno(actPostno);
			
			if(!errorMsgs.isEmpty()) {
				
				req.setAttribute("actPVO", actPVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_post/update_activitypost_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			/**2**/
			ActivitypostService actPSvc =new ActivitypostService();
			actPVO = actPSvc.update(actPostno, actno, memno, actPostDate, actPostCon, actPostPic);
			/**3**/
			String url = "/back-end/activity_post/listAllActivitypost.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			/**其他錯誤**/
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/activity_post/update_activitypost_input.jsp");
				failureView.forward(req, res);
				}
			}
			if("delete".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs",errorMsgs);
				try {
					String actPostno = req.getParameter("actPostno");
				
					/**2**/
					ActivitypostService actPSvc = new ActivitypostService();
					actPSvc.delete(actPostno);
					/**3**/
					String url = "/back-end/activity_post/listAllActivitypost.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_post/listAllActivity.jsp");
					failureView.forward(req, res);
				}
			}
				if("getOne_For_Display".equals(action)) {
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					try {
						String str = req.getParameter("actPostno");
						if(str == null || (str.trim()).length()==0) {
							errorMsgs.add("請輸入文章編號");
						}
						if(!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_post/activity_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}
						String actPostno = null;
						try {
							actPostno = str;
						} catch (Exception e) {
							errorMsgs.add("活動編號格式不正確");
						}
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_post/activitypost_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}
						/** 2 **/
						ActivitypostService actSvc = new ActivitypostService();
						ActivitypostVO actPVO = actSvc.getActivitypost(actPostno);
						if(actPVO == null) {
							errorMsgs.add("查無資料");
						}
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/activity_post/activitypost_page.jsp");
							failureView.forward(req, res);
							return;//程式中斷
						}
						/** 3 **/
						req.setAttribute("actPVO",actPVO);
						String url = "/back-end/activity_post/listOneActivitypost.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_post/activitypost_page.jsp");
						failureView.forward(req, res);
					}
				}
				if ("getOne_For_Update".equals(action)) {
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						String actPostno = new String(req.getParameter("actPostno"));
						/** 2 **/
						ActivitypostService actSvc = new ActivitypostService();
						ActivitypostVO actPVO = actSvc.getActivitypost(actPostno);
						/** 3 **/

						req.setAttribute("actPVO", actPVO); // 資料庫取出的empVO物件,存入req
						String url = "/back-end/activity_post/update_activitypost_input.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity_post/activitypost_page.jsp");
						failureView.forward(req, res);
					}

				}
			}
		}
	


