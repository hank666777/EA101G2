package com.activitypost.model;

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

public class ActivitypostJNIDAO implements ActivitypostDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
		public static final String INSERT_STMT = "INSERT INTO ACTIVITYPOST (ACTPOSTNO, ACTNO, MEMNO, ACTPOSTDATE, ACTPOSTCON, ACTPOSTPIC)"
				+"VALUES ((TO_CHAR(SYSDATE,'YYYYMMDD')||'-AA'||LPAD(TO_CHAR(ACTIVITYPOST_SEQ.NEXTVAL),4,'0'))"
				+ ",?,?,?,?,?)";
		public static final String UPDATE_STMT = "UPDATE ACTIVITYPOST SET ACTNO = ?, MEMNO = ?, ACTPOSTDATE = ?, ACTPOSTCON = ?, ACTPOSTPIC = ? WHERE ACTPOSTNO =?";
		public static final String DELETE_STMT = "DELETE FROM ACTIVITYPOST WHERE ACTPOSTNO = ?";
		public static final String FIND_BY_PK = "SELECT * FROM ACTIVITYPOST WHERE ACTPOSTNO = ?";
		public static final String GET_ALL = "SELECT * FROM ACTIVITYPOST";
	
	@Override
	public void add(ActivitypostVO ActivitypostVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
		

		pstmt.setString(1, ActivitypostVO.getActno());
		pstmt.setString(2, ActivitypostVO.getMemno());
		pstmt.setTimestamp(3, ActivitypostVO.getActPostDate());
		pstmt.setString(4, ActivitypostVO.getActPostCon());
		pstmt.setBytes(5, ActivitypostVO.getActPostPic());
		
		pstmt.executeUpdate();
		
		}catch(SQLException e) {
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
	public void update(ActivitypostVO ActivitypostVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, ActivitypostVO.getActno());
			pstmt.setString(2, ActivitypostVO.getMemno());
			pstmt.setTimestamp(3, ActivitypostVO.getActPostDate());
			pstmt.setString(4, ActivitypostVO.getActPostCon());
			pstmt.setBytes(5, ActivitypostVO.getActPostPic());
			pstmt.setString(6, ActivitypostVO.getActPostno());


			pstmt.executeUpdate();
			
		}catch(SQLException e) {
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
	public void delete(String actPostno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, actPostno);
			
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
	public ActivitypostVO findByPK(String actPostno) {
		ActivitypostVO AO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, actPostno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AO = new ActivitypostVO();
				AO.setActPostno(rs.getString("ACTPOSTNO"));
				AO.setActno(rs.getString("ACTNO"));
				AO.setMemno(rs.getString("MEMNO"));
				AO.setActPostDate(rs.getTimestamp("ACTPOSTDATE"));
				AO.setActPostCon(rs.getString("ACTPOSTCON"));
				AO.setActPostPic(rs.getBytes("ACTPOStPIC"));
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
		return AO;
	}


	@Override
	public List<ActivitypostVO> getAll() {
		List<ActivitypostVO> AVOList = new ArrayList<ActivitypostVO>();
		ActivitypostVO AVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				AVO = new ActivitypostVO();
				AVO.setActPostno(rs.getString("ACTPOSTNO"));
				AVO.setActno(rs.getString("ACTNO"));
				AVO.setMemno(rs.getString("MEMNO"));
				AVO.setActPostDate(rs.getTimestamp("ACTPOSTDATE"));
				AVO.setActPostCon(rs.getString("ACTPOSTCON"));
				AVO.setActPostPic(rs.getBytes("ACTPOStPIC"));
				AVOList.add(AVO);
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
		return AVOList;
	}

}
