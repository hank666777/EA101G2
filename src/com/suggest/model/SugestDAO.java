package com.suggest.model;

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

public class SugestDAO implements SugestDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static final String INSERT_STMT = "INSERT INTO RESPONSELIST (suggestno,SuggestDate,SuggestDetail,ResStatus,responseDetail,Memno) VALUES "
			+ "(to_char(sysdate,'yyyymmdd')||'-RL'||LPAD(to_char(Suggestno_seq.NEXTVAL),4,'0'), sysdate, ?,?,?,?)";
	public static final String GET_ALL_STMT = 
			"SELECT SuggestNo,to_char(SuggestDate,'yyyy-mm-dd hh:mm:ss') SuggestDate,SuggestDetail,ResStatus,responseDetail,MemNo from RESPONSELIST order by suggestno ";
	public static final String GET_ONE_STMT = 
			"SELECT SuggestNo,to_char(SuggestDate,'yyyy-mm-dd hh:mm:ss') SuggestDate,SuggestDetail,ResStatus,responseDetail,MemNo from RESPONSELIST where suggestno=? ";
	public static final String DELETE = "DELETE From ResponseList where suggestNo = ?";
	public static final String UPDATE = "UPDATE RESPONSELIST set SuggestDate=?,SuggestDetail=?,ResStatus=?,Memno=?,responseDetail=? where suggestno=? ";
	public static final String GET_MYSUGLIST_STMT = "SELECT * FROM RESPONSELIST where memno=? ORDER BY SUGGESTNO DESC";
	@Override
	public void insert(SugestVO sugestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sugestVO.getSuggestDetail());
			pstmt.setInt(2, sugestVO.getResStatus());
			pstmt.setString(3, sugestVO.getResponseDetail());
			pstmt.setString(4, sugestVO.getMemno());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(SugestVO sugestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, sugestVO.getSuggestDate());
			pstmt.setString(2, sugestVO.getSuggestDetail());
			pstmt.setInt(3, sugestVO.getResStatus());
			pstmt.setString(4, sugestVO.getResponseDetail());
			pstmt.setString(5, sugestVO.getMemno());
			pstmt.setString(6, sugestVO.getSuggestno());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(String sugetNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sugetNo);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

	@Override
	public SugestVO findbyPrimarykey(String suggestno) {
		SugestVO sugestVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, suggestno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sugestVO = new SugestVO();
				sugestVO.setSuggestno(rs.getString("SuggestNo"));
				sugestVO.setSuggestDate(rs.getTimestamp("SuggestDate"));
				sugestVO.setSuggestDetail(rs.getString("SuggestDetail"));
				sugestVO.setResStatus(rs.getInt("ResStatus"));
				sugestVO.setResponseDetail(rs.getString("responseDetail"));
				sugestVO.setMemno(rs.getString("MemNo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		return sugestVO;
	}

	@Override
	public List<SugestVO> getAll() {
		List<SugestVO> list = new ArrayList<SugestVO>();
		SugestVO sugestVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sugestVO = new SugestVO();
				sugestVO.setSuggestno(rs.getString("SuggestNo"));
				sugestVO.setSuggestDate(rs.getTimestamp("SuggestDate"));
				sugestVO.setSuggestDetail(rs.getString("SuggestDetail"));
				sugestVO.setResStatus(rs.getInt("ResStatus"));
				sugestVO.setResponseDetail(rs.getString("responseDetail"));
				sugestVO.setMemno(rs.getString("MemNo"));
				list.add(sugestVO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		return list;
	}

	@Override
	public List<SugestVO> findbyMemno(String memno) {
		List<SugestVO> list = new ArrayList<SugestVO>();
		SugestVO sugestVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MYSUGLIST_STMT);
			
			pstmt.setString(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sugestVO = new SugestVO();
				sugestVO.setSuggestno(rs.getString("SuggestNo"));
				sugestVO.setSuggestDate(rs.getTimestamp("SuggestDate"));
				sugestVO.setSuggestDetail(rs.getString("SuggestDetail"));
				sugestVO.setResStatus(rs.getInt("ResStatus"));
				sugestVO.setResponseDetail(rs.getString("RESPONSEDETAIL"));
				sugestVO.setMemno(rs.getString("MemNo"));
				list.add(sugestVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		return list;
	}

//	public static void main(String[] args) {
//		
//		SugestDAO dao = new SugestDAO();		
//		SugestVO sugestVO = new SugestVO();
//		sugestVO.setSuggestDetail("我覺得很棒");
//		sugestVO.setResStatus(0);
//		
//		dao.insert(sugestVO);
//	}

}
