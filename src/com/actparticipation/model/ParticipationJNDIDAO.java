package com.actparticipation.model;

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

public class ParticipationJNDIDAO implements ParticipationDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}

	public static final String INSERT_STMT = "INSERT INTO PARTICIPATION (AVTPARTNO, MEMNO, ACTNO, ACTPARTDATE, ACTPARTENR,ACTTALFEE)"
			+"VALUES ((TO_CHAR(SYSDATE,'YYYYMMDD')||'-AP'||LPAD(to_char(Participation_SEQ.NEXTVAL),4,'0'))"
			+ ",?,?,?,?,?)";
	public static final String UPDATE_STMT = "UPDATE PARTICIPATION SET MEMNO = ?, ACTNO = ?, ACTPARTDATE = ?, ACTPARTENR = ?, ACTTALFEE = ? WHERE AVTPARTNO =?";
	public static final String DELETE_STMT = "DELETE FROM PARTICIPATION WHERE AVTPARTNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM PARTICIPATION WHERE AVTPARTNO = ?";
	public static final String GET_ALL = "SELECT * FROM PARTICIPATION";
	
	@Override
	public void add(ParticipationVO ParticipationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);
		

		pstmt.setString(1, ParticipationVO.getMemno());
		pstmt.setString(2, ParticipationVO.getActno());
		pstmt.setTimestamp(3, ParticipationVO.getActPatTime());
		pstmt.setInt(4, ParticipationVO.getActParEnr());
		pstmt.setInt(5, ParticipationVO.getActTalFee());
		
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
	public void update(ParticipationVO ParticipationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE_STMT);
		

		pstmt.setString(1, ParticipationVO.getMemno());
		pstmt.setString(2, ParticipationVO.getActno());
		pstmt.setTimestamp(3, ParticipationVO.getActPatTime());
		pstmt.setInt(4, ParticipationVO.getActParEnr());
		pstmt.setInt(5, ParticipationVO.getActTalFee());
		pstmt.setString(6, ParticipationVO.getAvPartno());
		
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
	public void delete(String avPartno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, avPartno);
			
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
	public ParticipationVO findByPK(String avPartno) {
		
		ParticipationVO AO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, avPartno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AO = new ParticipationVO();
				AO.setAvPartno(rs.getString("AVTPARTNO"));
				AO.setMemno(rs.getString("MEMNO"));
				AO.setActno(rs.getString("ACTNO"));
				AO.setActPatTime(rs.getTimestamp("ACTPARTDATE"));
				AO.setActParEnr(rs.getInt("ACTPARTENR"));
				AO.setActTalFee(rs.getInt("ACTTALFEE"));
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
	public List<ParticipationVO> getAll() {
		List<ParticipationVO> AVOList = new ArrayList<ParticipationVO>();
		ParticipationVO AVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				AVO = new ParticipationVO();
				AVO.setAvPartno(rs.getString("AVTPARTNO"));
				AVO.setMemno(rs.getString("MEMNO"));
				AVO.setActno(rs.getString("ACTNO"));
				AVO.setActPatTime(rs.getTimestamp("ACTPARTDATE"));
				AVO.setActParEnr(rs.getInt("ACTPARTENR"));
				AVO.setActTalFee(rs.getInt("ACTTALFEE"));
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
	public List<String> getAllMemno() {
		List<String> memnoList = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				memnoList.add(rs.getString("MEMNO"));
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
		return memnoList;
	}

	@Override
	public List<ParticipationVO> getAllByMemno() {
		
		List<ParticipationVO> memnoByList = new ArrayList<ParticipationVO>();
		ParticipationVO AVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				AVO.setMemno(rs.getString("MEMNO"));
				memnoByList.add(AVO);
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
		return memnoByList;
	}

}
	
	

