package com.activity.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

public class ActivitypicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			String actno = req.getParameter("actno");
			ActivityService actSvc = new ActivityService();
			ActivityVO AVO = actSvc.getactinity(actno);
			byte[] buf = AVO.getActPic();
			out.write(buf);
		}catch(Exception e) {
			System.out.println(e);
		}
			
		
	}


}
