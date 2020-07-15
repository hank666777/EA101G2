package com.product.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.*;
import com.ptype.model.PTypeService;
import com.ptype.model.PTypeVO;

@MultipartConfig
public class ProductServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;CharSet:UTF-8");
		String action = req.getParameter("action");
		
		 // 來自selectProductPage.jsp的請求
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
			/**1.接收請求參數 - 輸入格式的錯誤處理**/
				
				String str = req.getParameter("pno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}

				//空值重導
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/selectProductPage.jsp");
					failureView.forward(req, res);
					return;
				}

				
				String pno = null;
				pno = new String(str);

				//空值重導	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/selectProductPage.jsp");
					failureView.forward(req, res);
					return;
				}

			/**2.開始查詢資料**/
				
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pno);
				
				PTypeService ptypeSvc = new PTypeService();
				PTypeVO ptypeVO = ptypeSvc.getOnePType(productVO.getpTno());
				System.out.println("no: " + productVO.getpTno());
				System.out.println(ptypeVO.toString());
				
				if (productVO == null || (str.trim()).length()==0) {
					errorMsgs.add("查無資料，請依照正確格式輸入，如: P0001");
				}
				
				//空值重導	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/selectProductPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
			/**3.查詢完成,準備轉交(Send the Success view)**/
				//輸出跳轉
				req.setAttribute("productVO", productVO);
				req.setAttribute("ptypeVO", ptypeVO);
				String url = "/back-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			/**其他可能的錯誤處理**/	
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/selectProductPage.jsp");
				failureView.forward(req, res);
			}
		}

	/***********************************************************************************************/
		
		// 來自selectProductPage.jsp的請求2
		
				if ("get_Category".equals(action)) {
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						
					/**1.接收請求參數 - 輸入格式的錯誤處理**/
						
						String str = req.getParameter("pTno");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入商品編號");
						}

						//空值重導
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/selectProductPage.jsp");
							failureView.forward(req, res);
							return;
						}

						
						String pTno = null;
						pTno = new String(str);

						//空值重導	
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/selectProductPage.jsp");
							failureView.forward(req, res);
							return;
						}

					/**2.開始查詢資料**/
						
						ProductService productSvc = new ProductService();
						List<ProductVO> pCategory = productSvc.getProductByCategory(pTno);
						
						PTypeService ptypeSvc = new PTypeService();

//						PTypeVO ptypeVO = ptypeSvc.getOnePType(productVO.getpTno());
//						System.out.println("no: " + productVO.getpTno());
//						System.out.println(ptypeVO.toString());
						
						if (pCategory == null || (str.trim()).length()==0) {
							errorMsgs.add("查無資料，請依照正確格式輸入，如: P0001");
						}
						
						//空值重導	
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/selectProductPage.jsp");
							failureView.forward(req, res);
							return;
						}
						
					/**3.查詢完成,準備轉交(Send the Success view)**/
						//輸出跳轉
						//存物件讓傳過去的網頁拿到
						 
//						req.setAttribute("pTno", pTno);
//						String url = "/back-end/product/listOneProduct.jsp";
//						RequestDispatcher successView = req.getRequestDispatcher(url);			
//						successView.forward(req, res);
						
						req.getSession().setAttribute("pTno", pTno);
						String url = req.getContextPath()+"/back-end/product/listProductCategory.jsp";
						res.sendRedirect(url);
						
					/**其他可能的錯誤處理**/	
						
					} catch (Exception e) {
						errorMsgs.add("無法取得資料：" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/selectProductPage.jsp");
						failureView.forward(req, res);
					}
				}

			/***********************************************************************************************/
		
				// 來自frontProductSearch.jsp的請求
				
				if ("get_Category_front".equals(action)) {
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						
					/**1.接收請求參數 - 輸入格式的錯誤處理**/
						
						String str = req.getParameter("pTno");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入商品編號");
						}

						//空值重導
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/frontProductSearch.jsp");
							failureView.forward(req, res);
							return;
						}

						
						String pTno = null;
						pTno = new String(str);
System.out.println(pTno);
						//空值重導	
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/frontProductSearch.jsp");
							failureView.forward(req, res);
							return;
						}

					/**2.開始查詢資料**/
						
						ProductService productSvc = new ProductService();
						List<ProductVO> pCategory = productSvc.getProductByCategory(pTno);
						
						PTypeService ptypeSvc = new PTypeService();

//						PTypeVO ptypeVO = ptypeSvc.getOnePType(productVO.getpTno());
//						System.out.println("no: " + productVO.getpTno());
//						System.out.println(ptypeVO.toString());
						
						if (pCategory == null || (str.trim()).length()==0) {
							errorMsgs.add("查無資料，請依照正確格式輸入，如: P0001");
						}
						
						//空值重導	
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/frontProductSearch.jsp");
							failureView.forward(req, res);
							return;
						}
						
					/**3.查詢完成,準備轉交(Send the Success view)**/
						//輸出跳轉
						//存物件讓傳過去的網頁拿到
						 
//						req.setAttribute("pTno", pTno);
//						String url = "/back-end/product/listOneProduct.jsp";
//						RequestDispatcher successView = req.getRequestDispatcher(url);			
//						successView.forward(req, res);
						
						req.getSession().setAttribute("pTno", pTno);
						String url = req.getContextPath()+"/front-end/product/frontProductCategory.jsp";
						res.sendRedirect(url);
						
					/**其他可能的錯誤處理**/	
						
					} catch (Exception e) {
						errorMsgs.add("無法取得資料：" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/frontProductSearch.jsp");
						failureView.forward(req, res);
					}
				}
		
				
			/***********************************************************************************************/				
				
		// 來自addEmp.jsp的請求
		
		if ("insert".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
			/**1.接收請求參數 - 輸入格式的錯誤處理**/
				
				String pname = req.getParameter("pname");
				String pnameReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,60}$";
					if(pname==null || pname.trim().length()==0) {
						errorMsgs.add("商品名稱：請勿空白");
					}else if(!pname.trim().matches(pnameReg)) {
						errorMsgs.add("商品名：只能是中、英文字母、數字，中文字數不得大於20");
					}
		
				Integer pP = null;
					try {
						pP = new Integer(req.getParameter("pP").trim());
						if(pP<=0) {
							errorMsgs.add("商品價格須為正整數且不得為0");
						}
					}catch(NumberFormatException e){
						errorMsgs.add("商品價格須為正整數且不得為0");
					}
					
					byte[] pPic = null;
					Part part = req.getPart("pPic");
					InputStream in = null;
						try {
							
							in = part.getInputStream();
							pPic = new byte[in.available()];
							in.read(pPic);
							
						} catch (IOException e) {
								errorMsgs.add("找不到圖片");
						} finally {
								in.close();
						}
						
				String pDes = req.getParameter("pDes").trim();
					if(pDes == null || pDes.trim().length() == 0) {
						errorMsgs.add("請撰寫商品描述");
					}	
				
				Integer pDoffer = null;
					try {
						pDoffer = new Integer(req.getParameter("pDoffer").trim());
						if(pDoffer<=0) {
							errorMsgs.add("商品供給量須為正整數且不得為0");
						}
					}catch(NumberFormatException e){
						errorMsgs.add("商品供給量須為正整數且不得為0");
					}
					
				Integer invStatus = new Integer(req.getParameter("invStatus").trim());
				Integer pStatus = new Integer(req.getParameter("pStatus").trim());
				String pTno = req.getParameter("pTno").trim();

				ProductVO productVO = new ProductVO();
				productVO.setpname(pname);
				productVO.setpP(pP);
				productVO.setpPic(pPic);
				productVO.setpDes(pDes);
				productVO.setpDoffer(pDoffer);
				productVO.setINVStatus(invStatus);
				productVO.setpStatus(pStatus);
				productVO.setpTno(pTno);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
			
			/**2.開始新增資料**/

				ProductService productSvc = new ProductService();
				productSvc.addProduct(pname,pP,pPic,pDes,pDoffer,invStatus,pStatus,pTno);

			/**3.新增完成,準備轉交(Send the Success view)**/			
				
				String url = "/back-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			/**其他可能的錯誤處理**/			
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}

	/***********************************************************************************************/	
	
		// 來自listAllProduct.jsp的請求	
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
		
			/**1.接收請求參數**/			
				
				String pno = req.getParameter("pno");
				
			/**2.開始查詢資料**/			
				
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pno);
	
			/**3.查詢完成,準備轉交(Send the Success view)**/						
				
				// 資料庫取出的productVO物件,存入req
				req.setAttribute("productVO", productVO);         
				String url = "/back-end/product/updateProductInput.jsp";
				
				// 成功轉交 updateProductInput.jsp
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			/**其他可能的錯誤處理**/						
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
	/***********************************************************************************************/		
		
		// 來自updateProductInput.jsp的請求
		
		if("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			
			try {
				
			/**1.接收請求參數 - 輸入格式的錯誤處理**/
			
				String pno = req.getParameter("pno").trim();
				String pname = req.getParameter("pname").trim();
				String pnameReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,60}$";
					if(pname==null || pname.trim().length()==0) {
						errorMsgs.add("商品名稱：請勿空白");
					}else if(!pname.trim().matches(pnameReg)) {
						errorMsgs.add("商品名：只能是中、英文字母、數字，中文字數不得大於20");
					}
					
					Integer pP = null;
					try {
						pP = new Integer(req.getParameter("pP").trim());
						if(pP<=0) {
							errorMsgs.add("商品價格須為正整數且不得為0");
						}
					}catch(NumberFormatException e){
						errorMsgs.add("商品價格須為正整數且不得為0");
					}
				
					byte[] pPic = null;
					Part part = req.getPart("pPic");
					InputStream in = null;
						try {
							
							in = part.getInputStream();
							pPic = new byte[in.available()];
							in.read(pPic);
							
						} catch (IOException e) {
								errorMsgs.add("找不到圖片");
						} finally {
								in.close();
						}

				String pDes = req.getParameter("pDes").trim();
					if(pDes == null || pDes.trim().length() == 0) {
						errorMsgs.add("請撰寫商品描述");
					}	
				
				Integer pDoffer = null;
					try {
						pDoffer = new Integer(req.getParameter("pP").trim());
						if(pDoffer<=0) {
							errorMsgs.add("商品供給量須為正整數且不得為0");
						}
					}catch(NumberFormatException e){
						errorMsgs.add("商品供給量須為正整數且不得為0");
					}
			
				Integer invStatus = new Integer(req.getParameter("invStatus").trim());
				Integer pStatus = new Integer(req.getParameter("pStatus").trim());
				String pTno = req.getParameter("pTno").trim();

				ProductVO productVO = new ProductVO();
				productVO.setpname(pname);
				productVO.setpP(pP);
				productVO.setpPic(pPic);
				productVO.setpDes(pDes);
				productVO.setpDoffer(pDoffer);
				productVO.setINVStatus(invStatus);
				productVO.setpStatus(pStatus);
				productVO.setpTno(pTno);
				productVO.setpno(pno);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/updateProductInput.jsp");
					failureView.forward(req, res);
					return;
				}
				
			/**2.開始修改資料**/
				
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());	
					session.setAttribute("map", map1);
					map = map1;
				}
				
				ProductService productSvc = new ProductService();
				productSvc.updateProduct(pname,pP,pPic,pDes,pDoffer,invStatus,pStatus,pTno,pno);

			/**3.修改完成,準備轉交(Send the Success view)**/	
				
				if(requestURL.equals("/back-end/product/listProductByCompositeQuery.jsp") || requestURL.equals("/back-end/product/listAllProduct.jsp"))		
				req.setAttribute("listProduct_ByCompositeQuery", productSvc.getAll(map));
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			/**其他可能的錯誤處理**/	
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗：" +e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/updateProductInput.jsp");
				failureView.forward(req, res);
			}
		}

		
	/***********************************************************************************************/		
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			
			try {
				
			/**1.接收請求參數**/
				
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());	
					session.setAttribute("map", map1);
					map = map1;
				}
				
				String pno = new String(req.getParameter("pno"));
				
			/**2.開始刪除資料**/
				
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(pno);

			/**3.刪除完成,準備轉交(Send the Success view)**/								
				
				if(requestURL.equals("/back-end/product/listProductByCompositeQuery.jsp") || requestURL.equals("/back-end/product/listAllProduct.jsp"))		
					req.setAttribute("listProduct_ByCompositeQuery", productSvc.getAll(map));
				
				String url = "/back-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			/**其他可能的錯誤處理**/	
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	


	/***********************************************************************************************/		
		if("listProduct_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
			/**1.將輸入資料轉為Map**/
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());	
					session.setAttribute("map", map1);
					map = map1;
				}
			/**2.開始複合查詢**/	
				ProductService productSvc = new ProductService();
				List<ProductVO> list = productSvc.getAll(map);
				
			/**3.查詢完成,準備轉交(Send the Success view)**/
				req.setAttribute("listProduct_ByCompositeQuery", list);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listProductByCompositeQuery.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/selectProductPage.jsp");
				failureView.forward(req, res);
				
			}
		}
	
	/***********************************************************************************************/		
	
		if("getDisplayView".equals(action)) {
			
			try {
				String pno = new String(req.getParameter("pno"));
				
				ProductJNDIDAO dao = new ProductJNDIDAO();
				ProductVO productVO = dao.findByPK(pno);
				
				req.setAttribute("productVO", productVO);
				
				boolean openModal=true;
				req.setAttribute("openModal", openModal);
				
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/frontProductCategory.jsp");
				
				successView.forward(req, res);
				return;
				
			}catch(Exception e) {
				throw new ServletException(e);
			}
			
		}
		
	/***********************************************************************************************/
	
	// 來自frontProductSearch2.jsp的請求		
			
			if("listProduct_ByCompositeQuery2".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
					
				try {
				/**1.將輸入資料轉為Map**/
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
					if (req.getParameter("whichPage") == null) {
						HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());	
						session.setAttribute("map", map1);
						map = map1;
					}
				/**2.開始複合查詢**/	
					ProductService productSvc = new ProductService();
					List<ProductVO> list = productSvc.getAll(map);
						
				/**3.查詢完成,準備轉交(Send the Success view)**/
					
					req.getSession().setAttribute("listProduct_ByCompositeQuery2", list);
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/frontProductCategory.jsp");
					successView.forward(req, res);
					
				}catch(Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/frontProductSearch.jsp");
					failureView.forward(req, res);
						
				}
			}	
	}
}