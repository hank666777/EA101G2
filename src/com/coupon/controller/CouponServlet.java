package com.coupon.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import com.utility.tool.JDBCUtilites;
import com.coupon.model.CouponVO;
import com.coupon.model.CpService;
import javax.servlet.http.*;

@MultipartConfig
public class CouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doPost(req,res);

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
				
			/*********insert************/
			if("insert".equals(req.getParameter("action"))) {
				
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);	
			try {
			/*********receive parameter & error verification************/
			
			
			String cname = req.getParameter("cname");
			String cnameReg = "^[(\u4e00-\u9fa5)(0-9)]{2,10}$";
			//error messages for property coupon_name 
			if (cname == null || cname.trim().length() == 0) {
				errorMsgs.put("cname","名稱欄位請勿空白");
			}else if(!cname.trim().matches(cnameReg)) { 
				errorMsgs.put("cname","名稱欄位請輸入正確格式(中文2-10字)");
             }
		
			String count = req.getParameter("count");
			String countReg = "[0-9]{1}";
			//error messages for property coupon_discount 
			if (count == null || count.trim().length() == 0) {
				errorMsgs.put("count","折扣欄位請勿空白");
			}else if(!count.trim().matches(countReg)) {
				errorMsgs.put("count","折扣欄位請輸入正確格式(0-9的數字)");
			 }
			 
			Part part = req.getPart("upfile");
			//error messages for property coupon_pic
			if (part.getSize() == 0) {
				errorMsgs.put("part", "圖片請勿空白");
			}
			
			//if it has any error=>forward 
			if (!errorMsgs.isEmpty()) {
			req.getRequestDispatcher("/back-end/coupon/addCoupon.jsp").forward(req, res);
			return;
			}
			
			/*********************select & storage**********************/
			CpService CpService = new CpService();
			InputStream is = part.getInputStream();
			byte[] byteArray = JDBCUtilites.toByteArray(is);
			Integer intCount = Integer.valueOf(count);
			CpService.add(cname, intCount, byteArray);
			
			/********************send the success view******************/
			String url = "/back-end/coupon/listAllCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);				
			}
			/********************the other error******************/
			catch (Exception e) {
			errorMsgs.put("Exception",e.getMessage());
			req.getRequestDispatcher("/back-end/coupon/addCoupon.jsp").forward(req, res);
			e.getStackTrace();
			}
		}
			/*********get one couponvo for update************/
			if("getOneForUpdate".equals(req.getParameter("action"))){
				
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
			try {
				String coupon = req.getParameter("coupon");
				CpService CpService = new CpService();
				CouponVO cpv = CpService.getOne(coupon);
				req.setAttribute("couponVO", cpv);
				req.getRequestDispatcher("/back-end/coupon/updateCoupon.jsp").forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
				}
			}
			
			/*********update************/
			if("update".equals(req.getParameter("action"))) {
				
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);	
			try {
			/*********receive parameter & error verification************/
			
			
			String cname = req.getParameter("cname");
			String cnameReg = "^[(\u4e00-\u9fa5)(0-9)]{2,10}$";
			//error messages for property coupon_name 
			if (cname == null || cname.trim().length() == 0) {
				errorMsgs.put("cname","名稱欄位請勿空白");
			}else if(!cname.trim().matches(cnameReg)) { 
				errorMsgs.put("cname","名稱欄位請輸入正確格式(中文2-10字)");
             }
		
			String count = req.getParameter("count");
			String countReg = "[0-9]{1}";
			//error messages for property coupon_discount 
			if (count == null || count.trim().length() == 0) {
				errorMsgs.put("count","折扣欄位請勿空白");
			}else if(!count.trim().matches(countReg)) {
				errorMsgs.put("count","折扣欄位請輸入正確格式(0-9的數字)");
			 }
			 
			CpService CpService = new CpService();
			//if it has any error=>forward 
			
			
			if (!errorMsgs.isEmpty()) {
			String coupon = req.getParameter("couponno");			
			CouponVO cpv = CpService.getOne(coupon);
			req.setAttribute("couponVO", cpv);	
			req.getRequestDispatcher("/back-end/coupon/updateCoupon.jsp").forward(req, res);
			return;
			}
			
			/*********************select & storage**********************/
			
			Part part = req.getPart("upfile");
			Integer intCount = Integer.valueOf(count);
			String couponno = req.getParameter("couponno").trim();
			if(part.getSize()==0) {		
				CpService.update(couponno,cname, intCount);
			}else {
				InputStream is = part.getInputStream();
				byte[] byteArray = JDBCUtilites.toByteArray(is);
				CpService.update(couponno,cname, intCount, byteArray);
			}
					
			/********************send the success view******************/
			String coupon = req.getParameter("couponno");			
			CouponVO cpv = CpService.getOne(coupon);
			req.setAttribute("couponVO", cpv);	
			String url = "/back-end/coupon/selectOneCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);				
			}
			/********************the other error******************/
			catch (Exception e) {
			errorMsgs.put("Exception",e.getMessage());
			req.getRequestDispatcher("/back-end/coupon/updateCoupon.jsp").forward(req, res);
			e.getStackTrace();
			}
		}
	}
	
}
