package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 5 * 1024 * 1024)
public class SungProductServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("get_For_Product".equals(action)) {// 來自selectGoods_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// 設置一個連結集合,錯誤處理用的
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("pno");
				if(str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/liveShop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷		
				}
				
				String pno = null;
				try {
					pno = str;
					
				}catch(Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/liveShop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
			
				/***************************2.開始查詢資料*****************************************/
				ProductService pdSvc = new ProductService();			
				ProductVO pdVO = pdSvc.getOneProduct(pno);
				System.out.println(pdVO);
				if(pdVO == null) {   
					errorMsgs.add("查無資料");			
				}
				
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/liveShop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pdVO", pdVO);// 資料庫取出的empVO物件,存入req
				String url = "/back-end/liveShop/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/liveShop/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("get_For_Update".equals(action)){  // 來自listAllProduct.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// 設置一個連結集合,錯誤處理用的
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String pno = req.getParameter("pno");
				
				/***************************2.開始查詢資料****************************************/
				ProductService pdSvc = new ProductService();
				ProductVO pdVO = pdSvc.getOneProduct(pno);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pdVO", pdVO);       // 資料庫取出的empVO物件,存入req
				String url = "/back-end/liveShop/update_product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/liveShop/listAllProduct.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
		if("update".equals(action)) { // 來自update_product.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// 設置一個連結集合,錯誤處理用的
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String pname = req.getParameter("pname");
				String pnameReg = "^[(\\u4e00-\\u9fa5)]{2,10}$";
				if(pname == null || pname.trim().length() == 0) {
					errorMsgs.add("商品名稱 : 請勿空白");
				}else if(!pname.trim().matches(pnameReg)) { //以下為正則(規)表示式驗證
					errorMsgs.add("商品名稱 : 只能是中 , 且長度必需在2到10之間");
				}
				
				Integer pP = null;
				try {
					pP = new Integer(req.getParameter("pP").trim());
				}catch (NumberFormatException e) {
					pP = 0;
					errorMsgs.add("價格請填數字.");
				}
				
				Part pPic = req.getPart("pPic");
				InputStream in = pPic.getInputStream();
				byte[] buf = new byte[in.available()];
				if(in.available() == 0) {
					errorMsgs.add("請上傳商品圖片");
				}else{
					in.read(buf);
					in.close();
				}
				
				String pDes = req.getParameter("pDes").trim();
				String pDesReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,}$";
				if(pDes == null || pDes.trim().length() == 0) {  
					errorMsgs.add("商品敘述請勿空白");
				}else if(!pDes.trim().matches(pDesReg)) {
					errorMsgs.add("商品敘述 : 只能是中、英文字母、數字和_ , 且長度至少要10以上");
				}
				
				Integer pDoffer = null;
				try {
					pDoffer = new Integer(req.getParameter("pDoffer").trim());
				}catch(NumberFormatException e) {
					pDoffer = 0;
					errorMsgs.add("請輸入商品庫存量");
				}
				
				Integer invStatus = null;
				try {
					invStatus = new Integer(req.getParameter("invStatus").trim());
				}catch (NumberFormatException e) {
					invStatus = 0; 
					errorMsgs.add("不正確的商品庫存狀態");
				}
				
				Integer pStatus = null;
				try {
					pStatus = new Integer(req.getParameter("pStatus").trim());
				}catch (NumberFormatException e) {
					pStatus = 0 ;
					errorMsgs.add("不正確的商品狀態");
				}
				
				String pTno = req.getParameter("pTno").trim();
				
				String pno = req.getParameter("pno").trim();
				
				ProductVO pdVO = new ProductVO();
				pdVO.setpname(pname);
				pdVO.setpP(pP);
				pdVO.setpPic(buf);
				pdVO.setpDes(pDes);
				pdVO.setpDoffer(pDoffer);
				pdVO.setINVStatus(invStatus);
				pdVO.setpStatus(pStatus);
				pdVO.setpTno(pTno);
				pdVO.setpno(pno);
				
				// Send the use back to the form, if there were errors
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pdVO", pdVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/liveShop/update_product.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				ProductService pdSvc = new ProductService();
				pdSvc.updateProduct(pname, pP, buf, pDes, pDoffer, invStatus, pStatus, pTno, pno);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pdVO", pdVO);// 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/liveShop/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);;		
				
				/***************************其他可能的錯誤處理*************************************/	
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/liveShop/update_product.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if("insert".equals(action)) { // 來自add_product.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// 設置一個連結集合,錯誤處理用的
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String pname = req.getParameter("pname");
				String pnameReg = "^[(\\u4e00-\\u9fa5)]{2,10}$";
				if(pname == null || pname.trim().length() == 0) {
					errorMsgs.add("商品名稱 : 請勿空白");
				}else if(!pname.trim().matches(pnameReg)) { //以下為正則(規)表示式驗證
					errorMsgs.add("商品名稱 : 只能是中 , 且長度必需在2到10之間");
				}
				
				Integer pP = null;
				try {
					pP = new Integer(req.getParameter("pP").trim());
				}catch (NumberFormatException e) {
					pP = 0;
					errorMsgs.add("價格請填數字.");
				}
				
				Part pPic = req.getPart("pPic");
				InputStream in = pPic.getInputStream();
				byte[] buf = new byte[in.available()];
				if(in.available() == 0) {
					errorMsgs.add("請上傳商品圖片");
				}
				in.read(buf);
				in.close();
				
				String pDes = req.getParameter("pDes").trim();
				String pDesReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$";
				if(pDes == null || pDes.trim().length() == 0) {  
					errorMsgs.add("商品敘述請勿空白");
				}else if(!pDes.trim().matches(pDesReg)) {
					errorMsgs.add("商品敘述 : 只能是中、英文字母、數字和_ , 且長度必需在2到200之間");
				}
				
				Integer pDoffer = null;
				try {
					pDoffer = new Integer(req.getParameter("pDoffer").trim());
				}catch(NumberFormatException e) {
					pDoffer = 0;
					errorMsgs.add("請輸入商品庫存量");
				}
				
				Integer invStatus = null;
				try {
					invStatus = new Integer(req.getParameter("invStatus").trim());
				}catch (NumberFormatException e) {
					invStatus = 0;
					errorMsgs.add("請輸入商品庫存狀態");
				}
				
				Integer pStatus = null;
				try {
					pStatus = new Integer(req.getParameter("pStatus").trim());
				}catch (NumberFormatException e) {
					pStatus = 0;
					errorMsgs.add("請輸入商品狀態");
				}
				
				String pTno = req.getParameter("pTno"); 
				
				ProductVO pdVO = new ProductVO();
				pdVO.setpname(pname);
				pdVO.setpP(pP);
				pdVO.setpPic(buf);
				pdVO.setpDes(pDes);
				pdVO.setpDoffer(pDoffer);
				pdVO.setINVStatus(invStatus);
				pdVO.setpStatus(pStatus);
				pdVO.setpTno(pTno);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pdVO", pdVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/liveShop/add_Product.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductService pdSvc = new ProductService();
				pdSvc.addProduct(pname, pP, buf, pDes, pDoffer, invStatus, pStatus, pTno);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/liveShop/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/		
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/liveShop/add_Product.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
		if("delete".equals(action)) {  // 來自listAllProduct.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// 設置一個連結集合,錯誤處理用的
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String pno = req.getParameter("pno");
				
				/***************************2.開始刪除資料***************************************/
				ProductService pdSvc = new ProductService();
				pdSvc.deleteProduct(pno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/liveShop/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/		
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/liveShop/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
