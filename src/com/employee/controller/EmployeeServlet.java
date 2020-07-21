package com.employee.controller;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.employee.model.*;
import com.features.model.FeaturesService;
import com.features.model.FeaturesVO;
import com.permission.model.PermissionService;
import com.permission.model.PermissionVO;

import mail.server.MailService;


@MultipartConfig
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//已開filter，測試
		//		req.setCharacterEncoding("UTF-8");
		
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********1.接收請求請求參數，輸入格式的錯誤處理********/
				String empno = req.getParameter("empno");
				/*非必要*/
				String empnoReg = "^E\\d{7}";
				if (empno == null || (empno.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}else if(!empno.trim().matches(empnoReg)) {
					errorMsgs.add("員工帳號格式不正確，EX：E0000001");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/select_page_employee.jsp");
					failureView.forward(req, res);
					return;
				}

				/*********2.開始資料查詢*********/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneEmployee(empno);
				if(empVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/select_page_employee.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢權限
				PermissionService perSvc = new PermissionService();
				List<PermissionVO> perVOlist = new ArrayList<>();
				perVOlist = perSvc.getOnePermission(empno);
				
				/*********3.查詢完成，準備轉交*************/
				req.setAttribute("employeeVO", empVO);
				req.setAttribute("perVOlist", perVOlist);
				String url = "/back-end/employee/employee_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*********可能的錯誤處理**********/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failDispatcher = req.getRequestDispatcher("/back-end/employee/select_page_employee.jsp");
				failDispatcher.forward(req, res);
			}
		}

		// 來自listAllEmployee.jsp的請求
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			//權限限制，取得登入者的權限，過低禁止修改
			EmployeeVO loginemp = (EmployeeVO) session.getAttribute("employeeVO");
			try {
//				if("服務生".equals(loginemp.geteTitle())) {
//					errorMsgs.add("權限過低，乖乖做事吧");
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_update.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				
				/********1.receive parameter*******/
				String empno = req.getParameter("empno");
				////////非必要
				String empnoReg = "^E\\d{7}";
				if(empno ==null || empno.trim().length() ==0) {
					errorMsgs.add("員工帳號：請勿空白");
				}else if(!empno.trim().matches(empnoReg)) {
					errorMsgs.add("員工帳號：格式不正確，EX：E0000001");
				}
				
				/********2.search data************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneEmployee(empno);

				PermissionService perSvc = new PermissionService();
				List<PermissionVO> perVOlist = perSvc.getOnePermission(empno);

				/********3.轉交************/
				req.setAttribute("employeeVO", empVO);
				req.setAttribute("perVOlist", perVOlist);
				String url = "/back-end/employee/employee_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/********Other failure process(其他錯誤處理)*******/
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_update.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 來自update_employee_input.jsp的請求
		if("update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			
			req.setAttribute("errorMsgs", errorMsgs);
			//for eAccount verify....
			EmployeeService empSvc = new EmployeeService();
			List<EmployeeVO> emplist = new ArrayList<>();
//權限限制，取得登入者的權限，過低禁止修改，取消
			EmployeeVO loginemp = (EmployeeVO) session.getAttribute("employeeVO");
			
			try {
//////0.1////，取消
//				if("服務生".equals(loginemp.geteTitle())) {
//					errorMsgs.add("權限過低，乖乖做事吧");
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_update.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				
				/*********1.receive request parameter ,input type failure process****/
				String empno = req.getParameter("empno");
				//eAccount verify，帳號預設為員工編號
				String eAccount = req.getParameter("eAccount");
				String eAccountReg = "^[\\w]{1,10}$";
		
				emplist = empSvc.getAll();
				
				if(eAccount == null || eAccount.trim().length() == 0) {
					errorMsgs.add("員工帳號: 請勿空白");
				}else if(!eAccount.trim().matches(eAccountReg)) {
					errorMsgs.add("員工帳號: 只能是英文大小寫或數字，長度1~10位");
				}
				for(EmployeeVO aemp:emplist) {
					//add eAccount verify dbeAccount
					String dbeAccount = aemp.geteAccount();
					if(dbeAccount.trim().equals(eAccount)) {
						//要過濾自己
						if(!empno.trim().equals(aemp.getEmpno())) {
							errorMsgs.add("該員工帳號已有人使用");
						}
					}
				}
				
				//password verify
				String ePw = req.getParameter("ePw");
				String ePwReg = "^[(a-zA-Z0-9)]{1,10}$";
				if(ePw == null || ePw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}else if(!ePw.trim().matches(ePwReg)) {
					errorMsgs.add("密碼只能是英文大小寫或數字，長度1~10位");
				}
				
				//employee name verify
				String eName = req.getParameter("eName");
				String eNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if(eName == null || eName.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				}else if(!eName.trim().matches(eNameReg)) {
					errorMsgs.add("姓名只能是中英文字母、數字，長度2~10位");
				}
				
				//ePhone verify
				String ePhone = req.getParameter("ePhone");
				String ePhoneReg = "^[(0-9)]{9,10}$";
				if(ePhone == null || ePhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}else if(!ePhone.trim().matches(ePhoneReg)) {
					errorMsgs.add("電話只能是數字0~9，長度9~10位");
				}
				
				//email verify
				String eEmail = req.getParameter("eEmail");
				String eEmailReg = "^[\\w_-]+@(.[\\w_-]+)+$";
				if(eEmail == null || eEmail.trim().length() == 0) {
					errorMsgs.add("EMAIL請勿空白");
				}else if(!eEmail.trim().matches(eEmailReg)) {
					errorMsgs.add("EMAIL請依格式輸入 ex:AAA@company.com");
				}
				
				//ePic verify
				byte[] ePic;
				Part ePicPart = req.getPart("ePic");
				InputStream in = ePicPart.getInputStream();
				//use default ePic
				if(in.available() == 0) {
					ePic = empSvc.getOneEmployee(empno).getePic();
//					System.out.println("員工圖片未更新");
				}else {
					//use new ePic
					ePic = new byte[in.available()];
					in.read(ePic);
				}
				in.close();
				
				//eTitle verify，已做下拉式選單
				String eTitle = req.getParameter("eTitle").trim();
				if(eTitle == null || eTitle.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				
				//eStatus verify，已做下拉式選單
				Integer eStatus = null;
				try {
					eStatus = new Integer(req.getParameter("eStatus").trim());
				}catch(Exception e) {
					eStatus = 0;
					errorMsgs.add("狀態請選擇");
				}
				
				//權限增加
				String[] feanoArr = req.getParameterValues("feano");
//				System.out.println( "feano: " +feanoArr.toString());
				List<PermissionVO> perVOlist = new ArrayList<>();
				if(feanoArr != null) {
					
					//權限加入list，準備丟回jsp
					for(String perArr:feanoArr) {
						PermissionVO perVO = new PermissionVO();
						perVO.setEmpno(empno);
						perVO.setFeano(perArr);
						perVOlist.add(perVO);
					}
				}
				
				EmployeeVO empVO = new EmployeeVO();
				empVO.setEmpno(empno);
				empVO.seteAccount(eAccount);
				empVO.setePw(ePw);
				empVO.seteName(eName);
				empVO.setePhone(ePhone);
				empVO.seteEmail(eEmail);
				empVO.setePic(ePic);
				empVO.seteTitle(eTitle);
				empVO.seteStatus(eStatus);
				
				//send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", empVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_update.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***********2.開始修改資料***********/
				//2.1權限修改
				PermissionService perSvc = new PermissionService();
				FeaturesService feaSvc = new FeaturesService();
				List<FeaturesVO> fea = new ArrayList<>();
				fea = feaSvc.getAll();
				try {
					for(FeaturesVO afea: fea) {
						//刪除全部
						perSvc.deletePermission(empno, afea.getFeano());
					}
					for(String perArr:feanoArr) {
						//新增權限
						perSvc.addPermission(empno, perArr);
					}
				}catch (Exception e){
					System.out.println("修改資料異常"+ e.getMessage());
				}
				//2.2員工資料修改
				empSvc.updateEmployee(eAccount, ePw, eName, ePhone, eEmail, ePic, eTitle, eStatus,empno);
//				System.out.println("一筆員工基本資料修改成功");
				
				/***********3.修改完成*************/
//如果是修改自己，傳session即時更新0628修改
				if(loginemp.getEmpno().equals(empno)) {
//					System.out.println("loginemp.getEmpno(): " + loginemp.getEmpno());
					session.setAttribute("employeeVO", empVO);
					session.setAttribute("perVOlist", perVOlist);
//////////////////////////
				}else{
					req.setAttribute("employeeVO", empVO);
					req.setAttribute("perVOlist", perVOlist);
				}
				//忘記這要幹嘛0705↓
//				req.setAttribute("perVOlist", perVOlist);
				String url = "/back-end/employee/employee_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***********其他可能的錯誤處理***************/
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_update.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 來自addEmployee.jsp的請求
		if("insert".equals(action)) {   
			
			List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			//for eAccount verify....
			EmployeeService empSvc = new EmployeeService();
//			List<EmployeeVO> emplist = new ArrayList<>();
			
//權限限制，取得登入者的權限，過低禁止修改，取消
			EmployeeVO loginemp = (EmployeeVO) session.getAttribute("employeeVO");
			try {
//取消				
//				if("服務生".equals(loginemp.geteTitle())) {
//					errorMsgs.add("權限過低，乖乖做事吧");
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_list_all.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				
				/*********1.receive request parameter ,input type failure process****/
				
				//account verify，原帳號帳號亂數生成
//				String eAccount = UUID.randomUUID().toString().substring(0, 3); 
				List<EmployeeVO> emplist = empSvc.getAll();
				//不是Kent463就給他Kent463
				boolean b = emplist.stream().anyMatch(emp -> emp.geteAccount().equals("Kent463"));
				String eAccount = "";
				if(!b) {
					eAccount = "Kent463";
				}else {
					eAccount = UUID.randomUUID().toString().substring(0, 3); 
				}
								
				
				//ePw verify，員工新增，密碼自動生成
				String ePw = UUID.randomUUID().toString().substring(0, 8);
				
				//employee name verify
				String eName = req.getParameter("eName");
				String eNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if(eName == null || eName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				}else if(!eName.trim().matches(eNameReg)) {
					errorMsgs.add("員工姓名: 只能是中英文字母、數字，長度2~10位");
				}
				
				//phone verify
				String ePhone = req.getParameter("ePhone");
				String ePhoneReg = "^[0-9]{9,10}$";
				if(ePhone == null || ePhone.trim().length() == 0) {
					errorMsgs.add("員工電話: 請勿空白");
				}else if(!ePhone.trim().matches(ePhoneReg)) {
					errorMsgs.add("員工電話: 只能是數字0~9，長度9~10位");
				}
				
				//email verify
				String eEmail = req.getParameter("eEmail");
				String eEmailReg = "^[\\w_-]+@(.[\\w_-]+)+$";
				if(eEmail == null || eEmail.trim().length() == 0) {
					errorMsgs.add("員工EMAIL: 請勿空白");
				}else if(!eEmail.trim().matches(eEmailReg)) {
					errorMsgs.add("員工EMAIL: 請依格式輸入 ex:AAA@company.com");
				}
				
				//picture verify
				byte[] ePicArr;
				Part ePicPart = req.getPart("ePic");
				InputStream in = ePicPart.getInputStream();
				ePicArr = new byte[in.available()];
				if(in.available() == 0){
					//使用者沒上傳，給他預設圖片
					in = getServletContext().getResourceAsStream("/images/user-circle.png");
					ePicArr = new byte[in.available()];
//					System.out.println("上傳預設圖片");
				}
				in.read(ePicArr);
				in.close();
				
				//employee title verify
				String eTitle = req.getParameter("eTitle");
				if(eTitle == null || eTitle.trim().length() == 0) {
					errorMsgs.add("員工職位: 請勿空白");
				}
				
				//employee status verify
				Integer eStatus = null;
				try {
					eStatus = new Integer(req.getParameter("eStatus").trim());
				}catch(Exception e) {
					eStatus = 0;
					errorMsgs.add("員工狀態: 請選擇");
				}
				
				//權限增加
				String[] feanoArr = req.getParameterValues("feanos");
				List<PermissionVO> perVOlist = new ArrayList<>();
				
				//有權限才進入做寫入，暫存list
				if(feanoArr != null) {
					for(String pers:feanoArr) {
						PermissionVO perVO = new PermissionVO();
						perVO.setFeano(pers);
						perVOlist.add(perVO);
					}
				}
				
				EmployeeVO empVO = new EmployeeVO();
				empVO.seteAccount(eAccount);
				empVO.setePw(ePw);
				empVO.seteName(eName);
				empVO.setePhone(ePhone);
				empVO.seteEmail(eEmail);
				empVO.setePic(ePicArr);
				empVO.seteTitle(eTitle);
				empVO.seteStatus(eStatus);
				
				//send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", empVO);
					//for sweet alert
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("error_code", "1");
					req.setAttribute("error_msg", "輸入格式有誤");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**********2.開始新增資料***************/
				empSvc = new EmployeeService();
				if(feanoArr != null) {
					empVO = empSvc.addEmployeeWithPermission( eAccount, ePw, eName, ePhone, eEmail, ePicArr, eTitle, eStatus, perVOlist);
//					System.out.println("一位員工與權限同時新增成功");
				}else {
					empVO = empSvc.addEmployee( eAccount, ePw, eName, ePhone, eEmail, ePicArr, eTitle, eStatus);
//					System.out.println("一位員工資料新增成功");
				}
//此處為同時新增employee和permission，20200621測試成功，新增員工不增加權限也成功
				/**********2.1寄送密碼給新使用者*********/
				//寄送郵件給新使用者
				String messageText = "Hello! \n" + eName + "\n" +
						"您的帳號: "+ eAccount +"\n您的密碼: " + ePw + "\n" +" (已經啟用)"; 
				MailService mailService = new MailService();
				mailService.sendMail(eEmail, "密碼通知", messageText);
				
				/**********3.新增完成，準備轉交**********/
				req.setAttribute("employeeVO", empVO);
				req.setAttribute("perVOlist", perVOlist);
				String url = "/back-end/employee/employee_list_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/**********其他可能的錯誤處理************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_add.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
//select_page_employee.jsp中的請求，未完成
		if("employee_list_search".equals(action) || "employee_list_search2".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try{
				/*************1.receive Parameter***************/
				String empno = req.getParameter("keyempno");
				System.out.println("empno:" + empno);
				String empnoReg = "^\\w{0,8}";
				if(!empno.trim().matches(empnoReg)) {
					errorMsgs.add("員工帳號格式不正確，EX：E0000001");
				}
//				if(empno == null || empno.trim().length() == 0) {
//					errorMsgs.add("請輸入查詢關鍵字");
//				}
				String eName = req.getParameter("keyeName");
				System.out.println("eName:"+ eName);
				String eNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,10}$";
				if(!eName.trim().matches(eNameReg)) {
					errorMsgs.add("員工姓名: 只能是中英文字母、數字和_，長度0~10位");
				}

				//keyeTitle verify，已做下拉式選單，可為空值查詢
				String eTitle = req.getParameter("keyeTitle");
				System.out.println("eTitle:" + eTitle);

				//keyeStatus verify，已做下拉式選單，可為空值查詢
				Integer eStatus = null;
				String keyeStatus = req.getParameter("keyeStatus").trim();
				if("".equals(keyeStatus)) {
					eStatus = 99;
				}else {
					eStatus = Integer.parseInt(keyeStatus);
				}
				System.out.println("eStatus: " + eStatus);

				//查詢資料
				EmployeeService empSvc = new EmployeeService();
				List<EmployeeVO> emplist = null;
				//模糊查詢或精確查詢
				if("employee_list_search".equals(action)) {
				  emplist = empSvc.getAllbyKeyWord(empno, eName, eTitle, eStatus);
				}else if("employee_list_search2".equals(action)) {
				  emplist = empSvc.getAllbyKeyWord2(empno, eName, eTitle, eStatus);
				}
				
				if(emplist == null) {
					errorMsgs.add("查無結果");
				}
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("emplist", emplist);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_list_search.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************3.search finish, send success view***/
				
				req.setAttribute("emplist", emplist);
//				req.setAttribute("perlist", perlist);
				String url = "/back-end/employee/employee_list_search.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************其他可能的錯誤處理*****************/
			}catch(Exception e) {
				errorMsgs.add("查無結果");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/employee_list_search.jsp");
				failureView.forward(req, res);
			}
		}
////////////////////////////////////////			
		if("employee_one_search".equals(action)) {
			
			try {
				String eName = req.getParameter("ename");
				System.out.println("eName: "+ eName);
				EmployeeService empSvc = new EmployeeService();
				List<EmployeeVO> emplist = empSvc.getAll();
				List<EmployeeVO> newEmpList = emplist.parallelStream().filter(e-> e.geteName().contains(eName))
									  								  .collect(Collectors.toList());
				
				JSONArray json = new JSONArray();
				if(newEmpList != null) {
					for(EmployeeVO emp: newEmpList) {
						JSONObject obj = new JSONObject();
						obj.put("empno",emp.getEmpno());
						obj.put("eName",emp.geteName());
						obj.put("eEmail",emp.geteEmail());
						obj.put("ePhone",emp.getePhone());
						obj.put("eTitle",emp.geteTitle());
						obj.put("eStatus",emp.geteStatus());
						json.put(obj);
					}
				}
				System.out.println("json.length() : "+json.length());
				PrintWriter out = res.getWriter();
				if(newEmpList.isEmpty()) {
					out.print(json.put("查無結果"));
				}else if (newEmpList.size() > 0) {
					out.print(json);
				}
				out.close();
				
			}catch(Exception e) {
				System.out.println("EmployeeServlet搜尋異常: " + e.getMessage());
			}
		}
			
			
		
		//不會用到
		if("delete".equals(action)) { //來自listAllEmployee.jsp
			
			List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************1.receive Parameter***************/
				
				String empno = req.getParameter("empno").toUpperCase();
				String empnoReg = "^E\\d{7}";
				if(empno ==null || empno.trim().length() ==0) {
					
				}else if(!empno.trim().matches(empnoReg)) {
					errorMsgs.add("員工帳號格式不正確，EX：E0000001");
				}
				
				/*************2.delete data*********************/
				EmployeeService empSvc = new EmployeeService();

empSvc.deleteEmployee(empno);
				
				/*************3.delete finish, send success view***/
String url = "/back-end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************其他可能的錯誤處理*****************/
				
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
RequestDispatcher failureView = req.getRequestDispatcher("/back-end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		
	}

}
