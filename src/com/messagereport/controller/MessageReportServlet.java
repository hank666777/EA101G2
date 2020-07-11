package com.messagereport.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.messageboard.model.MessageBoardService;
import com.messageboard.model.MessageBoardVO;
import com.messagereport.model.*;

public class MessageReportServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String reportno = req.getParameter("reportno");
				
				String reportReg = "M{1}R{1}[0-9]{3}$";// 正規表示驗證
				if (reportno == null || reportno.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				} else if (!reportno.trim().matches(reportReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("檢舉編號: MR開頭且後3碼為數字之組合，如:MR001");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/messagereport/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MessageReportService mrSvc = new MessageReportService();
				MessageReportVO mrVO = mrSvc.getOneReport(reportno);
				if (mrVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/messagereport/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("mrVO", mrVO); // 資料庫取出mbVO物件,存入req
				String url = "/back-end/messagereport/listOneReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/messagereport/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("Report_OneMessage".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String postno = req.getParameter("postno");
			/*************************** 2.開始查詢資料 *****************************************/
			MessageBoardService mbsvc = new MessageBoardService();
			MessageBoardVO mbVO = mbsvc.getOneMessage(postno);
						
			if (mbVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}		

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("mbVO", mbVO); // 資料庫取出mbVO物件,存入req
			String url = "/front-end/messagereport/addReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addMessage.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
			failureView.forward(req, res);
			}
		}
		
		if ("addReport".equals(action)) { // 來自addReport.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String reportdetail = req.getParameter("reportdetail");
				if (reportdetail == null || reportdetail.trim().length() == 0) {
					errorMsgs.add("留言內容請勿空白");
				}
			
				Timestamp reporttime = new java.sql.Timestamp(System.currentTimeMillis());
				
				String memno = req.getParameter("memno");
				
				String postno = req.getParameter("postno");				
				
				Integer reportstatus = 0;//檢舉成立預設值為0
				
				MessageReportVO mrVO = new MessageReportVO();				
				mrVO.setReportDetail(reportdetail);
				mrVO.setReportTime(reporttime);
				mrVO.setReportStatus(reportstatus);
				mrVO.setMemno(memno);
				mrVO.setPostno(postno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mrVO", mrVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messagereport/addReport.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MessageReportService mbSvc = new MessageReportService();
				mrVO = mbSvc.addReport(reportdetail, reporttime, reportstatus, memno,postno);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/messageboard/messageboard.do?search=getOne_For_Display";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMessageBoard.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messagereport/addReport.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("getOne_For_Update".equals(action)) { // 來自listAllReport.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String reportno =req.getParameter("reportno");
				String postno =req.getParameter("postno");
				
				/***************************2.開始查詢資料****************************************/
				MessageReportService mrSvc = new MessageReportService();
				MessageReportVO mrVO = mrSvc.getOneReport(reportno);
				if (mrVO == null) {
					errorMsgs.add("查無資料");	
				}
				
				MessageBoardService mbSvc = new MessageBoardService();
				MessageBoardVO mbVO = mbSvc.getOneMessage(postno);
				if (mbVO == null) {
					errorMsgs.add("查無資料");	
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/messagereport/listAllMessageReport.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}		
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("mrVO", mrVO);
				req.setAttribute("mbVO", mbVO);// 資料庫取出的mrVO,mbVO物件,存入req
				String url = "/back-end/messagereport/update_report_status.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_report_status.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messagereport/listAllMessageReport.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_report_status.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String reportno = req.getParameter("reportno").trim();
				Integer reportstatus = new Integer(req.getParameter("reportstatus"));
				String reportdetail = req.getParameter("reportdetail").trim();
				Timestamp reporttime = Timestamp.valueOf(req.getParameter("reporttime").trim());
				
				System.out.println(reportdetail +"時間:"+ reporttime);				
				
				String postno = req.getParameter("postno");
				String posttitle = req.getParameter("posttitle");
				Integer postsort = new Integer(req.getParameter("postsort"));
				String postdetail = req.getParameter("postdetail");
				Timestamp posttime = Timestamp.valueOf(req.getParameter("posttime"));
				String memno =req.getParameter("memno");
				String parentno = req.getParameter("parentno");
				Integer poststatus = new Integer(req.getParameter("poststatus"));
				
				MessageReportVO mrVO = new MessageReportVO();
				mrVO.setReportno(reportno);
				mrVO.setReportDetail(reportdetail);
				mrVO.setReportTime(reporttime);
				mrVO.setReportStatus(reportstatus);
				mrVO.setMemno(memno);
				mrVO.setPostno(postno);
				
				MessageBoardVO mbVO = new MessageBoardVO();
				mbVO.setPostno(postno);
				mbVO.setPostTitle(posttitle);
				mbVO.setPostSort(postsort);
				mbVO.setPostDetail(postdetail);
				mbVO.setPostTime(posttime);
				mbVO.setMemno(memno);
				mbVO.setParentno(parentno);
				mbVO.setPostStatus(poststatus);
			
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mrVO", mrVO); 
					req.setAttribute("mbVO", mbVO); // 含有輸入格式錯誤的mbVO,mrVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/messagereport/update_report_status.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				MessageReportService mrSvc = new MessageReportService();
				mrVO = mrSvc.updateReport(reportno,reportdetail,reporttime,reportstatus,memno,postno);				
				
				MessageBoardService mbSvc = new MessageBoardService();
				mbVO = mbSvc.updateMessage(postno,posttitle,postsort,postdetail,posttime,memno,parentno,poststatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mrVO", mrVO);
				req.setAttribute("mbVO", mbVO); // 資料庫update成功後,正確的的mrVO,mbVO物件,存入req
				String url = "/back-end/messagereport/listOneReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMessage.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messagereport/update_report_status.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
