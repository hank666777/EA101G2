package com.employee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.permission.model.PermissionService;
import com.permission.model.PermissionVO;

public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeLoginServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		String action = req.getParameter("action");
		
		HttpSession session = req.getSession();
		
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				//////////////// 1.錯誤驗證1///////////////
				// checkCode verify
				String checkCodeImg = (String) session.getAttribute("checkCodeImg");
//				System.out.println("EmployeeLoginServlet: " + checkCodeImg);
				
				String checkCode = req.getParameter("checkCode");
				if (checkCode == null || checkCode.trim().length() == 0) {
					errorMsgs.add("驗證碼請勿空白");
				} else if (!checkCodeImg.equals(checkCode)) {
					errorMsgs.add("驗證碼錯誤");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end_login.jsp");
					failureView.forward(req, res);
					return;
				}

				// eAccount verify
				String eAccount = req.getParameter("eAccount");
				String eAccountReg = "^[\\w]{1,10}$";
				if (eAccount == null || eAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if (!eAccount.trim().matches(eAccountReg)) {
					errorMsgs.add("系統無法辨識這個帳號");
				}

				// ePw verify
				String ePw = req.getParameter("ePw");
				String ePwReg = "[\\w]{1,10}";
				if (ePw == null || ePw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if (!ePw.trim().matches(ePwReg)) {
					errorMsgs.add("密碼錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end_login.jsp");
					failureView.forward(req, res);
					return;
				}

/////DB search employee
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneBy_eAccount_ePw(eAccount, ePw);

				if (empVO == null) {
					errorMsgs.add("無此使用者");
				} else if (empVO.geteStatus() == 0) {
					errorMsgs.add("您已離職");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end_login.jsp");
					failureView.forward(req, res);
					return;
				}

				// 查權限，準備轉交
				String empno = empVO.getEmpno();

				PermissionService perSvc = new PermissionService();
				List<PermissionVO> perVOlist = null;

				perVOlist = perSvc.getOnePermission(empno);

				// login success
				if (empVO.geteAccount().equals(eAccount) && empVO.getePw().equals(ePw)) {

					// 1.session內做已登入過的標籤
					// 1.1銷毀登入碼
					session.removeAttribute("checkCodeImg");

					session.setAttribute("employeeVO", empVO);
					session.setAttribute("perVOlist", perVOlist);

					try {
						String location = (String) session.getAttribute("location");

						// 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						if (location != null) {
							//銷毀避免存太多
							session.removeAttribute("location");
							res.sendRedirect(location);
							return;
							// sourcePage unknown -->至 back-end-index.jsp
						} else {
							String url = req.getContextPath() + "/back-end/back-end-index.jsp";
							res.sendRedirect(url);
						}
						// successView.forward(req, res);
					} catch (Exception e) {
						System.out.println("EmployeeLoginServlet error: " + e.getMessage());
					}

				}

			} catch (Exception e) {
				e.getMessage();
			}
		}

	}

}
