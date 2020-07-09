package com.product.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@MultipartConfig
public class DBGifReaderProduct extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String pno = req.getParameter("pno");
			ProductService pdSvc = new ProductService();
			ProductVO pdVO = pdSvc.getOneProduct(pno);
			byte[] buf = pdVO.getpPic();
			out.write(buf);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}