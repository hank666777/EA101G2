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

import com.messageboard.model.MessageBoardJDBCDAO;
import com.messageboard.model.MessageBoardVO;

public class MessageReportJDBCDAO implements MessageReportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "EA101G2";
	String password = "20200723";

	private static final String ADDREPORT = "INSERT INTO messagereport VALUES ('MR'||LPAD(to_char(reportno_seq.NEXTVAL), 3, '0'),?,?,?,?,?)";
	private static final String UPDATESTATUS = "UPDATE messagereport SET REPORTSTATUS=? WHERE REPORTNO=?";
	private static final String QUERYSINGLE = "SELECT REPORTNO, REPORTDETAIL, to_char(REPORTTIME,'yyyy-mm-dd hh:mi:ss') REPORTTIME, REPORTSTATUS, MEMNO, POSTNO FROM messagereport WHERE REPORTNO=?";
	private static final String QUERYALL = "SELECT * FROM messagereport ORDER BY reportstatus,reporttime ";

	@Override // 新增
	public void add(MessageReportVO mrVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(ADDREPORT);
			// 
			
			pstmt.setString(1, mrVO.getReportDetail());
			pstmt.setTimestamp(2, mrVO.getReportTime());
			pstmt.setInt(3, mrVO.getReportStatus());
			pstmt.setString(4, mrVO.getMemno());
			pstmt.setString(5, mrVO.getPostno());

			pstmt.executeUpdate();
			System.out.println("新增中...");

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(UPDATESTATUS);
			// "UPDATE MESSAGEREPORT SET REPORTSTATUS=? WHERE REPORTNO=?;"

			pstmt.setInt(1, mrVO.getReportStatus());
			pstmt.setString(2, mrVO.getReportno());
			
			pstmt.executeUpdate();
			System.out.println("資料修改進行中...");

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			Class.forName(driver);// 載入驅動
			conn = DriverManager.getConnection(url, user, password);// 建立連線
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

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException e) {
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
			Class.forName(driver);//載入驅動
			conn = DriverManager.getConnection(url, user, password);
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

		}  catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
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

	public static void main(String[] arg0) {
		MessageReportJDBCDAO mrdao = new MessageReportJDBCDAO();
		Date rightNow = new Date();
		Long longtime = rightNow.getTime();
		Timestamp timestamp = new Timestamp(longtime);
		// 新增
//		MessageReportVO mrVO1 = new MessageReportVO();
//		mrVO1.setReportdetail("連續發文已處理未通過2");
//		mrVO1.setReporttime(timestamp);
//		mrVO1.setReportstatus(2);//預設0:未處理
//		mrVO1.setMemno("M000000003");
//		mrVO1.setPostno("MB00000002");
//				
//		mrdao.add(mrVO1);
//		

		// 修改
		MessageReportVO mrVO2 = new MessageReportVO();
		
		mrVO2.setReportno("MR010");
		mrVO2.setReportStatus(0);
		mrdao.updateStatus(mrVO2);
		
//
//		// 刪除
//		dao.delete(7014);
//
		// 查詢單號
//		MessageReportVO mrVO3 = mrdao.getByNo("MR004");
//		//
//		System.out.print(mrVO3.getReportno() + ",");
//		System.out.print(mrVO3.getReportdetail() + ",");
//		System.out.print(mrVO3.getReporttime() + ",");
//		System.out.println(mrVO3.getReportstatus() + ",");
//		System.out.print(mrVO3.getMemno() + ",");
//		System.out.print(mrVO3.getPostno() + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<MessageReportVO> list = mrdao.getAll();
//		for (MessageReportVO mrVO : list) {
//			System.out.print(mrVO.getReportno() + ",");
//			System.out.print(mrVO.getReportdetail() + ",");
//			System.out.print(mrVO.getReporttime() + ",");
//			System.out.print(mrVO.getReportstatus() + ",");
//			System.out.print(mrVO.getMemno() + ",");
//			System.out.print(mrVO.getPostno() + ",");
//			
//			System.out.println();
//		}
//
	}

}
