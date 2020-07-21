package com.liveOrder.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeVO;
import com.liveOrder.model.LiveOrderService;
import com.liveOrder.model.LiveOrderVO;
import com.liveOrderDetail.model.LiveOrderDetailVO;
import com.product.model.ProductVO;
import com.tabless.model.TableVO;

@MultipartConfig
public class LiveOrderServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
		if("Detail".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);	
			
			EmployeeVO empVO = (EmployeeVO) session.getAttribute("employeeVO");//取得員工物件
			if(empVO == null) {
				String location=req.getContextPath()+"/back-end/liveShop/CheckOut.jsp";
				req.setAttribute("location", location);
				res.sendRedirect(req.getContextPath()+"/back-end/back-end-index.jsp");
				return;
			}		
			String empno = empVO.getEmpno();
			
			TableVO tbVO = (TableVO)session.getAttribute("tbVO");//取得座號物件
			String tableno = tbVO.getTableNo();
			
			Timestamp liveOrderTime = new Timestamp(System.currentTimeMillis());
			
			
			Double liveOrderTotalxx =  Double.valueOf(req.getParameter("liveOrderTotal"));
			Double liveOrderTotal = liveOrderTotalxx.doubleValue();
			System.out.println(liveOrderTotal);
			
			Integer liveOrderPayment = 0;
			Integer liveOrderStatus = 1;
			
			LiveOrderVO loVO = new LiveOrderVO();
			loVO.setEmpno(empno);
			loVO.setTableno(tableno);
			loVO.setLiveOrderTime(liveOrderTime);
			loVO.setLiveOrderTotal(liveOrderTotal);
			loVO.setLiveOrderPayment(liveOrderPayment);
			loVO.setLiveOrderStatus(liveOrderStatus);
			
			Vector<ProductVO> liveBuyList = (Vector<ProductVO>) session.getAttribute("liveShoppingcart");
			List<LiveOrderDetailVO> list = new ArrayList();
			
			for(ProductVO pdVO : liveBuyList) {
				LiveOrderDetailVO lodVO = new LiveOrderDetailVO();
				lodVO.setLiveOrderno(loVO.getLiveOrderno());
				lodVO.setPno(pdVO.getpno());
				lodVO.setPp(pdVO.getpP());
				lodVO.setLiveOrderQty(pdVO.getpDoffer());
				list.add(lodVO);
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("loVO", loVO); // 含有輸入格式錯誤的member物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("back-end/liveShop/CheckOut.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始查詢資料 *****************************************/
			LiveOrderService loSvc = new LiveOrderService();
			LiveOrderVO loVO2 = loSvc.insertWithLiveOrderDetail(loVO, list);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("loVO2", loVO2);
			String url = "/back-end/liveOrder/listAllLiveOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;// 程式中斷	
		}
		
		if("getOne_For_Display".equals(action)) {	// 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);	
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("liveOrderno");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入現場訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/liveOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String liveOrderno = null;
				try {
					liveOrderno = str;
				}catch(Exception e) {
					errorMsgs.add("現場訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/liveOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				LiveOrderService loSvc = new LiveOrderService();
				LiveOrderVO loVO = loSvc.getOneLiveOrder(liveOrderno);
				if(loVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/liveOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("loVO", loVO); // 資料庫取出的loVO物件,存入req
				String url = "/back-end/liveOrder/listOneLiveOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneLiveOrder.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/			
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/liveOrder/select_page.jsp");
				failureView.forward(req, res);
			}						
		}
		
		if("getOne_For_Update".equals(action)) { // 來自update_liveOrder_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.接收請求參數****************************************/
				String liveOrderno = req.getParameter("liveOrderno");
				
				/***************************2.開始查詢資料****************************************/
				LiveOrderService loSvc = new LiveOrderService();
				LiveOrderVO loVO = loSvc.getOneLiveOrder(liveOrderno);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("loVO", loVO);
				String url = "/back-end/liveOrder/update_liveOrder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/liveOrder/listAllLiveOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) { // 來自update_liveOrder_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String empno = req.getParameter("empno").trim(); //FK
				
				String tableno = req.getParameter("tableno").trim(); //FK
				
				Timestamp liveOrderTime = null;
				try {
					liveOrderTime = Timestamp.valueOf(req.getParameter("liveOrderTime").trim());
				}catch(IllegalArgumentException e) {
					liveOrderTime =new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Double liveOrderTotal = null;
				try {
					liveOrderTotal = new Double(req.getParameter("liveOrderTotal").trim());
				}catch (NumberFormatException e) {
					liveOrderTotal = 0.0;
					errorMsgs.add("訂單總價請填數字.");
				}
				
				Integer liveOrderPayment = null;
				try {
					liveOrderPayment = new Integer(req.getParameter("liveOrderPayment").trim());
				}catch(NumberFormatException e) {
					liveOrderPayment = 0;
					errorMsgs.add("現場訂單付款狀態請填數字.");
				}
				
				Integer liveOrderStatus = null;
				try {
					liveOrderStatus = new Integer(req.getParameter("liveOrderStatus").trim());
				}catch(NumberFormatException e) {
					liveOrderStatus = 0;
					errorMsgs.add("現場訂單狀態請填數字.");
				}
				
				String liveOrderno = req.getParameter("liveOrderno").trim();
				
				LiveOrderVO loVO = new LiveOrderVO();
				loVO.setEmpno(empno);
				loVO.setTableno(tableno);
				loVO.setLiveOrderTime(liveOrderTime);
				loVO.setLiveOrderTotal(liveOrderTotal);
				loVO.setLiveOrderPayment(liveOrderPayment);
				loVO.setLiveOrderStatus(liveOrderStatus);
				loVO.setLiveOrderno(liveOrderno);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("loVO", loVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/liveOrder/update_liveOrder_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				LiveOrderService loSvc = new LiveOrderService();
				loSvc.updateLiveOrder(empno, tableno, liveOrderTime, liveOrderTotal, liveOrderPayment, liveOrderStatus, liveOrderno);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("loVO", loVO); // 資料庫update成功後,正確的的loVO物件,存入req
				String url = "/back-end/liveOrder/listOneLiveOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/	
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/liveOrder/update_liveOrder_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) {  // 來自addLiveOrder.jsp的請求  
			 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String empno = req.getParameter("empno").trim(); //FK
				
				String tableno = req.getParameter("tableno").trim(); //FK
				
				Timestamp liveOrderTime = null;
				try {
					liveOrderTime = Timestamp.valueOf(req.getParameter("liveOrderTime").trim());
				}catch(IllegalArgumentException e) {
					liveOrderTime =new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Double liveOrderTotal = null;
				try {
					liveOrderTotal = new Double(req.getParameter("liveOrderTotal").trim());
				}catch (NumberFormatException e) {
					liveOrderTotal = 0.0;
					errorMsgs.add("訂單總價請填數字.");
				}
				
				Integer liveOrderPayment = null;
				try {
					liveOrderPayment = new Integer(req.getParameter("liveOrderPayment").trim());
				}catch(NumberFormatException e) {
					liveOrderPayment = 0;
					errorMsgs.add("現場訂單付款狀態請填數字.");
				}
				
				Integer liveOrderStatus = null;
				try {
					liveOrderStatus = new Integer(req.getParameter("liveOrderStatus").trim());
				}catch(NumberFormatException e) {
					liveOrderStatus = 0;
					errorMsgs.add("現場訂單狀態請填數字.");
				}

				
				LiveOrderVO loVO = new LiveOrderVO();
				loVO.setEmpno(empno);
				loVO.setTableno(tableno);
				loVO.setLiveOrderTime(liveOrderTime);
				loVO.setLiveOrderTotal(liveOrderTotal);
				loVO.setLiveOrderPayment(liveOrderPayment);
				loVO.setLiveOrderStatus(liveOrderStatus);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("loVO", loVO); // 含有輸入格式錯誤的loVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/liveOrder/addLiveOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				LiveOrderService loSvc = new LiveOrderService();
				loSvc.addLiveOrder(empno, tableno, liveOrderTime, liveOrderTotal, liveOrderPayment, liveOrderStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/liveOrder/listAllLiveOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/liveOrder/addLiveOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)) { // 來自listAllLiveOrder.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String liveOrderno = req.getParameter("liveOrderno");
				
				/***************************2.開始刪除資料***************************************/
				LiveOrderService loSvc = new LiveOrderService();
				loSvc.deleteLiveOrder(liveOrderno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/liveOrder/listAllLiveOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
				
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/liveOrder/listAllLiveOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if("listLiveOrder_ByCompositeQuery".equals(action)) {  // 來自select_page.jsp的複合查詢請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				
				LiveOrderService loSvc = new LiveOrderService();
				List<LiveOrderVO> list = loSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listLiveOrder_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/liveOrder/listLiveOrder_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/liveOrder/select_page.jsp");
				failureView.forward(req, res);
			}	
		}
	}
}
