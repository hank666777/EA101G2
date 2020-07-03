package com.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmployeeLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeLogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("empVO");
		session.removeAttribute("perVOlist");
		session.invalidate();
		
		res.sendRedirect(req.getContextPath() + "/backlogin.jsp");
	}

}
