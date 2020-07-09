package com.booking.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class BokJDBCDAO implements BokDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G2";
	String passwd = "123456";
	
	private static final String INSERT = "INSERT INTO Booking VALUES(to_char(sysdate,'yyyymmdd')||'-B'||LPAD(to_char(Bkno_seq.NEXTVAL),5,'0')," + 
			"?,?,?,?,0,?)";
	private static final String LIVEORDER = "INSERT INTO Booking VALUES(to_char(sysdate,'yyyymmdd')||'-B'||LPAD(to_char(Bkno_seq.NEXTVAL),5,'0')," + 
			"?,?,?,?,1,?)";
	private static final String GET_NEW_INSERT_BKNO = "select (to_char(sysdate,'yyyymmdd')||'-B'||LPAD(to_char(Bkno_seq.currval),5,'0')) as NEWBKNO from dual";
	private static final String UPDATE = "UPDATE Booking SET BkStatus = 1 WHERE BkNo = ? ";
	private static final String CANCEL = "UPDATE Booking SET BkStatus = 2 WHERE BkNo = ? ";
	private static final String DELETE = "DELETE FROM Booking WHERE BkNo = ? ";
	private static final String GET_ALL = "SELECT * FROM Booking";
	private static final String GET_BY_MEMNO = "SELECT * FROM Booking WHERE MemNo = ? ";
	private static final String GET_BY_TIME = "SELECT * FROM Booking WHERE BkDate = ? AND BkPeriod = ?";
	
	@Override
	public String insert(BokVO bokvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String newbkno ;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setString(1, bkno);
				
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
		
			pstmt.setString(1, bkno);
				
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_TIME);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CANCEL);
		
			pstmt.setString(1, bkno);
				
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public String liveOrder(BokVO bokvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BokVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BokVO getBokByBkNo(String bkno) {
		// TODO Auto-generated method stub
		return null;
	}

}
