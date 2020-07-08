package com.mycoupon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycoupon.model.MyCouponVO;
import com.mycoupon.model.MyCpService;

@WebServlet("/mycoupon/mycoupon.do")
public class MycpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MycpServlet() {
        super();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
		String memno = req.getParameter("memno");	
		String couponno = req.getParameter("couponName");
		//insert one coupon of member
		MyCpService myCpService = new MyCpService();
		myCpService.add(couponno, memno);
		List<MyCouponVO> myCoupon = myCpService.getMyCoupon(memno);
		
		req.setAttribute("myCoupon", myCoupon);
		req.getRequestDispatcher("/front-end/myCoupon/listMyCoupon.jsp").forward(req,res);
		//to find coupons of member
		}
		else {
		String memno=null;	
		HttpSession session = req.getSession();	
		if(session!=null) {
		memno= (String) session.getAttribute("memno");}
		memno = req.getParameter("memno");
		MyCpService myCpService = new MyCpService();
		List<MyCouponVO> myCoupon = myCpService.getMyCoupon(memno);
		
		session.setAttribute("myCoupon", myCoupon);
		req.getRequestDispatcher("/front-end/myCoupon/listMyCoupon.jsp").forward(req,res);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
