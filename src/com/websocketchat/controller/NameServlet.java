package com.websocketchat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeVO;
import com.mem.model.MemVO;

public class NameServlet extends HttpServlet {

  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	HttpSession session = req.getSession();
	
	String userName = req.getParameter("userName");

	EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
	String eName = employeeVO.geteName();

	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mName = memVO.getmName();

	req.getRequestURI();
	if (userName.equals(eName)) {
	  
	  req.setAttribute("userName", eName);
	  RequestDispatcher dispatcher = req.getRequestDispatcher("/back-end/chat/employee_chat.jsp");
	  dispatcher.forward(req, res);
	  
	} else if (userName.equals(mName)) {
	  
	  req.setAttribute("userName", mName);
	  RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/chat/member_chat.jsp");
	  dispatcher.forward(req, res);
	  
	}

//	String userName = req.getParameter("userName");
//
//	req.setAttribute("userName", userName);
//
//	RequestDispatcher dispatcher = req.getRequestDispatcher("/chat.jsp");
//	dispatcher.forward(req, res);
  }
}
