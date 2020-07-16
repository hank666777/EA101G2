package com.activitypost.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.ActivityJNDIDAO;
import com.activity.model.ActivityVO;
import com.activitypost.model.ActivitypostService;
import com.activitypost.model.ActivitypostVO;

@MultipartConfig
public class ActivitypostpicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			String actPostno = req.getParameter("actPostno");
			ActivitypostService actSvc = new ActivitypostService();
			ActivitypostVO ACPVO = actSvc.getActivitypost(actPostno);
			byte[] buf = ACPVO.getActPostPic();
			out.write(buf);
		}catch(Exception e) {
			System.out.println(e);
		}
			
		
	}


	
	
}
