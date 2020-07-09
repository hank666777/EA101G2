package com.bookingdetail.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.booking.model.BokVO;

import jdbcUtil_CompositeQuery.jdbcUtil_CompositeQuery_BookingDetail;



public class BokdtDAO implements BokdtDAO_interface {
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G2";
	String passwd = "123456";
	
	private static final String INSERT = "INSERT INTO bookingDetail VALUES ('BD'||LPAD(TO_CHAR(BkDetailno_seq.NEXTVAL),4,'0'), ? , ? , ? , ? ,0)";
	private static final String DELETE_BY_BKNO = "DELETE FROM BookingDetail WHERE BkNo = ?";
	private static final String GET_ALL = "SELECT * FROM BookingDetail";
	private static final String GET_BY_BKNO = "SELECT * FROM BookingDetail WHERE BkNo = ?";
	private static final String GET_BY_TIME = "SELECT * FROM BookingDetail WHERE BkDate = ? AND BkPeriod = ?";
	private static final String UPDATE = "UPDATE bookingdetail SET orderstatus = 1 WHERE bkdetailno = ?";
	
	@Override
	public void insert(BokdtVO bokdtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setDate(1, bokdtVO.getBkDate());
			pstmt.setString(2, bokdtVO.getBkPeriod());
			pstmt.setString(3, bokdtVO.getTableno());
			pstmt.setString(4, bokdtVO.getBkno());
	
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
	public void deleteByBkNo(String bkNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BY_BKNO);

			pstmt.setString(1, bkNo);
	
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
	public List<BokdtVO> getAll() {
		List<BokdtVO> list = new ArrayList<BokdtVO>();
		BokdtVO bokdtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bokdtVO = new BokdtVO();
				bokdtVO.setBkDetailno(rs.getString("BkDetailNo"));
				bokdtVO.setBkDate(rs.getDate("BkDate"));
				bokdtVO.setBkPeriod(rs.getString("BkPeriod"));
				bokdtVO.setTableno(rs.getString("TableNo"));
				bokdtVO.setBkno(rs.getString("BkNo"));
				
				list.add(bokdtVO); // Store the row in the list
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<BokdtVO> getBokdtListByBkNo(String bkNo) {
		List<BokdtVO> list = new ArrayList<BokdtVO>();
		BokdtVO bokdtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_BKNO);
			pstmt.setString(1, bkNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bokdtVO = new BokdtVO();
				bokdtVO.setBkDetailno(rs.getString("BkDetailNo"));
				bokdtVO.setBkDate(rs.getDate("BkDate"));
				bokdtVO.setBkPeriod(rs.getString("BkPeriod"));
				bokdtVO.setTableno(rs.getString("TableNo"));
				bokdtVO.setBkno(rs.getString("BkNo"));
				
				list.add(bokdtVO); // Store the row in the list
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<BokdtVO> getBokdtListByTime(Date bkDate, String bkPeriod) {
		List<BokdtVO> list = new ArrayList<BokdtVO>();
		BokdtVO bokdtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_TIME);
			pstmt.setDate(1, bkDate);
			pstmt.setString(2, bkPeriod);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bokdtVO = new BokdtVO();
				bokdtVO.setBkDetailno(rs.getString("BkDetailNo"));
				bokdtVO.setBkDate(rs.getDate("BkDate"));
				bokdtVO.setBkPeriod(rs.getString("BkPeriod"));
				bokdtVO.setTableno(rs.getString("TableNo"));
				bokdtVO.setBkno(rs.getString("BkNo"));
				
				list.add(bokdtVO); // Store the row in the list
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(String bkdtNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, bkdtNo);
	
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
	public List<BokdtVO> getAll(Map<String, String[]> map) {
		List<BokdtVO> list = new ArrayList<BokdtVO>();
		BokdtVO bokdtVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from BOOKINGDETAIL "
		          + jdbcUtil_CompositeQuery_BookingDetail.get_WhereCondition(map)
		          + "order by bkdate";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				bokdtVO = new BokdtVO();
				bokdtVO.setBkDetailno(rs.getString("BkDetailno"));
				bokdtVO.setBkno(rs.getString("bkno"));
				bokdtVO.setBkDate(rs.getDate("bkdate"));
				bokdtVO.setBkPeriod(rs.getString("bkperiod"));
				bokdtVO.setTableno(rs.getString("tableno"));			
				bokdtVO.setOrderStatus(rs.getInt("OrderStatus"));			
				list.add(bokdtVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
}
