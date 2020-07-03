package com.mem.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.mem.model.MemVO;

public class loginfilter implements Filter {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G2";
	String passwd = "20200723";
	
	private static final String login = "SELECT MEMNO FROM MEMBER WHERE mAccount=? AND mPw=?";
	
    public loginfilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(login);

			String mAccount = req.getParameter("mAccount");
			String mPw = req.getParameter("mPw");			
			
			
			pstmt.setString(1, mAccount);
			pstmt.setString(2, mPw);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemVO memVO = new MemVO();
				memVO.setMemno(rs.getString("memno"));	
				session.setAttribute("memno", rs.getString("memno"));
			}
			
			Object memno = session.getAttribute("memno");
			
			if(memno == null) {
				session.setAttribute("location",req.getRequestURI());
				res.sendRedirect(req.getContextPath() + "/mem/select_page.jsp");
				return;
			}else{						
				chain.doFilter(request, response);	
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
