package com.activity.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

@MultipartConfig
public class ActivityServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String actTyno = req.getParameter("actTyno");

				String actName = req.getParameter("actName");
				if (actName == null || actName.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}

				String actDes = req.getParameter("actDes");
				if (actDes == null || actDes.trim().length() == 0) {
					errorMsgs.add("活動描述請勿空白");
				} else if (actDes.trim().length() <= 10 || actDes.trim().length() >= 3000) {
					errorMsgs.add("活動描述請大於等於10的長度且不得超過3000");
				}

				Part part = req.getPart("actPic");
			    InputStream in =  part.getInputStream();
			    byte[] actPic = new byte[in.available()];
				if (actPic.length == 0) {
					errorMsgs.add("請上傳圖片");
				}
				in.read(actPic);
				

				Integer actTalPeo = null;
				try {
					actTalPeo = new Integer(req.getParameter("actTalPeo").trim());
				} catch (NumberFormatException e) {
					actTalPeo = 0;
					errorMsgs.add("請輸入報名人數");
				}
				Timestamp actHoDate = null;
				try {
					actHoDate = Timestamp.valueOf(req.getParameter("actHoDate").trim());
				} catch (IllegalArgumentException e) {
					actHoDate = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入舉辦日期!");
				}
				Timestamp actStDate = null;
				try {
					actStDate = Timestamp.valueOf(req.getParameter("actStDate").trim());
				} catch (IllegalArgumentException e) {
					actStDate = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入報名開始日期!");
				}
				Timestamp actEdDate = null;
				try {
					actEdDate = Timestamp.valueOf(req.getParameter("actEdDate").trim());
				} catch (IllegalArgumentException e) {
					actEdDate = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入截止日期!");
				}
				Integer actFee = null;
				try {
					actFee = new Integer(req.getParameter("actFee").trim());
				} catch (NumberFormatException e) {
					actFee = 0;
					errorMsgs.add("請輸入報名費");
				}
				Integer actMode = null;
				try {
					actMode = new Integer(req.getParameter("actMode").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入成團狀態");
				}
				Integer actUpper = null;
				try {
					actUpper = new Integer(req.getParameter("actUpper").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入人數上限");
				}
				Integer actLower = null;
				try {
					actLower = new Integer(req.getParameter("actLower").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入人數下限");
				}
				ActivityVO actVO = new ActivityVO();
				actVO.setActTyno(actTyno);
				actVO.setActName(actName);
				actVO.setActDes(actDes);
				actVO.setActPic(actPic);
				actVO.setActTalPeo(actTalPeo);
				actVO.setActHoDate(actHoDate);
				actVO.setActStDate(actStDate);
				actVO.setActEdDate(actEdDate);
				actVO.setActFee(actFee);
				actVO.setActMode(actMode);
				actVO.setActUpper(actUpper);
				actVO.setActLower(actLower);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/addactivity.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/** 2 **/
				ActivityService actSvc = new ActivityService();
				actVO = actSvc.add(actTyno, actName, actDes, actPic, actTalPeo, actHoDate, actStDate, actEdDate, actFee,
						actMode, actUpper, actLower);
				/** 3 成功進入**/ 
				String url = "/back-end/activity/listAllActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				in.close();
				/** 其他錯誤 **/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/addactivity.jsp");
				failureView.forward(req, res);
			}

		}
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String actTyno = req.getParameter("actTyno");
				String acttynoReg = "^[a-zA-Z0-9]{5}$";
				if (actTyno == null || actTyno.trim().length() == 0) {
					errorMsgs.add("活動類別編號請勿空白");
				} else if (!actTyno.trim().matches(acttynoReg)) {
					errorMsgs.add("活動編號:只能是英文和數字，且長度必須在5");
				}
				String actName = req.getParameter("actName").trim();
				if (actName == null || actName.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}
				String actDes = req.getParameter("actDes");
				if (actDes == null || actDes.trim().length() == 0) {
					errorMsgs.add("活動描述請勿空白");
				} else if (actDes.trim().length() <= 10 || actDes.trim().length() >= 3000) {
					errorMsgs.add("活動描述請大於等於10的長度且不得超過3000");
				}
				Part actpicArr = req.getPart("actPic");
				InputStream in = actpicArr.getInputStream();
				byte[] actpic = new byte[in.available()];
				if (actpic.length == 0) {
					errorMsgs.add("請上傳圖片");
				}
				in.read(actpic);
				in.close();
				Integer actTalPeo = null;
				try {
					actTalPeo = new Integer(req.getParameter("actTalPeo").trim());
				} catch (NumberFormatException e) {
					actTalPeo = 0;
					errorMsgs.add("請輸入報名人數");
				}
				Timestamp actHoDate = null;
				try {
					actHoDate = Timestamp.valueOf(req.getParameter("actHoDate").trim());
				} catch (IllegalArgumentException e) {
					actHoDate = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入舉辦日期!");
				}
				Timestamp actStDate = null;
				try {
					actStDate = Timestamp.valueOf(req.getParameter("actStDate").trim());
				} catch (IllegalArgumentException e) {
					actStDate = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入報名開始日期!");
				}
				Timestamp actEdDate = null;
				try {
					actEdDate = Timestamp.valueOf(req.getParameter("actEdDate").trim());
				} catch (IllegalArgumentException e) {
					actEdDate = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入截止日期!");
				}
				Integer actFee = null;
				try {
					actFee = new Integer(req.getParameter("actFee").trim());
				} catch (NumberFormatException e) {
					actFee = 0;
					errorMsgs.add("請輸入報名費");
				}
				Integer actMode = null;
				try {
					actMode = new Integer(req.getParameter("actMode").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入成團狀態");
				}
				Integer actUpper = null;
				try {
					actUpper = new Integer(req.getParameter("actUpper").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入人數上限");
				}
				Integer actLower = null;
				try {
					actLower = new Integer(req.getParameter("actLower").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入人數下限");
				}
				String actno = new String(req.getParameter("actno".trim()));
				ActivityVO actVO = new ActivityVO();
				actVO.setActTyno(actTyno);
				actVO.setActName(actName);
				actVO.setActDes(actDes);
				actVO.setActPic(actpic);
				actVO.setActTalPeo(actTalPeo);
				actVO.setActHoDate(actHoDate);
				actVO.setActStDate(actStDate);
				actVO.setActEdDate(actEdDate);
				actVO.setActFee(actFee);
				actVO.setActMode(actMode);
				actVO.setActUpper(actUpper);
				actVO.setActLower(actLower);
				actVO.setActno(actno);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/update_activity_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/** 2 **/
				ActivityService actSvc = new ActivityService();
				actVO = actSvc.update(actno, actTyno, actName, actDes, actpic, actTalPeo, actHoDate, actStDate,
						actEdDate, actFee, actMode, actUpper, actLower);
				/** 3 **/
				req.setAttribute("actVO", actVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/activity/listOneActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/update_activity_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String actno = req.getParameter("actno");

				/** 2 **/
				ActivityService actSvc = new ActivityService();
				actSvc.delete(actno);
				/** 3 **/
				String url = "/back-end/activity/listAllActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/listAllActivity.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String str = req.getParameter("actno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/activity_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/activity_page.jsp");
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
							.getRequestDispatcher("/back-end/activity/activity_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/** 3 **/
				req.setAttribute("actVO",actVO);
				String url = "/back-end/activity/listOneActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/activity_page.jsp");
				failureView.forward(req, res);
			}

		}
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String actno = new String(req.getParameter("actno"));
				/** 2 **/
				ActivityService actSvc = new ActivityService();
				ActivityVO actVO = actSvc.getactinity(actno);
				/** 3 **/

				req.setAttribute("actVO", actVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/activity/update_activity_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/activity_page.jsp");
				failureView.forward(req, res);
			}

		}
	}
}
