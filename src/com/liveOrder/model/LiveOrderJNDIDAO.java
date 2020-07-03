package com.liveOrder.model;

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

public class LiveOrderJNDIDAO implements LiveOrder_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static final String INSERT_STMT = "INSERT INTO LIVEORDER (LIVEORDERNO, EMPNO , TABLENO,LIVEORDERTIME,LIVEORDERTOTAL,LIVEORDERPAYMENT,"
			+ "LIVEORDERSTATUS) VALUES (to_char(sysdate,'yyyymmdd')||'-L'||LPAD(to_char(LiveOrderDetail_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE LIVEORDER SET  EMPNO = ?, TABLENO = ?, "
			+ "LIVEORDERTIME = ?, LIVEORDERTOTAL = ?, LIVEORDERPAYMENT = ? ,LIVEORDERSTATUS=? WHERE LIVEORDERNO = ?";
	private static final String DELETE_STMT = "DELETE FROM LIVEORDER WHERE LIVEORDERNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM LIVEORDER WHERE LIVEORDERNO = ?";
	public static final String GET_ALL = "SELECT * FROM LIVEORDER ORDER BY LIVEORDERNO DESC";

	@Override
	public void add(LiveOrderVO liveOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, liveOrderVO.getEmpno());
			pstmt.setString(2, liveOrderVO.getTableno());
			pstmt.setTimestamp(3, liveOrderVO.getLiveOrderTime());
			pstmt.setDouble(4, liveOrderVO.getLiveOrderTotal());
			pstmt.setInt(5, liveOrderVO.getLiveOrderPayment());
			pstmt.setInt(6, liveOrderVO.getLiveOrderStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public void update(LiveOrderVO liveOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, liveOrderVO.getEmpno());
			pstmt.setString(2, liveOrderVO.getTableno());
			pstmt.setTimestamp(3, liveOrderVO.getLiveOrderTime());
			pstmt.setDouble(4, liveOrderVO.getLiveOrderTotal());
			pstmt.setInt(5, liveOrderVO.getLiveOrderPayment());
			pstmt.setInt(6, liveOrderVO.getLiveOrderStatus());
			pstmt.setString(7, liveOrderVO.getLiveOrderno());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public void delete(String liveOrderno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1,liveOrderno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public LiveOrderVO findByPK(String liveOrderno) {
		
		LiveOrderVO lodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setString(1,liveOrderno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lodVO = new LiveOrderVO();
				lodVO.setLiveOrderno(rs.getString("liveOrderno"));
				lodVO.setEmpno(rs.getString("empno"));
				lodVO.setTableno(rs.getString("tableno"));
				lodVO.setLiveOrderTime(rs.getTimestamp("LIVEORDERTIME"));
				lodVO.setLiveOrderTotal(rs.getDouble("liveOrderTotal"));
				lodVO.setLiveOrderPayment(rs.getInt("liveOrderPayment"));
				lodVO.setLiveOrderStatus(rs.getInt("liveOrderStatus"));
			}
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
		
		return lodVO;
	}

	@Override
	public List<LiveOrderVO> getAll() {
		
		List<LiveOrderVO> list = new ArrayList<LiveOrderVO>();
		LiveOrderVO lodVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lodVO = new LiveOrderVO();
				lodVO.setLiveOrderno(rs.getString("liveOrderno"));
				lodVO.setEmpno(rs.getString("empno"));
				lodVO.setTableno(rs.getString("tableno"));
				lodVO.setLiveOrderTime(rs.getTimestamp("LIVEORDERTIME"));
				lodVO.setLiveOrderTotal(rs.getDouble("liveOrderTotal"));
				lodVO.setLiveOrderPayment(rs.getInt("liveOrderPayment"));
				lodVO.setLiveOrderStatus(rs.getInt("liveOrderStatus"));
				list.add(lodVO);
			}
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
