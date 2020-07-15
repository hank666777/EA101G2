package com.activity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.activity.model.ActivityVO;

public class ActivityDAO implements ActivityDAO_interface{
	
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER = "EA101_G2";
	public static final String PASSWORD = "123456";

	public static final String INSERT_STMT = "INSERT INTO ACTIVITY (ACTNO, ACTTYNO, ACTNAME, ACTDES, ACTPIC, ACTTALPEO,"
			+ "ACTHODATE, ACTSTDATE, ACTEDDATE, ACTFEE, ACTMODE, ACTUPPER, ACTLOWER)"
			+"VALUES ((TO_CHAR(SYSDATE,'YYYYMMDD')||'-A'||LPAD(TO_CHAR(Activity_seq.NEXTVAL),5,'0'))"
			+ ",?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ACTIVITY SET  ACTTYNO = ?, ACTNAME = ?, ACTDES = ?, ACTPIC = ?, "
			+ "ACTTALPEO = ?, ACTHODATE = ?, ACTSTDATE = ?, ACTEDDATE = ?, ACTFEE = ?, ACTMODE = ?, ACTUPPER = ?,"
			+ "ACTLOWER = ? WHERE ACTNO = ?";
	private static final String DELETE_STMT = "DELETE FROM ACTIVITY WHERE ACTNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM ACTIVITY WHERE ACTNO = ?";
	public static final String GET_ALL = "SELECT * FROM ACTIVITY";
	
	static {
		try {
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}
	
	@Override
	public void add(ActivityVO ActivityVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, ActivityVO.getActTyno());
			pstmt.setString(2, ActivityVO.getActName());
			pstmt.setString(3, ActivityVO.getActDes());
			pstmt.setBytes(4, ActivityVO.getActPic());
			pstmt.setInt(5, ActivityVO.getActTalPeo());
			pstmt.setTimestamp(6, ActivityVO.getActHoDate());
			pstmt.setTimestamp(7, ActivityVO.getActStDate());
			pstmt.setTimestamp(8, ActivityVO.getActEdDate());
			pstmt.setInt(9, ActivityVO.getActFee());
			pstmt.setInt(10, ActivityVO.getActMode());
			pstmt.setInt(11, ActivityVO.getActUpper());
			pstmt.setInt(12, ActivityVO.getActLower());

			
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
	public void update(ActivityVO ActivityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, ActivityVO.getActTyno());
			pstmt.setString(2, ActivityVO.getActName());
			pstmt.setString(3, ActivityVO.getActDes());
			pstmt.setBytes(4, ActivityVO.getActPic());
			pstmt.setInt(5, ActivityVO.getActTalPeo());
			pstmt.setTimestamp(6, ActivityVO.getActHoDate());
			pstmt.setTimestamp(7, ActivityVO.getActStDate());
			pstmt.setTimestamp(8, ActivityVO.getActEdDate());
			pstmt.setInt(9, ActivityVO.getActFee());
			pstmt.setInt(10, ActivityVO.getActMode());
			pstmt.setInt(11, ActivityVO.getActUpper());
			pstmt.setInt(12, ActivityVO.getActLower());
			pstmt.setString(13, ActivityVO.getActno());

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
	public void delete(String actno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, actno);
			
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
	public ActivityVO findByPK(String actno) {
		
		ActivityVO AO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, actno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AO = new ActivityVO();
				AO.setActno(rs.getString("ACTNO"));
				AO.setActTyno(rs.getString("ACTTYNO"));
				AO.setActName(rs.getString("ACTNAME"));
				AO.setActDes(rs.getString("ACTDES"));
				AO.setActPic(rs.getBytes("ACTPIC"));
				AO.setActTalPeo(rs.getInt("ACTTALPEO"));
				AO.setActHoDate(rs.getTimestamp("ACTHODATE"));
				AO.setActStDate(rs.getTimestamp("ACTSTDATE"));
				AO.setActEdDate(rs.getTimestamp("ACTEDDATE"));
				AO.setActFee(rs.getInt("ACTFEE"));
				AO.setActMode(rs.getInt("ACTMODE"));
				AO.setActUpper(rs.getInt("ACTUPPER"));
				AO.setActLower(rs.getInt("ACTLOWER"));
				
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
	public List<ActivityVO> getAll() {
		List<ActivityVO> AVOList = new ArrayList<ActivityVO>();
		ActivityVO AVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AVO = new ActivityVO();
				AVO.setActno(rs.getString("ACTNO"));
				AVO.setActTyno(rs.getString("ACTTYNO"));
				AVO.setActName(rs.getString("ACTNAME"));
				AVO.setActDes(rs.getString("ACTDES"));
				AVO.setActPic(rs.getBytes("ACTPIC"));
				AVO.setActTalPeo(rs.getInt("ACTTALPEO"));
				AVO.setActHoDate(rs.getTimestamp("ACTHODATE"));
				AVO.setActStDate(rs.getTimestamp("ACTSTDATE"));
				AVO.setActEdDate(rs.getTimestamp("ACTEDDATE"));
				AVO.setActFee(rs.getInt("ACTFEE"));
				AVO.setActMode(rs.getInt("ACTMODE"));
				AVO.setActUpper(rs.getInt("ACTUPPER"));
				AVO.setActLower(rs.getInt("ACTLOWER"));
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


	@Override
	public void updateTotal(ActivityVO ActivityVO) {
		// TODO Auto-generated method stub
		
	}

}
