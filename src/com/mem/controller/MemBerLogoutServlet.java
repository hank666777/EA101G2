package com.mem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemBerLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemBerLogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		session.removeAttribute("MemVO");
		session.invalidate();
		
		res.sendRedirect(req.getContextPath() + "/front-end/index.jsp");
	}

}
