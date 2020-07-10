package com.messageboard.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import support.SplitSupport;

public class MessageBoardDAO implements MessageBoardDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TESTDB3");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		
	}

	private static final String ADDPOST = "INSERT INTO MessageBoard VALUES ('MB'||LPAD(to_char(postno_seq.NEXTVAL), '8', '0'),?,?,?,?,?,?,?)";
	private static final String REPLYPOST = 
			"INSERT INTO MessageBoard(POSTNO, PARENTNO, POSTDETAIL, POSTTIME, POSTSTATUS, MEMNO) VALUES ('MB'||LPAD(to_char(postno_seq.NEXTVAL), '8', '0'),?,?,?,?,?)";
	
	private static final String UPDATEPOST = "UPDATE MessageBoard SET POSTTITLE=? , POSTSORT=? , POSTDETAIL=? , POSTSTATUS=? WHERE POSTNO=?";
	private static final String QUERYBYPOSTNO = "SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL, to_char(POSTTIME,'yyyy-mm-dd hh24:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE POSTNO=?";
	private static final String QUERY_REPLY = "SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL, to_char(POSTTIME,'yyyy-mm-dd hh24:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE PARENTNO=? order by POSTTIME" ;	
	private static final String QUERYBYMEMNO = "SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL,to_char(POSTTIME,'yyyy-mm-dd hh24:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE MEMNO= ?";
	private static final String QUERYALL = "SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL, to_char(POSTTIME,'yyyy-mm-dd hh24:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard order by POSTTIME";

	/*
	 * private static final String QUERYALL =
	 * "SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL, to_char(POSTTIME,'yyyy-mm-dd hh:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE parentno = '0' order  by POSTNO"
	 * ;
	 */

	private static final String QUERYMAIN = "SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL,to_char(POSTTIME,'yyyy-mm-dd hh:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE parentno = '0'  AND poststatus = '1'  order by POSTTIME DESC";

	@Override // 新增
	public void add(MessageBoardVO mbVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(ADDPOST);

			// "INSERT INTO MESSAGEBOARD VALUES (postno_seq.NEXTVAL,?,?,?,?,?,?,?);"

			pstmt.setString(1, mbVO.getParentno());
			pstmt.setString(2, mbVO.getPostTitle());
			pstmt.setInt(3, mbVO.getPostSort());
			pstmt.setString(4, mbVO.getPostDetail());
			pstmt.setTimestamp(5, mbVO.getPostTime());
			pstmt.setInt(6, mbVO.getPostStatus());
			pstmt.setString(7, mbVO.getMemno());

			pstmt.executeUpdate();
			System.out.println("新增中...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	}
	
	@Override // 回覆
	public void reply(MessageBoardVO mbVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(REPLYPOST);

			// "INSERT INTO MESSAGEBOARD VALUES (postno_seq.NEXTVAL,?,?,?,?,?,?,?);"

			pstmt.setString(1, mbVO.getParentno());
			
			
			pstmt.setString(2, mbVO.getPostDetail());
			pstmt.setTimestamp(3, mbVO.getPostTime());
			pstmt.setInt(4, mbVO.getPostStatus());
			pstmt.setString(5, mbVO.getMemno());

			pstmt.executeUpdate();
			System.out.println("新增中...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	}

	@Override // 更新
	public void update(MessageBoardVO mbVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(UPDATEPOST);
			// String updatepost = "UPDATE MessageBoard SET POSTTITLE=? , POSTSORT=? , POSTDETAIL=? WHERE POSTNO=?";

			pstmt.setString(1, mbVO.getPostTitle());
			pstmt.setInt(2, mbVO.getPostSort());
			pstmt.setString(3, mbVO.getPostDetail());
			pstmt.setInt(4, mbVO.getPostStatus());
			pstmt.setString(5, mbVO.getPostno());
			pstmt.executeUpdate();
			System.out.println("資料修改進行中...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

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

	}

	@Override // 查詢by留言號碼
	public MessageBoardVO getByPostno(String postno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageBoardVO mbVO = null;

		try {

			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(QUERYBYPOSTNO);

			pstmt.setString(1, postno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mbVO = new MessageBoardVO();

				mbVO.setPostno(rs.getString(1));
				mbVO.setParentno(rs.getString(2));
				mbVO.setPostTitle(rs.getString(3));
				mbVO.setPostSort(rs.getInt(4));
				mbVO.setPostDetail(rs.getString(5));
				mbVO.setPostTime(rs.getTimestamp(6));
				mbVO.setPostStatus(rs.getInt(7));
				mbVO.setMemno(rs.getString(8));

			}

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
		return mbVO;
	}

	@Override //查詢某留言底下回覆
	public List<MessageBoardVO> getReply(String postno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageBoardVO mbVO = null;
		List<MessageBoardVO> list = new ArrayList<MessageBoardVO>();
	
		try {
			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(QUERY_REPLY);
			
			pstmt.setString(1,postno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				mbVO = new MessageBoardVO();
				mbVO.setPostno(rs.getString(1));
				mbVO.setParentno(rs.getString(2));
				mbVO.setPostTitle(rs.getString(3));
				mbVO.setPostSort(rs.getInt(4));
				mbVO.setPostDetail(rs.getString(5));
				mbVO.setPostTime(rs.getTimestamp(6));
				mbVO.setPostStatus(rs.getInt(7));
				mbVO.setMemno(rs.getString(8));
				list.add(mbVO);
				
								
			}
			
		} catch (SQLException e) {
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
	
	@Override // 查詢by會員號碼
	public List<MessageBoardVO> getByMemno(String memno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageBoardVO mbVO = null;
		List<MessageBoardVO> list = new ArrayList<MessageBoardVO>();

		try {
			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(QUERYBYMEMNO);

			pstmt.setString(1, memno);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				mbVO = new MessageBoardVO();
				mbVO.setPostno(rs.getString(1));
				mbVO.setParentno(rs.getString(2));
				mbVO.setPostTitle(rs.getString(3));
				mbVO.setPostSort(rs.getInt(4));
				
				String postdetail = rs.getString(5);
				SplitSupport ss = new SplitSupport();
				String trimhtmldetail = ss.trimHTML(postdetail);
				
				mbVO.setPostDetail(trimhtmldetail);
				mbVO.setPostTime(rs.getTimestamp(6));
				mbVO.setPostStatus(rs.getInt(7));
				mbVO.setMemno(rs.getString(8));
				list.add(mbVO);
				
			}

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
		return list;
	}

	@Override // 查詢全部
	public List<MessageBoardVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageBoardVO mbVO = null;
		List<MessageBoardVO> list = new ArrayList<MessageBoardVO>();

		try {
			conn = ds.getConnection();//取得連線
			pstmt = conn.prepareStatement(QUERYALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				mbVO = new MessageBoardVO();
				mbVO.setPostno(rs.getString(1));
				mbVO.setParentno(rs.getString(2));
				mbVO.setPostTitle(rs.getString(3));
				mbVO.setPostSort(rs.getInt(4));
				
				String postdetail = rs.getString(5);
				String postimg = null;
				try{
					SplitSupport ss =new SplitSupport();
					String [] img = ss.getImgURL(postdetail);
				
				
					postimg = img[0];
					//System.out.println(rs.getString(1)+","+postimg);//比對用
				}catch(NullPointerException ne){				
						postimg = null;				
				}
				mbVO.setPostDetail(postimg);
				
				mbVO.setPostTime(rs.getTimestamp(6));
				mbVO.setPostStatus(rs.getInt(7));
				mbVO.setMemno(rs.getString(8));
				list.add(mbVO);

			}

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
		return list;
	}
	
	
	
}
