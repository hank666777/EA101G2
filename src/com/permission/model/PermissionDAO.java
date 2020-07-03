package com.permission.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PermissionDAO implements PermissionDAO_interface {
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO PERMISSION VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM PERMISSION ORDER BY EMPNO";
	private static final String GET_ONE_BYEMPNO_STMT =
			"SELECT * FROM PERMISSION WHERE EMPNO = ? ORDER BY EMPNO";
	private static final String DELETE_EMPNO = 
			"DELETE FROM PERMISSION WHERE EMPNO = ? AND FEANO = ?";

	
	@Override
	public void insert(PermissionVO permissionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, permissionVO.getEmpno());
			pstmt.setString(2, permissionVO.getFeano());

			pstmt.executeUpdate();
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
		Integer updateCount_EMPs = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE_EMPNO);
			pstmt.setString(1, empno);
			pstmt.setString(2, feano);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	public List<PermissionVO> findBy_empno(String empno) {
		List<PermissionVO> perlist = null;
		PermissionVO perVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
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

		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO perVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				perVO = new PermissionVO();
				perVO.setEmpno(rs.getString(1));
				perVO.setFeano(rs.getString(2));
				list.add(perVO);
			}

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
		return list;
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
//Clean up DAO resources
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

	
	
}
