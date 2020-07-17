package com.ptype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class PTypeJNDIDAO implements PTypeDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT=
			"INSERT INTO PType(pTno, pTName) VALUES(('P'||LPAD(TO_CHAR(PT_SEQ.NEXTVAL),3,'0'),?)";
	private static final String GET_ALL_STMT=
			"SELECT * FROM PTYPE ORDER BY PTNO";
	private static final String GET_ONE_STMT=
			"SELECT pTno, pTName FROM PType WHERE pTno=?";
	private static final String DELETE=
			"DELETE FROM PType WHERE Pno = ?";
	private static final String UPDATE=
			"UPDATE PType set Pname=? WHERE pTno=?";
	
	@Override
	public void insert(PTypeVO PTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, PTypeVO.getpTName());
			
			pstmt.executeUpdate();
		
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
			if(pstmt!= null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void update(PTypeVO PTypeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, PTypeVO.getpTName());
			
			pstmt.executeUpdate();

		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
			if(pstmt!= null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void delete(String pTno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, pTno);
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public PTypeVO findByPrimaryKey(String pTno) {
		PTypeVO PTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, pTno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PTypeVO = new PTypeVO();
				PTypeVO.setpTno(rs.getString("pTno"));

				
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if(rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
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
			return PTypeVO;
		
		
	}
	@Override
	public List<PTypeVO> getAll() {
		List<PTypeVO>list = new ArrayList<PTypeVO>();
		PTypeVO PTypeVO = null;
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con= ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PTypeVO = new PTypeVO();
				PTypeVO.setpTno(rs.getString("pTno"));
				PTypeVO.setpTName(rs.getString("pTName"));
				
				list.add(PTypeVO);
			}

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
		
		
		return list;
	}
}
