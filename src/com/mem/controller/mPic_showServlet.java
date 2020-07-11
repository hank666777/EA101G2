package com.mem.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.mem.model.MemService;
import com.mem.model.MemVO;

public class mPic_showServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/png");
		ServletOutputStream out = res.getOutputStream();

		try {
			String memno = req.getParameter("memno");
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(memno);
			byte[] b = memVO.getmPic();
			out.write(b);
		} catch (Exception e) {
			System.out.println("MPICServlet: " + e.getMessage());
		}
	}
}
