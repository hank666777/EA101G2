package com.bookingdetail.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.booking.model.*;
import com.bookingdetail.model.*;

@WebServlet("/BokdtServlet")
public class BokdtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("listBokDetailByCompositeQuery".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 *************************/
				Map<String, String[]> map = new HashMap<String, String[]>(req.getParameterMap());
				HttpSession session = req.getSession();
				session.setAttribute("BokDetailMap",map);
				
				/***************************2.開始處理資料**********************/
				BokdtService bokdtSvc = new BokdtService();
				List<BokdtVO> list = bokdtSvc.getAll(map);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/bok/failureView.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
								
				/***************************3.完成,準備轉交(Send the Success view)***********/
				req.setAttribute("BokDetailList", list);
				String url = "/back-end/bok/liveOrderMultiQuery2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/bok/failureView.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}

}
