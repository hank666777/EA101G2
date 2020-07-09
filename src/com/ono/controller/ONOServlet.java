package com.ono.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.product.model.ProductVO;
import com.mem.model.MemVO;
import com.mycoupon.model.MyCpService;
import com.ono.model.ONOService;
import com.ono.model.ONOVO;
import com.onodetail.model.ONODetailVO;

@MultipartConfig
public class ONOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	} 
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
		if("Detail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);	
			
			MemVO memVO = (MemVO) session.getAttribute("memVO");//取得會員物件
			if(memVO == null) {
				String location=req.getContextPath()+"/front-end/onlineShop/OCheckOut.jsp";
				req.setAttribute("location", location);
				res.sendRedirect(req.getContextPath()+"/front-end/index.jsp");
				return;
			}
			
			String memno = memVO.getMemno();
			
			
			String couponSno = null;
			try {
				couponSno = req.getParameter("myCoupon").trim();
				System.out.println(couponSno);
			}catch (NumberFormatException e) {
				couponSno = null;
				errorMsgs.add("線上訂單總價請填數字.");
			}
			MyCpService mcp = new MyCpService();
			mcp.update(couponSno);
			
			Timestamp onoTime = new Timestamp(System.currentTimeMillis());
			
			Integer onoTotalxx = Integer.valueOf(req.getParameter("onoTotal"));
			Integer onoTotal = onoTotalxx.intValue();
			System.out.println(onoTotal);
			
			Integer onoStatus = 1;
			Integer onoPay = 1;
			
			ONOVO onVO = new ONOVO();
			onVO.setmemno(memno);
			onVO.setcouponSno(couponSno);
			onVO.setonoTime(onoTime);
			onVO.setonoTotal(onoTotal);
			onVO.setonoStatus(onoStatus);
			onVO.setonoPay(onoPay);
			
			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
			List<ONODetailVO> list = new ArrayList();
			
			for(ProductVO pdVO : buylist) {
				ONODetailVO ondVO = new ONODetailVO();
				ondVO.setonono(onVO.getonono());
				ondVO.setpno(pdVO.getpno());
				ondVO.setonoQty(pdVO.getpDoffer());
				ondVO.setonoPrice(pdVO.getpP());
				list.add(ondVO);
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("onVO", onVO); // 含有輸入格式錯誤的member物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("front-end/onlineShop/CheckOut.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始查詢資料 *****************************************/
			ONOService onSvc = new ONOService();
			ONOVO onVO2 = onSvc.insertWithONODetail(onVO, list);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("onVO2", onVO2);
			String url = "/front-end/ono/listAllONO.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;// 程式中斷	
		}
		
		if("getOne_For_Display".equals(action)) {  // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.接收請求參數****************************************/
				String str = req.getParameter("onono");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入現場訂單編號");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ono/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String onono = null;
				try {
					onono = str;
				}catch(Exception e) {
					errorMsgs.add("現場訂單編號格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ono/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}				
				
				/***************************2.開始查詢資料****************************************/
				ONOService onSvc = new ONOService();
				ONOVO onVO = onSvc.getOneProduct(onono);
				if(onVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ono/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
						
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				
				req.setAttribute("onVO", onVO);
				String url = "/front-end/ono/listOneONO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ono/select_page.jsp");
				failureView.forward(req, res);
			}		
		}
		
		if("getOne_For_Update".equals(action)) { // 來自update_ono_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String onono = req.getParameter("onono");
				
				/***************************2.開始查詢資料****************************************/
				ONOService onSvc = new ONOService();
				ONOVO onVO = onSvc.getOneProduct(onono);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("onVO", onVO);
				String url = "/front-end/ono/update_ono_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ono/listAllONO.jsp");
				failureView.forward(req, res);
			}				
		}
		
		if("update".equals(action)) { // 來自update_ono_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String memno = req.getParameter("memno").trim();
				
				String couponSno = req.getParameter("couponSno").trim();
				
				Timestamp onoTime = null;
				try {
					onoTime = Timestamp.valueOf(req.getParameter("onoTime").trim());
				}catch(IllegalArgumentException e) {
					onoTime =new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer onoTotal = null;
				try {
					onoTotal = new Integer(req.getParameter("onoTotal").trim());
				}catch (NumberFormatException e) {
					onoTotal = 0;
					errorMsgs.add("線上訂單總價請填數字.");
				}
				
				Integer onoStatus = null;
				try {
					onoStatus = new Integer(req.getParameter("onoStatus").trim());
				}catch(NumberFormatException e) {
					onoStatus = 0;
					errorMsgs.add("線上訂單付款狀態請填數字.");
				}

				Integer onoPay = null;
				try {
					onoPay = new Integer(req.getParameter("onoPay").trim());
				}catch(NumberFormatException e) {
					onoPay = 0;
					errorMsgs.add("線上訂單付款狀態請填數字.");
				}
				
				String onono = req.getParameter("onono").trim();
				
				ONOVO onVO = new ONOVO();
				onVO.setmemno(memno);
				onVO.setcouponSno(couponSno);
				onVO.setonoTime(onoTime);
				onVO.setonoTotal(onoTotal);
				onVO.setonoStatus(onoStatus);
				onVO.setonoPay(onoPay);
				onVO.setonono(onono);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("onVO", onVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ono/update_ono_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ONOService onSvc = new ONOService();
				onSvc.updateProduct(memno, couponSno, onoTime, onoTotal, onoStatus, onoPay, onono);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("onVO", onVO); // 資料庫update成功後,正確的的onVO物件,存入req
				String url = "/front-end/ono/listOneONO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ono/update_ono_input.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if("insert".equals(action)) {   // 來自addONO.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String memno = req.getParameter("memno").trim(); //FK
				
				String couponSno = req.getParameter("couponSno").trim(); //FK
				
				Timestamp onoTime = null;
				try {
					onoTime = Timestamp.valueOf(req.getParameter("onoTime").trim());
				}catch(IllegalArgumentException e) {
					onoTime =new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer onoTotal = null;
				try {
					onoTotal = new Integer(req.getParameter("onoTotal").trim());
				}catch (NumberFormatException e) {
					onoTotal = 0;
					errorMsgs.add("訂單總價請填數字.");
				}
				
				Integer onoStatus = null;
				try {
					onoStatus = new Integer(req.getParameter("onoStatus").trim());
				}catch(NumberFormatException e) {
					onoStatus = 0;
					errorMsgs.add("現場訂單付款狀態請填數字.");
				}

				Integer onoPay = null;
				try {
					onoPay = new Integer(req.getParameter("onoPay").trim());
				}catch(NumberFormatException e) {
					onoPay = 0;
					errorMsgs.add("現場訂單付款狀態請填數字.");
				}
				
				ONOVO onVO = new ONOVO();
				onVO.setmemno(memno);
				onVO.setcouponSno(couponSno);
				onVO.setonoTime(onoTime);
				onVO.setonoTotal(onoTotal);
				onVO.setonoStatus(onoStatus);
				onVO.setonoPay(onoPay);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("onVO", onVO); // 含有輸入格式錯誤的loVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ono/addONO.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ONOService onSvc = new ONOService();
				onSvc.addONO(memno, couponSno, onoTime, onoTotal, onoStatus, onoPay);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/ono/listAllONO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ono/addONO.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if("delete".equals(action)) {  // 來自listAllONO.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String onono = req.getParameter("onono").trim();
				
				/***************************2.開始刪除資料***************************************/
				ONOService onSvc = new ONOService();
				onSvc.deleteProduct(onono);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/ono/listAllONO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ono/listAllONO.jsp");
				failureView.forward(req, res);
			}				
		}					
	}
}
