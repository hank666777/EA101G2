package com.liveOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LiveOrderJDBCDAO implements LiveOrder_interface {

	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // Oracle≈X∞ ÆM•Û
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER = "EA101_G2";
	public static final String PASSWORD = "123456";

	public static final String INSERT_STMT = "INSERT INTO LIVEORDER (LIVEORDERNO, EMPNO , TABLENO,LIVEORDERTIME,LIVEORDERTOTAL,LIVEORDERPAYMENT,"
			+ "LIVEORDERSTATUS) VALUES (to_char(sysdate,'yyyymmdd')||'-L'||LPAD(to_char(LiveOrderDetail_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE LIVEORDER SET  EMPNO = ?, TABLENO = ?, "
			+ "LIVEORDERTIME = ?, LIVEORDERTOTAL = ?, LIVEORDERPAYMENT = ? ,LIVEORDERSTATUS=? WHERE LIVEORDERNO = ?";
	private static final String DELETE_STMT = "DELETE FROM LIVEORDER WHERE LIVEORDERNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM LIVEORDER WHERE LIVEORDERNO = ?";
	public static final String GET_ALL = "SELECT * FROM LIVEORDER ORDER BY LIVEORDERNO DESC";

	static {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException ce) {
				ce.printStackTrace();
			}
		}

	@Override
	public void add(LiveOrderVO liveOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setString(1, liveOrderVO.getEmpno());
			pstmt.setString(2, liveOrderVO.getTableno());
			pstmt.setTimestamp(3, liveOrderVO.getLiveOrderTime());
			pstmt.setDouble(4, liveOrderVO.getLiveOrderTotal());
			pstmt.setInt(5, liveOrderVO.getLiveOrderPayment());
			pstmt.setInt(6, liveOrderVO.getLiveOrderStatus());
			pstmt.setString(7, liveOrderVO.getLiveOrderno());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, liveOrderno);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
		
		LiveOrderVO lod = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, liveOrderno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lod = new LiveOrderVO();
				lod.setLiveOrderno(rs.getString("LIVEORDERNO"));
				lod.setEmpno(rs.getString("EMPNO"));
				lod.setTableno(rs.getString("TABLENO"));
				lod.setLiveOrderTime(rs.getTimestamp("LIVEORDERTIME"));
				lod.setLiveOrderTotal(rs.getDouble("LIVEORDERTOTAL"));
				lod.setLiveOrderPayment(rs.getInt("LIVEORDERPAYMENT"));
				lod.setLiveOrderStatus(rs.getInt("LIVEORDERSTATUS"));
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
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
		return lod;
	}

	@Override
	public List<LiveOrderVO> getAll() {
		List<LiveOrderVO> lodList = new ArrayList<>();
		LiveOrderVO lod = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lod = new LiveOrderVO();
				lod.setLiveOrderno(rs.getString("LIVEORDERNO"));
				lod.setEmpno(rs.getString("EMPNO"));
				lod.setTableno(rs.getString("TABLENO"));
				lod.setLiveOrderTime(rs.getTimestamp("LIVEORDERTIME"));
				lod.setLiveOrderTotal(rs.getDouble("LIVEORDERTOTAL"));
				lod.setLiveOrderPayment(rs.getInt("LIVEORDERPAYMENT"));
				lod.setLiveOrderStatus(rs.getInt("LIVEORDERSTATUS"));
				lodList.add(lod);
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
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
		return lodList;
	}

}