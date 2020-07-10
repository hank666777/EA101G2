package com.messageboard.controller;

import java.io.IOException;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.messageboard.model.MessageBoardService;
import com.messageboard.model.MessageBoardVO;

public class MessageBoardServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String search = req.getParameter("search");
		// 查詢留言 & 所有回覆 
		if ("getOne_For_Display".equals(search)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String postno = req.getParameter("postno");
				
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MessageBoardService mbSvc = new MessageBoardService(); 
				MessageBoardVO mbVO = mbSvc.getOneMessage(postno);
				if(!"0".equals(mbVO.getParentno())) {
					postno = mbVO.getParentno();
					mbVO = mbSvc.getOneMessage(postno);
				}
				if (mbVO == null) {
					errorMsgs.add("查無資料");
				}
				
				List<MessageBoardVO> list = mbSvc.getReply(postno);
				
				MemService mSvc= new MemService();
				List<MemVO> memlist = mSvc.getAll();
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("mbVO", mbVO); // 資料庫取出mbVO物件,存入req
				req.setAttribute("list", list);
				req.setAttribute("memlist",memlist);
				String url = "/front-end/messageboard/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
				failureView.forward(req, res);
			}
		}
		
		//依種類查詢
		if ("getMessageByPostSort".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer postsort = new Integer(req.getParameter("postsort"));
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MessageBoardService mbSvc = new MessageBoardService();
				List<MessageBoardVO> list = mbSvc.getByPostSort(postsort);

				if (list.isEmpty()) {
					errorMsgs.add("發生未預期之狀況請聯絡該網站營運方");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出mbVO物件,存入req
				String url = "/front-end/messageboard/listBySearchCondition.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 查詢某會員所有留言 
		if ("getMessageFromMemno_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memno = req.getParameter("memno");
				String memReg = "M{1}[0-9]{9}$";// 正規表示驗證
				if (memno == null || (memno.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				} else if (!memno.trim().matches(memReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: M開頭且後9碼為數字之組合，如:M000000001");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				

				/*************************** 2.開始查詢資料 *****************************************/
				MessageBoardService mbSvc = new MessageBoardService();
				List<MessageBoardVO> list = mbSvc.getByMemno(memno);

				if (list.isEmpty()) {
					errorMsgs.add("您還沒有留下任何蹤跡");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出mbVO物件,存入req
				String url = "/front-end/messageboard/listMemMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			
		}

		if ("addMessage".equals(action)) { // 來自addMessageEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String posttitle = req.getParameter("posttitle");
				String titleReg = "[]{4,30}$";
							
				if (posttitle == null || posttitle.trim().length() == 0) {
					errorMsgs.add("留言標題: 請勿空白");}
//				} else if (!posttitle.trim().matches(titleReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("留言標題: 只能是中、英文字母、數字和_ , 且長度必需在4到30之間");
//				}
				
				Integer postsort = new Integer(req.getParameter("postsort").trim());

				String postdetail = req.getParameter("postdetail");
				if (postdetail == null || postdetail.trim().length() == 0) {
					errorMsgs.add("留言內容:請勿空白");
				}

				Timestamp posttime = new java.sql.Timestamp(System.currentTimeMillis());
				//"^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10}$"
				String memno = req.getParameter("memno");
//				String memReg = "M{1}[0-9]{9}$";
//				if (memno == null || memno.trim().length() == 0) {
//					errorMsgs.add("會員編號: 請勿空白");
//				} else if (!memno.trim().matches(memReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員編號: M開頭且後9碼為數字之組合，如:M000000001");
//				}
				
				String parentno = "0";
				Integer poststatus = 1;

				MessageBoardVO mbVO = new MessageBoardVO();
				mbVO.setPostTitle(posttitle);
				mbVO.setPostSort(postsort);
				mbVO.setPostDetail(postdetail);
				mbVO.setPostTime(posttime);
				mbVO.setMemno(memno);
				mbVO.setParentno(parentno);
				mbVO.setPostStatus(poststatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mbVO", mbVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/addNew.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MessageBoardService mbSvc = new MessageBoardService();
				mbVO = mbSvc.addMessage(posttitle,postsort,postdetail,posttime,memno,parentno,poststatus);//能直接用mbVO?

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/messageboard/listAllMessageBoard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMessageBoard.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/addNew.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("replyMessage".equals(action)) { // 來自addMessageEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
				String postdetail = req.getParameter("postdetail");
				if (postdetail == null || postdetail.trim().length() == 0) {
					errorMsgs.add("留言內容:請勿空白");
				}
				
				String parentno = req.getParameter("parentno");
				
				Timestamp posttime = new java.sql.Timestamp(System.currentTimeMillis());
				//"^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10}$"
				String memno = req.getParameter("memno");	
				Integer poststatus = 1;

				MessageBoardVO mbVO = new MessageBoardVO();
				mbVO.setPostno(parentno);
				mbVO.setPostDetail(postdetail);
				mbVO.setPostTime(posttime);
				mbVO.setMemno(memno);
				mbVO.setParentno("0");
				mbVO.setPostStatus(poststatus);
				
				MessageBoardService mbSvc = new MessageBoardService();
				List<MessageBoardVO> list = mbSvc.getReply(parentno);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mbVO", mbVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("list", list);

					
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/replyMessage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始新增資料 ***************************************/
				mbSvc.replyMessage(postdetail,posttime,memno,parentno,poststatus);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mbVO", mbVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("list", list);
					
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listOneMessage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				MessageBoardVO mbVO2 = mbSvc.getOneMessage(parentno);
				list = mbSvc.getReply(parentno);
				
				req.setAttribute("mbVO", mbVO2); // 資料庫取出mbVO物件,存入req
				req.setAttribute("list", list);
				String url = "/front-end/messageboard/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String postno =req.getParameter("postno");
				
				/***************************2.開始查詢資料****************************************/
				MessageBoardService mbSvc = new MessageBoardService();
				MessageBoardVO mbVO = mbSvc.getOneMessage(postno);
				if (mbVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}		
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("mbVO", mbVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/messageboard/update_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/messageboard/listAllMessageBoard.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String postno = req.getParameter("postno").trim();
				String parentno = req.getParameter("parentno").trim();
				
				
				String posttitle = req.getParameter("posttitle");
				if("0".equals(parentno)) {
					String titleReg = "{4,30}$";
					if (posttitle == null || posttitle.trim().length() == 0) {
						errorMsgs.add("留言標題: 請勿空白");}					
				}				
				Integer postsort = new Integer(req.getParameter("postsort").trim());

				String postdetail = req.getParameter("postdetail");
				if (postdetail == null || postdetail.trim().length() == 0) {
					errorMsgs.add("留言內容請勿空白");
				}

				Timestamp posttime = Timestamp.valueOf(req.getParameter("posttime"));
				
				String memno = req.getParameter("memno");
				Integer poststatus = 1;

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
					req.setAttribute("mbVO", mbVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/messageboard/update_message_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				MessageBoardService mbSvc = new MessageBoardService();
				mbSvc.updateMessage(postno,posttitle,postsort,postdetail,posttime,memno,parentno,poststatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				//判斷是否為主留言postno,用於後面查詢listOneMessage所需資料
				if(!"0".equals(parentno)) {
					postno = parentno;
				}
				MessageBoardVO mbVO2 = mbSvc.getOneMessage(postno);//主留言
				List<MessageBoardVO> list = mbSvc.getReply(postno);//底下reply
																
				req.setAttribute("mbVO", mbVO2); // 資料庫update成功後,正確的的mbVO物件,存入req
				req.setAttribute("list", list);
				String url = "/front-end/messageboard/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMessage.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/messageboard/update_message_input.jsp");
				failureView.forward(req, res);
				
			}
		}
	}
	
	
	
}
