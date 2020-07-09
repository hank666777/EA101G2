package com.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tabless.model.TableService;
import com.tabless.model.TableVO;

@MultipartConfig
public class TablenoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		// res.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		HttpSession session = req.getSession(); 
		if (action.equals("confirm")) {

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String tableno = req.getParameter("tableno");// 取得桌號
				/*************************** 2.開始查詢資料 ****************************************/
				TableService tableSvc = new TableService();
				TableVO tbVO = tableSvc.getOne(tableno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				session.setAttribute("tbVO", tbVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/liveShop/EShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				
			}

		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
