package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liveOrderDetail.model.LiveOrderDetailService;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.tabless.model.TableService;
import com.tabless.model.TableVO;

@MultipartConfig
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		Vector<ProductVO> liveBuyList = (Vector<ProductVO>) session.getAttribute("liveShoppingcart");// 區域變數，也可放實體變數
		String action = req.getParameter("action");
		
		if ("changeValue".equals(action)) { 
			try {
				String url = "/back-end/liveShop/EShop.jsp";
				String inCludeVO=req.getParameter("inCludeVO");
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				  switch(inCludeVO) { 
		            case "drink": 
		            	req.setAttribute("inCludeVO", "drink");
						successView.forward(req, res);
		                break; 
		            case "dessert": 
		            	req.setAttribute("inCludeVO", "dessert"); 
						successView.forward(req, res);
		                break; 
		            case "lightfood": 
		            	req.setAttribute("inCludeVO", "lightfood"); 
						successView.forward(req, res);
		                break; 
		            case "soup": 
		            	req.setAttribute("inCludeVO", "soup");
						successView.forward(req, res);
		                break; 
		            case "jam": 
		            	req.setAttribute("inCludeVO", "jam"); 
						successView.forward(req, res);
		                break;
		            default: 
		                System.out.println("include有問題!!"); 
		        }	  
			} 
			catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/liveShop/EShop.jsp");
				failureView.forward(req, res);
			}
		}
		
		//------------------------------新增物品至購物車中-------------------------------------------
		if (action.equals("ADD")) {
			
			String requestURL = req.getParameter("requestURL");// 送出新增商品的來源網頁路徑
			String pTno = req.getParameter("pTno");
			
			String inCludeVO = "";
			String pno = req.getParameter("pno");
			
			switch(pTno) {
				case "PT001":
					inCludeVO = "drink";
					break;
				case "PT002":
					inCludeVO = "dessert";
					break;
				case "PT003":
					inCludeVO = "lightfood";
					break;
				case "PT004":
					inCludeVO = "soup";
					break;
				case "PT005":
					inCludeVO = "jam";
					break;
			}

			// 取得後來新增的商品
			ProductVO pd = getProduct(req);
//			System.out.println(pd);
			// 新增第一個商品到購物車時
			if (liveBuyList == null) {
				liveBuyList = new Vector<ProductVO>();
				liveBuyList.add(pd);
				
			} else {
				// 假若新增的商品和購物車一樣時
				if (liveBuyList.contains(pd)) {
					ProductVO innerPd = liveBuyList.get(liveBuyList.indexOf(pd));// innerPd是購物車裡的數量,pd是選擇加入的數量
					innerPd.setpDoffer(innerPd.getpDoffer() + pd.getpDoffer());
				} else if(!liveBuyList.contains(pd)){// end of if
						// 假若新增的商品和購物車不一樣時
					liveBuyList.add(pd);
			    }
			}
			
//			ProductService pdSVC = new ProductService();
//			ProductVO pdVO = pdSVC.getOneProduct(pno);
//			
//			LiveOrderDetailService lodSvc = new LiveOrderDetailService();
//			String inCludeVo = "soup";// 加入購物車要傳過來本來是什麼頁面
//			String ur ="/back-end/AdministratorServlet.do?inCludeVO="+inCludeVo+"&action=changeValue";
//			System.out.print("url"+requestURL);
//			if(requestURL.equals(ur))
//			req.setAttribute("EShop", lodSvc.getProductBypno(pdVO.getpno()));
			
			session.setAttribute("liveShoppingcart", liveBuyList);
			session.setAttribute("inCludeVO", inCludeVO);
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交回送出新增的來源網頁
			successView.forward(req, res);
			return;	
			
			/*************************** 3.完成,準備轉交(Send the Success view) ***********/
			
//			session.setAttribute("liveShoppingcart", liveBuyList);
//			String url ="/back-end/AdministratorServlet.do?inCludeVO="+includeVo+"&action=changeValue";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);	
			
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
			liveBuyList.get(d).setpDoffer(y);
			
			
			String url = "/back-end/liveShop/EShop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;	
		}
		
		// ----------------------刪除購物車中的書籍-------------------------------------------
		
		if (action.equals("DELETE")) {
			String del = req.getParameter("del");
			int d = Integer.parseInt(del);
			liveBuyList.remove(d);
			
			String url = "/back-end/liveShop/EShop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;	
		}
		
		//---------------------確認訂單，計算購物車總價錢-----------------------------------------
			
		if (action.equals("CHECKOUT")) {
			
			Double total = 0.0;
			for (int i = 0; i < liveBuyList.size(); i++) {
				ProductVO order = liveBuyList.get(i);
				Integer price = order.getpP();
				Integer quantity = order.getpDoffer();
				total += (price * quantity);
			}
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/back-end/liveShop/CheckOut.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;
		}
	}
		
		
		
		
		
		
		
		
		
//		if (!action.equals("CHECKOUT")) { // 來自來自EShop.jsp的請求
//			
//			String requestURL = req.getParameter("requestURL");// 送出新增商品的來源網頁路徑
//			String pno = req.getParameter("pno");
//
//			// 刪除購物車中的商品
//			if (action.equals("DELETE")) {
//				String del = req.getParameter("del");
//				int d = Integer.parseInt(del);
//				liveBuyList.remove(d);
//			}			
//			if("QTY".equals(action)) {
//				System.out.println("更改購物車的數量");
//				String qty = req.getParameter("qty");
//				System.out.println(qty);
//				int d = Integer.parseInt(qty);
//				String pDoffer = req.getParameter("pDoffer");
//				System.out.println(pDoffer);
//				int y=Integer.parseInt(pDoffer);
//				liveBuyList.get(d).setpDoffer(y);
//				System.out.println(liveBuyList);
//			}
//			// 新增書品至購物車中
//			else if (action.equals("ADD")) {
//				
//				// 取得後來新增的商品
//				ProductVO pd = getProduct(req);
//				System.out.println(pd);
//				// 新增第一個商品到購物車時
//				if (liveBuyList == null) {
//					liveBuyList = new Vector<ProductVO>();
//					liveBuyList.add(pd);
//					
//				} else {
//					// 假若新增的商品和購物車一樣時
//					if (liveBuyList.contains(pd)) {
//						ProductVO innerPd = liveBuyList.get(liveBuyList.indexOf(pd));// innerPd是購物車裡的數量,pd是選擇加入的數量
//						innerPd.setpDoffer(innerPd.getpDoffer() + pd.getpDoffer());
//					} else if(!liveBuyList.contains(pd)){// end of if
//							// 假若新增的商品和購物車不一樣時
//						liveBuyList.add(pd);
//				    }
//				}
//				//修改購物車內的數量
//				if("QTY".equals(action)) {
//					System.out.println("更改購物車的數量");
//					String qty = req.getParameter("qty");
//					System.out.println(qty);
//					int d = Integer.parseInt(qty);
//					String pDoffer = req.getParameter("pDoffer");
//					System.out.println(pDoffer);
//					int y=Integer.parseInt(pDoffer);
//					liveBuyList.get(d).setpDoffer(y);
//				}			
//			}
//			
////			ProductService pdSVC = new ProductService();
////			ProductVO pdVO = pdSVC.getOneProduct(pno);
////			
////			LiveOrderDetailService lodSvc = new LiveOrderDetailService();
////			if(requestURL.equals("/back-end/liveShop/EShop.jsp"))
////				req.setAttribute("EShop", lodSvc.getProductBypno(pdVO.getpno()));
////			
//			
////			String url = requestURL;
////			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交回送出新增的來源網頁
////			successView.forward(req, res);
//			session.setAttribute("liveShoppingcart", liveBuyList);
//			String url = "/back-end/liveShop/EShop.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
//		}
//
//		// 結帳，計算購物車商品價錢總價
//		else if (action.equals("CHECKOUT")) {
////			Double total = liveBuyList.stream().mapToDouble(p -> p.getpP()*p.getpDoffer()).sum();
//			Double total = 0.0;
//			for (int i = 0; i < liveBuyList.size(); i++) {
//				ProductVO order = liveBuyList.get(i);
//				Integer price = order.getpP();
//				Integer quantity = order.getpDoffer();
//				total += (price * quantity);
//			}
//			String amount = String.valueOf(total);
//			req.setAttribute("amount", amount);
//			String url = "/back-end/liveShop/CheckOut.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
//		}
//	  } 	
		
	

	private ProductVO getProduct(HttpServletRequest req) {

		String pname = req.getParameter("pname");
		String pP = req.getParameter("pP");
		String pDes = req.getParameter("pDes");
		String pDoffer = req.getParameter("pDoffer");
		String pno = req.getParameter("pno");
		String pTno = req.getParameter("pTno");

		ProductVO pd = new ProductVO();
		pd.setpname(pname);
		pd.setpP((new Integer(pP)).intValue());
		pd.setpDes(pDes);
		pd.setpDoffer((new Integer(pDoffer)).intValue());
		pd.setpno(pno);
		pd.setpTno(pTno);

		return pd;
	}
}
