package com.employee.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

public class EPicShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EPicShowServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/png");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			String empno = req.getParameter("empno");
			EmployeeService empSvc = new EmployeeService();
			EmployeeVO empVO = empSvc.getOneEmployee(empno);
			byte[] b = empVO.getePic();
			out.write(b);
		}catch(Exception e) {
			System.out.println("EPICServlet: "+ e.getMessage());
		}
		
	}

}
