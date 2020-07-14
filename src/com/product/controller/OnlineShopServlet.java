package com.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coupon.model.CouponVO;
import com.coupon.model.CpService;
import com.mycoupon.model.MyCouponVO;
import com.mycoupon.model.MyCpService;
import com.onodetail.model.ONODetailService;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@MultipartConfig
public class OnlineShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");// 區域變數，也可放實體變數
		
		String action = req.getParameter("action");
		
		//------------------------------新增物品至購物車中-------------------------------------------
		if("ADD".equals(action)) {
			String requestURL = req.getParameter("requestURL");// 送出新增商品的來源網頁路徑
			String pno = req.getParameter("pno");
			
			// 取得後來新增的商品
			ProductVO pd = getProduct(req);
			// 新增第一個商品到購物車時
			if (buylist == null) {
				buylist = new Vector<ProductVO>();
				buylist.add(pd);
			} else {
				// 假若新增的商品和購物車一樣時
				if (buylist.contains(pd)) {
					ProductVO innerPd = buylist.get(buylist.indexOf(pd));// innerPd是購物車裡的數量,pd是選擇加入的數量
					innerPd.setpDoffer(innerPd.getpDoffer() + pd.getpDoffer());
				} else if(!buylist.contains(pd)){// end of if
						// 假若新增的商品和購物車不一樣時
					buylist.add(pd);
			    }

		      }  
			
			ProductService pdSVC = new ProductService();
			ProductVO pdVO = pdSVC.getOneProduct(pno);
			
			ONODetailService onSvc = new ONODetailService();
			if(requestURL.equals("/front-end/onlineShop/OShop.jsp")) 
				req.setAttribute("OShop", onSvc.getProductBypno(pdVO.getpno()));
				
				session.setAttribute("shoppingcart", buylist);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交回送出新增的來源網頁
				successView.forward(req, res);
			
				
			/*************************** 3.完成,準備轉交(Send the Success view) ***********/
//			session.setAttribute("shoppingcart", buylist);
//			String url = "/front-end/onlineShop/OShop.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
			return;	
		 }
		
		// ----------------------更改購物車的數量-------------------------------------------
		
		if("QTY".equals(action)) {
			System.out.println("更改購物車的數量");
			String qty = req.getParameter("qty");
			System.out.println(qty);
			int d = Integer.parseInt(qty);
			String pDoffer = req.getParameter("pDoffer");
			System.out.println(pDoffer);
			int y=Integer.parseInt(pDoffer);
			buylist.get(d).setpDoffer(y);
			System.out.println(buylist);
			
			String url = "/front-end/onlineShop/OCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;	
			
		}
		
		
		
		// ----------------------查詢商品-------------------------------------------
		
		if("listProduct_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
			/**1.將輸入資料轉為Map**/
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());	
					req.setAttribute("map", map1);
					map = map1;
				}
			/**2.開始複合查詢**/	
				ProductService productSvc = new ProductService();
				List<ProductVO> list = productSvc.getAll(map);
				
			/**3.查詢完成,準備轉交(Send the Success view)**/
				req.setAttribute("listProduct_ByCompositeQuery", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/onlineShop/OShop.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/onlineShop/OShop.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		// ----------------------查詢商品2_AJAX-------------------------------------------
		
		if("listProduct_ByCompositeQuery_ajax".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/**1.將輸入資料轉為Map**/
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());	
					req.setAttribute("map", map1);
					map = map1;
				}
				/**2.開始複合查詢**/	
				ProductService productSvc = new ProductService();
				List<ProductVO> list = productSvc.getAll(map);
				
				/**3.查詢完成,準備轉交(Send the Success view)**/
				req.setAttribute("listProduct_ByCompositeQuery_ajax", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/onlineShop/oshop_ajax.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/onlineShop/oshop_ajax.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		
		
		// ----------------------刪除購物車中的書籍-------------------------------------------
		
		if (action.equals("DELETE")) {
			String del = req.getParameter("del");
			int d = Integer.parseInt(del);
			buylist.remove(d);
			
			String url = "/front-end/onlineShop/OCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;	
	
		}			
		
		
		//---------------------確認訂單，計算購物車總價錢-----------------------------------------
		
		if (action.equals("CHECKOUT")) {
			Double total = 0.0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				Integer price = order.getpP();
				Integer quantity = order.getpDoffer();
				total += (price * quantity);
			}
			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);
			String url = "/front-end/onlineShop/OCheckOut.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		if(action.equals("payPage")) {
			Double total = 0.0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				Integer price = order.getpP();
				Integer quantity = order.getpDoffer();
				total += (price * quantity);
			}
			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);
			
			String url = "/front-end/onlineShop/payMoney.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		if(action.equals("paypay")) {
			 if(req.getParameter("myCoupon").equals("no")) {
			  String amount = (String)session.getAttribute("amount");
			  res.getWriter().write(amount);
			 }else {
			 String couponsno = req.getParameter("myCoupon");
			 CpService CpService = new CpService();
			 Integer disCount = CpService.getOne3(couponsno);
			 String amount = (String)session.getAttribute("amount");
			 Double amount2 = Double.valueOf(amount);
			 Double dis=disCount*amount2/10;
			 res.getWriter().write(String.valueOf(dis));
			 }
		}
	}
			
	private ProductVO getProduct(HttpServletRequest req) {

		String pname = req.getParameter("pname");
		String pP = req.getParameter("pP");
		String pDes = req.getParameter("pDes");
		String pDoffer = req.getParameter("pDoffer");
		String pno = req.getParameter("pno");

		ProductVO pd = new ProductVO();
		pd.setpname(pname);
		pd.setpP((new Integer(pP)).intValue());
		pd.setpDes(pDes);
		pd.setpDoffer((new Integer(pDoffer)).intValue());
		pd.setpno(pno);

		return pd;
	}
}
