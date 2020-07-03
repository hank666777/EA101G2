package com.liveOrderDetail.model;

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

public class LiveOrderDetailJNDIDAO implements LiveOrderDetail_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static final String INSERT_STMT = "INSERT INTO LIVEORDERDETAIL (LIVEORDERNO, PNO , PP,LIVEORDERQTY)"
			+ " VALUES (to_char(sysdate,'yyyymmdd')||'-L'||LPAD(to_char(LiveOrderDetail_seq.NEXTVAL), 6, '0'),?,?,?)";
	private static final String UPDATE_STMT = "UPDATE LIVEORDERDETAIL SET  PNO = ?, PP = ?, "
			+ "LIVEORDERQTY = ? WHERE LIVEORDERNO = ?";
	private static final String DELETE_STMT = "DELETE FROM LIVEORDERDETAIL WHERE LIVEORDERNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM LIVEORDERDETAIL WHERE LIVEORDERNO = ?";
	public static final String GET_ALL = "SELECT * FROM LIVEORDERDETAIL ORDER BY LIVEORDERNO DESC";

	@Override
	public void add(LiveOrderDetailVO liveOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,liveOrderDetailVO.getLiveOrderno());
			pstmt.setString(2,liveOrderDetailVO.getPno());
			pstmt.setInt(3,liveOrderDetailVO.getPp());
			pstmt.setInt(4,liveOrderDetailVO.getLiveOrderQty());
			
			pstmt.executeUpdate();
					
		}catch (SQLException e) {
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
	public void update(LiveOrderDetailVO liveOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1,liveOrderDetailVO.getPno());
			pstmt.setInt(2,liveOrderDetailVO.getPp());
			pstmt.setInt(3,liveOrderDetailVO.getLiveOrderQty());
			pstmt.setString(4,liveOrderDetailVO.getLiveOrderno());
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1,liveOrderno);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
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
	public LiveOrderDetailVO findByPK(String liveOrderno) {
		
		LiveOrderDetailVO loddVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, liveOrderno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				loddVO = new LiveOrderDetailVO();
				loddVO.setLiveOrderno(rs.getString("LIVEORDERNO"));
				loddVO.setPno(rs.getString("PNO"));
				loddVO.setPp(rs.getInt("PP"));
				loddVO.setLiveOrderQty(rs.getInt("LIVEORDERQTY"));
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
		return loddVO;
	}

	@Override
	public List<LiveOrderDetailVO> getAll() {
		
		List<LiveOrderDetailVO> loddList = new ArrayList<LiveOrderDetailVO>();
		LiveOrderDetailVO loddVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				loddVO = new LiveOrderDetailVO();
				loddVO.setLiveOrderno(rs.getString("LIVEORDERNO"));
				loddVO.setPno(rs.getString("PNO"));
				loddVO.setPp(rs.getInt("PP"));
				loddVO.setLiveOrderQty(rs.getInt("LIVEORDERQTY"));
				loddList.add(loddVO);
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
		return loddList;
	}
}
