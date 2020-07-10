package com.image.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycoupon.model.MyCpService;



@WebServlet("/mycoupon/imgs")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("image/gif");
			String couponno = request.getParameter("couponno");
			
			//find a couponPic of coupon for couponno
			MyCpService myCpService = new MyCpService();	
			byte[] pic = myCpService.getPic(couponno);
			
			ServletOutputStream os = response.getOutputStream();
			BufferedInputStream bf= new BufferedInputStream(new ByteArrayInputStream(pic));
				
			byte[] buf = new byte[1024];
			int len ;
				while((len=bf.read(buf))!=-1) {
					os.write(buf, 0, len);
					os.flush();
				}
					bf.close();
			}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
