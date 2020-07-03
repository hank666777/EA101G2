package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductVO;

@MultipartConfig
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		// res.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = res.getWriter();

		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");// 區域變數，也可放實體變數
		String action = req.getParameter("action");

		if (!action.equals("CHECKOUT")) { // 來自來自EShop.jsp的請求

			// 刪除購物車中的商品
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			// 新增書品至購物車中
			else if (action.equals("ADD")) {

				// 取得後來新增的商品
				ProductVO pd = getProduct(req);
				// 新增第一個商品到購物車時
				if (buylist == null) {
					buylist = new Vector<ProductVO>();
					buylist.add(pd);
				} else {
					// 假若新增的商品和購物車一樣時
					if (buylist.contains(pd)) {
						ProductVO innerPd = buylist.get(buylist.indexOf(pd));// product是購物車裡的數量,pd是選擇加入的數量
						innerPd.setpDoffer(innerPd.getpDoffer() + pd.getpDoffer());
					} else {// end of if
							// 假若新增的商品和購物車不一樣時
						buylist.add(pd);
					}
				}
			}
			session.setAttribute("shoppingcart", buylist);
			String url = "/back-end/product/EShop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 結帳，計算購物車商品價錢總價
		else if (action.equals("CHECKOUT")) {
			Double total = 0.0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				Integer price = order.getpP();
				Integer quantity = order.getpDoffer();
				total += (price * quantity);
			}
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/back-end/product/CheckOut.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private ProductVO getProduct(HttpServletRequest req) {

		String pname = req.getParameter("pname");
		String pP = req.getParameter("pP");
		String pDes = req.getParameter("pDes");
		String pDoffer = req.getParameter("pDoffer");

		ProductVO pd = new ProductVO();
		pd.setpname(pname);
		pd.setpP((new Integer(pP)).intValue());
		pd.setpDes(pDes);
		pd.setpDoffer((new Integer(pDoffer)).intValue());

		return pd;
	}
}
