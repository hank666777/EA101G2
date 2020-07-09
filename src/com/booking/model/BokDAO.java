package com.booking.model;
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



import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Booking;

public class BokDAO implements BokDAO_interface{
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO Booking VALUES(to_char(sysdate,'yyyymmdd')||'-B'||LPAD(to_char(Bkno_seq.NEXTVAL),5,'0')," + 
			"?,?,?,?,0,?)";
	private static final String LIVEORDER = "INSERT INTO Booking VALUES(to_char(sysdate,'yyyymmdd')||'-B'||LPAD(to_char(Bkno_seq.NEXTVAL),5,'0')," + 
			"?,?,?,?,1,?)";
	private static final String GET_NEW_INSERT_BKNO = "select (to_char(sysdate,'yyyymmdd')||'-B'||LPAD(to_char(Bkno_seq.currval),5,'0')) as NEWBKNO from dual";
	private static final String UPDATE = "UPDATE Booking SET BkStatus = 1 WHERE BkNo = ? ";
	private static final String CANCEL = "UPDATE Booking SET BkStatus = 2 WHERE BkNo = ? ";
	private static final String DELETE = "DELETE FROM Booking WHERE WHERE BkNo = ? ";
	private static final String GET_ALL = "SELECT * FROM Booking ORDER BY BkDate";
	private static final String GET_BY_MEMNO = "SELECT * FROM Booking WHERE MemNo = ? ORDER BY BkDate";
	private static final String GET_BY_TIME = "SELECT * FROM Booking WHERE BkDate = ? AND BkPeriod = ?";
	private static final String GET_BY_BKNO = "SELECT * FROM Booking WHERE BkNo = ? ";
	
	@Override
	public String insert(BokVO bokvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String newbkno ;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setDate(1, bokvo.getBkDate());
			pstmt.setString(2, bokvo.getBkPeriod());
			pstmt.setInt(3, bokvo.getNumOfPeople());
			pstmt.setInt(4, bokvo.getBkPrice());
			pstmt.setString(5, bokvo.getMemno());
	
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(GET_NEW_INSERT_BKNO);
			rs = pstmt.executeQuery();
			rs.next();
			newbkno = rs.getString("NEWBKNO");
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
		return newbkno ;
		
	}
	
	public String liveOrder(BokVO bokvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String newbkno ;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LIVEORDER);

			pstmt.setDate(1, bokvo.getBkDate());
			pstmt.setString(2, bokvo.getBkPeriod());
			pstmt.setInt(3, bokvo.getNumOfPeople());
			pstmt.setInt(4, bokvo.getBkPrice());
			pstmt.setString(5, bokvo.getMemno());
	
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(GET_NEW_INSERT_BKNO);
			rs = pstmt.executeQuery();
			rs.next();
			newbkno = rs.getString("NEWBKNO");
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
		return newbkno ;
		
	}
	
	@Override
	public void update(String bkno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setString(1, bkno);
				
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
	public void delete(String bkno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
		
			pstmt.setString(1, bkno);
				
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
	public List<BokVO> getAll() {
		List<BokVO> list = new ArrayList<BokVO>();
		BokVO bokVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bokVO = new BokVO();
				bokVO.setBkno(rs.getString("BkNo"));
				bokVO.setBkDate(rs.getDate("BkDate"));
				bokVO.setBkPeriod(rs.getString("BkPeriod"));
				bokVO.setNumOfPeople(rs.getInt("NumberOfPeople"));
				bokVO.setBkPrice(rs.getInt("BkPrice"));
				bokVO.setBkStatus(rs.getInt("BkStatus"));
				bokVO.setMemno(rs.getString("MemNo"));
				
				list.add(bokVO); // Store the row in the list
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
	public List<BokVO> getBokListByMemNo(String memno) {
		List<BokVO> list = new ArrayList<BokVO>();
		BokVO bokVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMNO);
			pstmt.setString(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bokVO = new BokVO();
				bokVO.setBkno(rs.getString("BkNo"));
				bokVO.setBkDate(rs.getDate("BkDate"));
				bokVO.setBkPeriod(rs.getString("BkPeriod"));
				bokVO.setNumOfPeople(rs.getInt("NumberOfPeople"));
				bokVO.setBkPrice(rs.getInt("BkPrice"));
				bokVO.setBkStatus(rs.getInt("BkStatus"));
				bokVO.setMemno(rs.getString("MemNo"));
				
				list.add(bokVO); // Store the row in the list
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
	public List<BokVO> getBokListByTime(Date bkdate, String bkperiod) {
		List<BokVO> list = new ArrayList<BokVO>();
		BokVO bokVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMNO);
			pstmt.setDate(1, bkdate);
			pstmt.setString(2, bkperiod);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bokVO = new BokVO();
				bokVO.setBkno(rs.getString("BkNo"));
				bokVO.setBkDate(rs.getDate("BkDate"));
				bokVO.setBkPeriod(rs.getString("BkPeriod"));
				bokVO.setNumOfPeople(rs.getInt("NumberOfPeople"));
				bokVO.setBkPrice(rs.getInt("BkPrice"));
				bokVO.setBkStatus(rs.getInt("BkStatus"));
				bokVO.setMemno(rs.getString("MemNo"));
				
				list.add(bokVO); // Store the row in the list
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
	public void cancel(String bkno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CANCEL);
		
			pstmt.setString(1, bkno);
				
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
	public List<BokVO> getAll(Map<String, String[]> map) {
		List<BokVO> list = new ArrayList<BokVO>();
		BokVO bokVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from BOOKING "
		          + jdbcUtil_CompositeQuery_Booking.get_WhereCondition(map)
		          + "order by bkdate";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				bokVO = new BokVO();
				bokVO.setBkno(rs.getString("bkno"));
				bokVO.setBkDate(rs.getDate("bkdate"));
				bokVO.setBkPeriod(rs.getString("bkperiod"));
				bokVO.setNumOfPeople(rs.getInt("numberofpeople"));
				bokVO.setBkPrice(rs.getInt("bkprice"));
				bokVO.setBkStatus(rs.getInt("bkstatus"));
				bokVO.setMemno(rs.getString("memno"));
				
				list.add(bokVO); // Store the row in the List
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
	public BokVO getBokByBkNo(String bkno) {
		BokVO bokVO = new BokVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_BKNO);
			pstmt.setString(1, bkno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bokVO.setBkno(rs.getString("BkNo"));
				bokVO.setBkDate(rs.getDate("BkDate"));
				bokVO.setBkPeriod(rs.getString("BkPeriod"));
				bokVO.setNumOfPeople(rs.getInt("NumberOfPeople"));
				bokVO.setBkPrice(rs.getInt("BkPrice"));
				bokVO.setBkStatus(rs.getInt("BkStatus"));
				bokVO.setMemno(rs.getString("MemNo"));
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
		return bokVO;
	}

}
