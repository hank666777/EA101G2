package com.messagereport.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.messageboard.model.MessageBoardJDBCDAO;
import com.messageboard.model.MessageBoardVO;

public class MessageReportDAO implements MessageReportDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TESTDB3");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		
	}

	private static final String ADDREPORT = "INSERT INTO messagereport VALUES ('MR'||LPAD(to_char(reportno_seq.NEXTVAL), 3, '0'),?,?,?,?,?)";
	private static final String UPDATESTATUS = "UPDATE messagereport SET REPORTSTATUS=? WHERE REPORTNO=?";
	private static final String QUERYSINGLE = "SELECT REPORTNO, REPORTDETAIL, to_char(REPORTTIME,'yyyy-mm-dd hh:mi:ss') REPORTTIME, REPORTSTATUS, MEMNO, POSTNO FROM messagereport WHERE REPORTNO=?";
	private static final String QUERYALL = "SELECT * FROM messagereport ORDER BY reportstatus,reporttime ";

	@Override // 新增
	public void add(MessageReportVO mrVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(ADDREPORT);
			// 
			
			pstmt.setString(1, mrVO.getReportDetail());
			pstmt.setTimestamp(2, mrVO.getReportTime());
			pstmt.setInt(3, mrVO.getReportStatus());
			pstmt.setString(4, mrVO.getMemno());
			pstmt.setString(5, mrVO.getPostno());

			pstmt.executeUpdate();
			System.out.println("新增中...");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override // 修改
	public void updateStatus(MessageReportVO mrVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(UPDATESTATUS);
			// "UPDATE MESSAGEREPORT SET REPORTSTATUS=? WHERE REPORTNO=?;"

			pstmt.setInt(1, mrVO.getReportStatus());
			pstmt.setString(2, mrVO.getReportno());
			
			pstmt.executeUpdate();
			System.out.println("資料修改進行中...");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override // 查詢單號
	public MessageReportVO getByno(String reportno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageReportVO mrVO = null;

		try {
			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(QUERYSINGLE);
			// querysingle = "SELECT REPORTNO, MEMNO, POSTNO, REPORTDETAIL,
			// to_char(REPORTDATE,'yyyy-mm-dd') REPORTDATE, REPORTSTATUS FROM MESSAGEREPORT
			// WHERE REPORTNO=?;";

			pstmt.setString(1, reportno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mrVO = new MessageReportVO();
				mrVO.setReportno(rs.getString(1));				
				mrVO.setReportDetail(rs.getString(2));
				mrVO.setReportTime(rs.getTimestamp(3));
				mrVO.setReportStatus(rs.getInt(4));
				mrVO.setMemno(rs.getString(5));
				mrVO.setPostno(rs.getString(6));

			}

		}  catch (SQLException e) {
			e.printStackTrace();
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return mrVO;
	}

	@Override // 查詢全部
	public List<MessageReportVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageReportVO mrVO = null;
		
		List<MessageReportVO> list = new ArrayList<MessageReportVO>();

		try {
			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(QUERYALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				mrVO = new MessageReportVO();
				mrVO.setReportno(rs.getString(1));				
				mrVO.setReportDetail(rs.getString(2));
				mrVO.setReportTime(rs.getTimestamp(3));
				mrVO.setReportStatus(rs.getInt(4));
				mrVO.setMemno(rs.getString(5));
				mrVO.setPostno(rs.getString(6));
				list.add(mrVO);

			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
			

		return list;
	}

}
