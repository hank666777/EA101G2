package com.mem.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

public class mPic_showServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			String memno = req.getParameter("memno");
			MemVO memVO = new MemService().getOneMem(memno);
			byte[] buf = memVO.getmPic();
			out.write(buf);
		}catch(Exception e) {
			System.out.println("mPic_showServlet" + e.getMessage());
		}
			
		
	}
}

