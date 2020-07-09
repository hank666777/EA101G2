package com.booking.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booking.model.*;
import com.bookingdetail.model.*;



@WebServlet("/BokServlet")
public class BokServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BokServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 *************************/
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = req.getParameter("bkdate");
				System.out.println(dateString);
				java.util.Date udate = sdf.parse(dateString);
				java.sql.Date bkdate= new java.sql.Date(udate.getTime());
				String bkperiod = req.getParameter("bkperiod");
				Integer numofpeoples = new Integer(req.getParameter("numofpeoples"));
				Integer bkprice = new Integer(req.getParameter("bkprice"));
				String memno = req.getParameter("memno");
				String tableno[] = req.getParameterValues("tableno");
				
				BokVO bokvo = new BokVO();
				BokdtVO bokdtvo = new BokdtVO();
				
				bokvo.setBkDate(bkdate);
				bokvo.setBkPeriod(bkperiod);
				bokvo.setNumOfPeople(numofpeoples);
				bokvo.setBkPrice(bkprice);
				bokvo.setMemno(memno);
				/************************判斷是否有重複資料****************************************/
				BokdtService bokdtSvc = new BokdtService();
				List<BokdtVO> bokdtlist = bokdtSvc.getBokdtListByTime(bkdate, bkperiod);
				for(BokdtVO bokdt : bokdtlist) {
					for(int i=0 ; i<tableno.length ; i++) {
						if(tableno[i].equals(bokdt.getTableno())) {
							errorMsgs.add("位子已經被訂走了，動作太慢囉~");
							RequestDispatcher failureView = req.getRequestDispatcher("/front-end/bok/failureView.jsp");
							failureView.forward(req, res);
							return;
						}
					}
				}
				/***************************2.開始新增資料***************************************/
				BokService bokSvc = new BokService();
				String newbkno = bokSvc.insertNewBok(bokvo);
				System.out.println(newbkno);
				System.out.println(tableno.length);
				for(int i=0 ; i<tableno.length ; i++) {
					bokdtvo.setBkDate(bkdate);
					bokdtvo.setBkPeriod(bkperiod);
					bokdtvo.setTableno(tableno[i]);
					bokdtvo.setBkno(newbkno);
					bokdtSvc.insertOneBokdt(bokdtvo);
					System.out.println(tableno[i]);
				}
				
				
				/***************************3.完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/bok/successView.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/bok/failureView.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("listByMemNo".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 *************************/
				String memno = req.getParameter("memno");
				
				/***************************2.開始處理資料***************************************/
				BokService bokSvc = new BokService();
				List<BokVO> bokListByMemNo = bokSvc.getBokListByMemNo(memno);
				if(bokListByMemNo == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/bok/failureView.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
								
				/***************************3.完成,準備轉交(Send the Success view)***********/
				req.setAttribute("bokListByMemNo", bokListByMemNo);
				String url = "/front-end/bok/listByMemNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bok/failureView.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listByBkNo".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 *************************/
				String bkno = req.getParameter("bkno");
				
				/***************************2.開始處理資料***************************************/
				BokdtService bokdtSvc = new BokdtService();
				List<BokdtVO> bokDtListByBkNo = bokdtSvc.getBokdtListByBkNo(bkno);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
								
				/***************************3.完成,準備轉交(Send the Success view)***********/
				req.setAttribute("bokDtListByBkNo", bokDtListByBkNo);
				String url = "/bok/listByBkNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bok/failureView.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getStatusByTime".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 *************************/
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println("666");
				String dateString = req.getParameter("bkdate");
				/************************檢查bkdate*******************************/
				if(dateString == null || dateString.trim().length() == 0) {
					errorMsgs.add("日期不可為空!");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/bok/failureView.jsp");
					failureView.forward(req, res);
					return;
				}
				/****************************************************************/
				req.setAttribute("bkdate", dateString);
				System.out.println(dateString);
				java.util.Date udate = sdf.parse(dateString);
				java.sql.Date bkdate= new java.sql.Date(udate.getTime());
				System.out.println(bkdate);
				String bkperiod = req.getParameter("bkperiod");
				System.out.println(bkperiod);
				
				/***************************2.開始處理資料***************************************/
				BokdtService bokdtSvc = new BokdtService();
				List<BokdtVO> bokDtListByTime = bokdtSvc.getBokdtListByTime(bkdate, bkperiod);
								
				/***************************3.完成,準備轉交(Send the Success view)***********/
				req.setAttribute("bokDtListByTime", bokDtListByTime);
				req.setAttribute("bkperiod", bkperiod);
				
				System.out.println("777");
				String url = "/front-end/bok/newBookingStep2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bok/failureView.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("cancelBooking".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 *************************/
				
				String bkno = req.getParameter("bkno");
				System.out.println(bkno);
				/************************檢查狀態*******************************/
				BokService bokSvc = new BokService();
				if(bokSvc.getBokByBkNo(bkno).getBkStatus()==1) {
					errorMsgs.add("本訂單已經報到了!");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/bok/failureView.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/****************************************************************/
				
				
				/***************************2.開始處理資料***************************************/
				BokdtService bokdtSvc = new BokdtService();
				bokdtSvc.deleteByBkNo(bkno);
				
				bokSvc = new BokService();
				bokSvc.cancelbok(bkno);
								
				/***************************3.完成,準備轉交(Send the Success view)***********/
				
				System.out.println("777");
				String url = "/front-end/bok/successView.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bok/failureView.jsp");
				failureView.forward(req, res);
			}
		}

		
	}

}
