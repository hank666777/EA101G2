package com.permission.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
SELECT EMPLOYEE.EMPNO, EMPLOYEE.ENAME ,PERMISSION.FEANO FROM EMPLOYEE 
LEFT JOIN PERMISSION ON PERMISSION.EMPNO = EMPLOYEE.EMPNO;

SELECT PERMISSION.EMPNO, FEATURES.FEANO, FEATURES.FEANAME FROM FEATURES 
JOIN(SELECT * FROM PERMISSION WHERE EMPNO = 'E0000009') PERMISSION ON PERMISSION.FEANO = FEATURES.FEANO ;

DELETE FROM PERMISSION WHERE EMPNO = 'E0000009' AND FEANO = 'F0010';
 */
public class PermissionJDBCDAO implements PermissionDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G2";
	String password = "20200723";
	
	private static final String INSERT_STMT =
			"INSERT INTO PERMISSION (EMPNO, FEANO) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM PERMISSION ORDER BY EMPNO";
	private static final String GET_ONE_BYEMPNO_STMT =
			"SELECT * FROM PERMISSION WHERE EMPNO = ? ORDER BY FEANO";
	private static final String DELETE_EMPNO = 
			"DELETE FROM PERMISSION WHERE EMPNO = ? AND FEANO = ?";
	
	@Override
	public void insert(PermissionVO permissionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, permissionVO.getEmpno());
			pstmt.setString(2, permissionVO.getFeano());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String empno,String feano) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			
			pstmt = con.prepareStatement(DELETE_EMPNO);
			pstmt.setString(1, empno);
			pstmt.setString(2, feano);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public List<PermissionVO> findBy_empno(String empno) {
		List<PermissionVO> perlist = null;
		PermissionVO perVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_BYEMPNO_STMT);
			
			pstmt.setString(1, empno);
			
			rs = pstmt.executeQuery();
			
			perlist = new ArrayList<PermissionVO>();
			while(rs.next()) {
				perVO = new PermissionVO();
				perVO.setEmpno(rs.getString("EMPNO"));
				perVO.setFeano(rs.getString("FEANO"));
				perlist.add(perVO);
			}
			

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return perlist;
	}
	
	@Override
	public List<PermissionVO> getAll() {

		List<PermissionVO> perlist = new ArrayList<PermissionVO>();
		PermissionVO perVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				perVO = new PermissionVO();
				perVO.setEmpno(rs.getString(1));
				perVO.setFeano(rs.getString(2));
				perlist.add(perVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return perlist;
	}
	
	@Override
	public void insert2(PermissionVO permissionVO, java.sql.Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, permissionVO.getEmpno());
			pstmt.setString(2, permissionVO.getFeano());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if(con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-front-employee");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. +"
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured." + se.getMessage());
			//Clean up JDBC resources
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}
	
	public static void main(String[] args) {
		PermissionJDBCDAO dao = new PermissionJDBCDAO();
		List<PermissionVO> perlist = new ArrayList<PermissionVO>();
		
		//測試成功
//		PermissionVO perVO1 = new PermissionVO();
//		perVO1.setEmpno("E0000009");
//		perVO1.setFeano("F0010");
//		dao.insert(perVO1);
//		System.out.println("新增成功");
		
		//刪除，測試成功
//		dao.delete("E0000009", "F0030");
//		System.out.println("刪除成功");
		
		//測試成功
		
		perlist = dao.findBy_empno("E0000020");
		if(perlist.size() == 0 ) {
			System.out.println("該員工沒有任何權限");
		
		}else {
			for(PermissionVO aPer:perlist) {
				System.out.print(aPer.getEmpno() + " \\ ");
				System.out.println(aPer.getFeano());
			}
			System.out.println("查詢一名員工所有權限成功");
		}
		
		//查所有員工權限
//		List<PermissionVO> list = dao.getAll();
//		for(PermissionVO perVO : list) {
//			System.out.println(perVO.getEmpno());
//			System.out.println(perVO.getFeano());
//		}
//		System.out.println("查詢全部成功"); 
		
		
		
		
	}
	
}
