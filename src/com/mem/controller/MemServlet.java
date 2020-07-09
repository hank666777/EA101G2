package com.mem.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.mem.model.*;

import redis.clients.jedis.Jedis;

import com.mem.controller.MailService;
import com.mem.controller.MemberRedis;

@MultipartConfig(maxRequestSize = 100 * 1024 * 1024)

public class MemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("memberLogin".equals(action)) { // 來自select_page.jsp的請求,登入畫面

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mAccount = req.getParameter("mAccount");
				String mPw = req.getParameter("mPw");
			    

				if (mAccount == null || (mAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入會員帳號");
				}
				String mAccount_Reg = "^[(a-zA-Z0-9_)]{1,10}$";
				if (!mAccount.matches(mAccount_Reg)) {
					errorMsgs.add("會員編號格式不正確");
				}
				if (mPw == null || (mPw.trim()).length() == 0) {
					errorMsgs.add("請輸入會員密碼");
				}
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memberlogin.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}


				/*************************** 2.開始查詢資料 *****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.memberLogin(mAccount, mPw);
				
				System.out.println(memVO==null);
				
				if (memVO == null) {
					errorMsgs.add("查無資料");
				} else if (memVO.getmStatus() == 0) {
					errorMsgs.add("未驗證");
				} else if (memVO.getmStatus() == 2) {
					errorMsgs.add("停權");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memberlogin.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				session.setAttribute("memVO", memVO);
				session.setAttribute("memno",memVO.getMemno());
				
				MemVO mem = (MemVO) session.getAttribute("memVO");
				if (mem == null) {
					session.setAttribute("location", req.getRequestURI());
					res.sendRedirect(req.getContextPath() + "/front-end/mem/memberlogin.jsp");
					return;
				} else {

					// session.setAttribute("memno", rs.getString("memno")); //*工作1:
					// 才在session內做已經登入過的標識
					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
							res.sendRedirect(location);
							return;
						}
					} catch (Exception ignored) {
					}
					res.sendRedirect(req.getContextPath() + "/front-end/mem/member_center.jsp"); // *工作3:
																						// (-->如無來源網頁:則重導至member_center.jsp)

				}
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/memberlogin.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			MemService memSvc = new MemService();
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memno = req.getParameter("memno").trim();
				String mAccount = req.getParameter("mAccount").trim();

				if (mAccount == null || mAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}

				String mPw = req.getParameter("mPw").trim();
				String mPw_reg = "^[a-zA-Z0-9]{4,10}$";
				if (mPw == null || mPw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");					
				}else if(!mPw.trim().matches(mPw_reg)) {
					errorMsgs.add("密碼只能是英文字母&數字,且長度為4到10之間");
				}

				byte[] mPic;
				Part part = req.getPart("mPic");
				InputStream in = part.getInputStream();
				if(in.available() == 0) {
					mPic = memSvc.getOneMem(memno).getmPic();
				}else {
					mPic = new byte[in.available()];
					in.read(mPic);
				}
				in.close();

				String mName = req.getParameter("mName");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (mName == null || mName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mName.trim().matches(nameReg)) { 
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String mGender = req.getParameter("mGender").trim();
				if (mGender == null || mGender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				String mPhone = req.getParameter("mPhone").trim();
				String mPhone_reg = "^09[0-9]{8}$";				
				if (mPhone == null || mPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}else if(!mPhone.trim().matches(mPhone_reg)) {
					errorMsgs.add("電話只能是09開頭且為10位數的數字");
				}	

				String mEmail = req.getParameter("mEmail").trim();
				if (mEmail == null || mEmail.trim().length() == 0) {
					errorMsgs.add("email請勿空白");
				}

				java.sql.Date mRegDate = java.sql.Date.valueOf(req.getParameter("mRegDate").trim());

				Integer mStatus = new Integer(req.getParameter("mStatus"));

				if (mStatus == null) {
					errorMsgs.add("會員狀態請勿空白");
				}

				

				MemVO memVO = new MemVO();
				memVO.setmAccount(mAccount);
				memVO.setmPw(mPw);
				memVO.setmPic(mPic);
				memVO.setmName(mName);
				memVO.setmGender(mGender);
				memVO.setmPhone(mPhone);
				memVO.setmEmail(mEmail);
				memVO.setmRegDate(mRegDate);
				memVO.setmStatus(mStatus);
				memVO.setMemno(memno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				
				MemVO memVO2 = memSvc.updateMem(mAccount, mPw, mPic, mName, mGender, mPhone, mEmail, mRegDate, mStatus,	memno);

				session.setAttribute("memVO", memVO2);
//
				res.sendRedirect(req.getContextPath() + "/front-end/mem/member_center.jsp"); // *工作3:
//				MemVO mem = (MemVO) session.getAttribute("memVO");
//
//				if (mem == null) {
//					session.setAttribute("location", req.getRequestURI());
//					res.sendRedirect(req.getContextPath() + "/front-end/mem/select_page.jsp");
//					return;
//				} else {
//
//					// session.setAttribute("memno", rs.getString("memno")); //*工作1:
//					// 才在session內做已經登入過的標識
//					try {
//						String location = (String) session.getAttribute("location");
//						if (location != null) {
//							session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
//							return;
//						}
//					} catch (Exception ignored) {
//						ignored.getStackTrace();
//					}
																					// (-->如無來源網頁:則重導至member_center.jsp)

//				}

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			MemService memSvc = new MemService();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				//帳號重複防呆
				
				List<MemVO> memlist = new ArrayList<>();
				memlist = memSvc.getAll();
				
				String mAccount = req.getParameter("mAccount").trim();
                String mAccount_reg = "^[a-zA-Z0-9]{4,10}$";
				if (mAccount == null || mAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}else if (!mAccount.trim().matches(mAccount_reg)) {
					errorMsgs.add("帳號只能是英文字母&數字,且長度為4到10之間");
				}
//				System.out.println("mAccount: "+ mAccount);
				
				//使用foreach跑迴圈確認使用者輸入的帳號是否與資料庫中的某一帳號重複
				for(MemVO amem : memlist) {
					if(mAccount.trim().equals(amem.getmAccount())) {
						errorMsgs.add("此帳號已有人使用");
					}
				}

				String mPw = req.getParameter("mPw").trim();
				String mPw_reg = "^[a-zA-Z0-9]{4,10}$";
				if (mPw == null || mPw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");					
				}else if(!mPw.trim().matches(mPw_reg)) {
					errorMsgs.add("密碼只能是英文字母&數字,且長度為4到10之間");
				}

				Part part = req.getPart("mPic");
				InputStream in = part.getInputStream();
				byte[] mPic = new byte[in.available()];
				in.read(mPic);
				in.close();

				String mName = req.getParameter("mName");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (mName == null || mName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mName.trim().matches(nameReg)) { 
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}

				String mGender = req.getParameter("mGender").trim();
				if (mGender == null || mGender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				String mPhone = req.getParameter("mPhone").trim();
				String mPhone_reg = "^09[0-9]{8}$";				
				if (mPhone == null || mPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}else if(!mPhone.trim().matches(mPhone_reg)) {
					errorMsgs.add("電話只能是09開頭且為10位數的數字");
				}	
				

				String mEmail = req.getParameter("mEmail").trim();
				if (mEmail == null || mEmail.trim().length() == 0) {
					errorMsgs.add("email請勿空白");
				}

				//會員狀態預設0

				MemVO memVO = new MemVO();
				memVO.setmAccount(mAccount);
				memVO.setmPw(mPw);
				memVO.setmPic(mPic);
				memVO.setmName(mName);
				memVO.setmGender(mGender);
				memVO.setmPhone(mPhone);
				memVO.setmEmail(mEmail);
				memVO.setmStatus(0);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/member_signup.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**********2.開始新增資料***************/
				MemVO memVO2 = memSvc.addMem(mAccount, mPw, mPic, mName, mGender, mPhone, mEmail, 0);
				
				// 2.1寄信驗證碼**********************//
				MailService ms = new MailService(); // 建立物件 發送mail
				String authCode = UUID.randomUUID().toString().substring(0, 8); // 產生驗證碼
				System.out.println("驗證碼: "+authCode);

				String messageText = "Hi " + memVO2.getmName() + " 歡迎你加入 ," + "\n這是你的驗證碼："+authCode;
				ms.sendMail(mEmail, "Miss M 認證信", messageText);

				// 連線Jedis
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");

				jedis.set(memVO2.getmAccount(), authCode);// 把key value 存入Redis

				/**********3.新增完成，準備轉交**********/
				// 把key存session 存ID
				session.setAttribute("memVO", memVO2);

				String url = "/front-end/mem/identify.jsp";// 跳轉驗證頁
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				jedis.close();// 關閉jedis

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/member_signup.jsp");
				failureView.forward(req, res);
			}
		}

		if ("identify".equals(action)) { // 來自identify.jsp的請求
 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Jedis jedis = null;
            try {
            	
            	MemVO SmemVO = (MemVO) session.getAttribute("memVO");// 使用session取得剛加入會員的資訊

    			// 建立連線 取得新增會員的key value
    		    jedis = new Jedis("localhost", 6379);
    			jedis.auth("123456");
    			String authCode = jedis.get(SmemVO.getmAccount());// 取得key裡的value

    			// 驗證碼防呆 &
    			String formCode = req.getParameter("formCode");// 取得從form表單輸入的驗證碼
    			
    			if (formCode == null || formCode.trim().length() == 0) {
    				errorMsgs.add("驗證碼請勿空白");
    			}
    			
    			if (!authCode.equals(formCode)) {
    				errorMsgs.add("驗證碼不正確");
    			}
    			
    			if (authCode.equals(formCode)) {
    				System.out.println("驗證成功");
    				
    				// 驗證成功後,修改會員Status
    				MemService mSv = new MemService();
    				Integer mStatus = 1;
    				String mAccount = SmemVO.getmAccount();
    				mSv.identify(mStatus, mAccount);
    					 				
    				String url = "/front-end/mem/memberlogin.jsp";// 成功後，跳轉到重新登入
    				//重新登入要移除會員session
    				session.removeAttribute("memVO");
    				
    				RequestDispatcher successView = req.getRequestDispatcher(url);
    				successView.forward(req, res);
    				return;
    			}

    			if (!errorMsgs.isEmpty()) {
    				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/identify.jsp");
    				failureView.forward(req, res);
    				return;// 程式中斷
    			}
            } catch(Exception e){
            	e.printStackTrace();
            } finally{
				jedis.close();// 關閉Jedis資源
			}

		}
		
		//再次取得驗證碼
		if("identify_again".equals(action)) {

			MemVO member = (MemVO)session.getAttribute("memVO");

			MailService ms = new MailService(); // 建立物件 發送mail
			String authCode = UUID.randomUUID().toString().substring(0, 8); // 產生驗證碼
			System.out.println(authCode);



			// 連線Jedis
			Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");

			jedis.set(member.getmAccount(), authCode);// 把key value 存入Redis

			String mEmail = member.getmEmail(); // 取得寄送到的會員mail
			String messageText = "Hi \n" + member.getmName() + "\n這是你的驗證碼："+ authCode;
			ms.sendMail(mEmail, "Miss M 認證信", messageText);

			//回驗證網頁
			String url = "/front-end/mem/identify.jsp";// 跳轉驗證網頁
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);


		}
		
		
//---------------------------------------後台控制器----------------------------------------------------------------
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求,會員資料修改

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String memno = new String(req.getParameter("memno"));
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         
				String url = "/back-end/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("back_update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/			    
				String mAccount = req.getParameter("mAccount").trim();

				if (mAccount == null || mAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}

				String mPw = req.getParameter("mPw").trim();
				String mPw_reg = "^[a-zA-Z0-9]{4,10}$";
				if (mPw == null || mPw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");					
				}else if(!mPw.trim().matches(mPw_reg)) {
					errorMsgs.add("密碼只能是英文字母&數字,且長度為4到10之間");
				}

//				新增圖片
				Part part = req.getPart("mPic");
				InputStream in = part.getInputStream();
				byte[] mPic = new byte[in.available()];
				in.read(mPic);
				in.close();

				String mName = req.getParameter("mName");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (mName == null || mName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mName.trim().matches(nameReg)) { 
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String mGender = req.getParameter("mGender").trim();
				if (mGender == null || mGender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				String mPhone = req.getParameter("mPhone").trim();
				String mPhone_reg = "^09[0-9]{8}$";				
				if (mPhone == null || mPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}else if(!mPhone.trim().matches(mPhone_reg)) {
					errorMsgs.add("電話只能是09開頭且為10位數的數字");
				}	

				String mEmail = req.getParameter("mEmail").trim();
				if (mEmail == null || mEmail.trim().length() == 0) {
					errorMsgs.add("email請勿空白");
				}

				java.sql.Date mRegDate = java.sql.Date.valueOf(req.getParameter("mRegDate").trim());

				
				
				Integer mStatus = Integer.parseInt(req.getParameter("mStatus"));
//				Integer mStatus_reg = Integer.parseInt("^(0-2){1}$");
//				if (mStatus == null) {
//					errorMsgs.add("會員狀態請勿空白");
//				}else if(!(mStatus == mStatus_reg)) {
//					errorMsgs.add("狀態只能是數字,且為0~2之間的整數");
//				}	
				
				String memno = req.getParameter("memno").trim();

				MemVO memVO = new MemVO();
				memVO.setmAccount(mAccount);
				memVO.setmPw(mPw);
				memVO.setmPic(mPic);
				memVO.setmName(mName);
				memVO.setmGender(mGender);
				memVO.setmPhone(mPhone);
				memVO.setmEmail(mEmail);
				memVO.setmRegDate(mRegDate);
				memVO.setmStatus(mStatus);
				memVO.setMemno(memno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mAccount,mPw,mPic,mName,mGender,mPhone,mEmail, mRegDate,mStatus,memno);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
	}
    
}
