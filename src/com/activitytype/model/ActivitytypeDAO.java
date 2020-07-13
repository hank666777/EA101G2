package com.activitytype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivitytypeDAO implements ActivitytypeDAO_interface{
	

	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // Oracle�X�ʮM��
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER = "EA101_G2";
	public static final String PASSWORD = "123456";
	
	public static final String INSERT_STMT = "INSERT INTO ACTIVITYTYPE (ACTTYNO, ACTTYNAME)"
			+"VALUES ('AT'||LPAD(to_char(ActivityType_seq.NEXTVAL),3,'0'),?)";
	private static final String UPDATE_STMT = "UPDATE ACTIVITYTYPE SET  ACTTYNAME = ? WHERE ACTTYNO = ?";
	private static final String DELETE_STMT = "DELETE FROM ACTIVITYTYPE WHERE ACTTYNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM ACTIVITYTYPE WHERE ACTTYNO = ?";
	public static final String GET_ALL = "SELECT * FROM ACTIVITYTYPE";
		
		static {
			try {
				Class.forName(DRIVER);
			}catch(ClassNotFoundException ce){
				ce.printStackTrace();
			}
		}
	
	@Override
	public void add(ActivitytypeVO actypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, actypeVO.getActTyName());
			
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
	public void update(ActivitytypeVO actypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, actypeVO.getActTyName());
			pstmt.setString(2, actypeVO.getActtyNo());

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
	public void delete(String acttyNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, acttyNo);
			
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
	public ActivitytypeVO findByPK(String ActtyNo) {
		
		ActivitytypeVO AP = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, ActtyNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AP = new ActivitytypeVO();
				AP.setActtyNo(rs.getString("ACTTYNO"));
				AP.setActTyName(rs.getString("ACTTYNAME"));
				
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
		return AP;
	}

	@Override
	public List<ActivitytypeVO> getAll() {
		List<ActivitytypeVO> AVOList = new ArrayList<ActivitytypeVO>();
		ActivitytypeVO AVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AVO = new ActivitytypeVO();
				AVO.setActtyNo(rs.getString("ACTTYNO"));
				AVO.setActTyName(rs.getString("ACTTYNAME"));
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