package com.administrator.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@MultipartConfig
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset=UTF-8");
		String action = req.getParameter("action");
		
		if ("changeValue".equals(action)) { 
			try {
				String url = "/back-end/liveShop/EShop.jsp";
				String inCludeVO=req.getParameter("inCludeVO");
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				  switch(inCludeVO) { 
		            case "drink": 
		            	req.setAttribute("inCludeVO", "drink");
						successView.forward(req, res);
		                break; 
		            case "dessert": 
		            	req.setAttribute("inCludeVO", "dessert"); 
						successView.forward(req, res);
		                break; 
		            case "lightfood": 
		            	req.setAttribute("inCludeVO", "lightfood"); 
						successView.forward(req, res);
		                break; 
		            case "soup": 
		            	req.setAttribute("inCludeVO", "soup");
						successView.forward(req, res);
		                break; 
		            case "jam": 
		            	req.setAttribute("inCludeVO", "jam"); 
						successView.forward(req, res);
		                break;
		            default: 
		                System.out.println("include有問題!!"); 
		        }
				  
			} 
			catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/liveShop/EShop.jsp");
				failureView.forward(req, res);

			}

		}
	}

}
