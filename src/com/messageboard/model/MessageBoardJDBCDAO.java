package com.messageboard.model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBoardJDBCDAO implements MessageBoardDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "EA101G2";
	String password = "20200723";
	
	private static final String ADDPOST = 
			"INSERT INTO MessageBoard VALUES ('MB'||LPAD(to_char(postno_seq.NEXTVAL), '8', '0'),?,?,?,?,?,?,?)";
	private static final String REPLYPOST = 
			"INSERT INTO MessageBoard(POSTNO, PARENTNO, POSTDETAIL, POSTTIME, POSTSTATUS, MEMNO) VALUES ('MB'||LPAD(to_char(postno_seq.NEXTVAL), '8', '0'),?,?,?,?,?)";
	private static final String UPDATEPOST = 
			"UPDATE MessageBoard SET POSTTITLE=? , POSTSORT=? , POSTDETAIL=? , POSTSTATUS=? WHERE POSTNO=?";
	private static final String QUERYBYPOSTNO = 
			"SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL, to_char(POSTTIME,'yyyy-mm-dd hh:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE POSTNO=?";
	private static final String QUERY_REPLY =
			"SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL, to_char(POSTTIME,'yyyy-mm-dd hh:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE PARENTNO=?";
	
	private static final String QUERYBYMEMNO =
			"SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL,to_char(POSTTIME,'yyyy-mm-dd hh:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE MEMNO= ?";
	
	private static final String QUERYALL = 
			"SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL, to_char(POSTTIME,'yyyy-mm-dd hh:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard order by POSTNO";
	
	/*private static final String QUERYALL = 
			"SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL, to_char(POSTTIME,'yyyy-mm-dd hh:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE parentno = '0' order  by POSTTIME";*/
	
	private static final String QUERYMAIN = 
			"SELECT POSTNO, PARENTNO, POSTTITLE, POSTSORT, POSTDETAIL,to_char(POSTTIME,'yyyy-mm-dd hh:mi:ss') POSTTIME, POSTSTATUS, MEMNO FROM MessageBoard WHERE parentno = '0'  AND poststatus = '1'  order by POSTTIME DESC";
	
	
	
	
	@Override//新增
	public void add(MessageBoardVO mbVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);//載入驅動
			conn = DriverManager.getConnection(url, user, password);//建立連線
			pstmt = conn.prepareStatement(ADDPOST);
			
			//"INSERT INTO MESSAGEBOARD VALUES (postno_seq.NEXTVAL,?,?,?,?,?,?,?);"
			
			pstmt.setString(1,mbVO.getParentno());
			pstmt.setString(2,mbVO.getPostTitle());
			pstmt.setInt(3, mbVO.getPostSort());
			pstmt.setString(4, mbVO.getPostDetail());
			pstmt.setTimestamp(5, mbVO.getPostTime());
			pstmt.setInt(6, mbVO.getPostStatus());
			pstmt.setString(7,mbVO.getMemno());
			
			pstmt.executeUpdate();
			System.out.println("新增中...");
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		
		
	}

	@Override // 回覆
	public void reply(MessageBoardVO mbVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);//載入驅動
			conn = DriverManager.getConnection(url, user, password);//建立連線
			pstmt = conn.prepareStatement(REPLYPOST);

			// "INSERT INTO MESSAGEBOARD VALUES (postno_seq.NEXTVAL,?,?,?,?,?,?,?);"

			pstmt.setString(1, mbVO.getParentno());
			
			
			pstmt.setString(2, mbVO.getPostDetail());
			pstmt.setTimestamp(3, mbVO.getPostTime());
			pstmt.setInt(4, mbVO.getPostStatus());
			pstmt.setString(5, mbVO.getMemno());

			pstmt.executeUpdate();
			System.out.println("新增中...");

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
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

	
	@Override//更新
	public void update(MessageBoardVO mbVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);//載入驅動
			conn = DriverManager.getConnection(url, user, password);//建立連線
			pstmt = conn.prepareStatement(UPDATEPOST);
			//String updatepost = "UPDATE MESSAGEBOARD SET POSTTITLE = ? , POSTSORT = ? , POSTDETAIL = ? WHERE POSTNO = ?";
		
			pstmt.setString(1, mbVO.getPostTitle());
			pstmt.setInt(2, mbVO.getPostSort());
			pstmt.setString(3, mbVO.getPostDetail());
			pstmt.setInt(4, mbVO.getPostStatus());
			pstmt.setString(5, mbVO.getPostno());
			pstmt.executeUpdate();
			System.out.println("資料修改進行中...");
			
		}
		catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
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
		
	}

	

	@Override//查詢by留言號碼
	public MessageBoardVO getByPostno(String postno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageBoardVO mbVO = null;
	
		try {
			Class.forName(driver);//載入驅動
			conn = DriverManager.getConnection(url, user, password);//建立連線
			pstmt = conn.prepareStatement(QUERYBYPOSTNO);
			//String querysingle = ";";
			
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
								
			}
			
		}catch (ClassNotFoundException cnfe) {
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
			Class.forName(driver);//載入驅動
			conn = DriverManager.getConnection(url, user, password);//建立連線
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
			
		} catch (ClassNotFoundException cnfe) {
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

	
	@Override //查詢by會員號碼
	public List <MessageBoardVO> getByMemno(String memno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageBoardVO mbVO = null;
		List<MessageBoardVO> list = new ArrayList<MessageBoardVO>();
	
		try {
			Class.forName(driver);//載入驅動
			conn = DriverManager.getConnection(url, user, password);//建立連線
			pstmt = conn.prepareStatement(QUERYBYMEMNO);
			
			pstmt.setString(1,memno);
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
			
		} catch (ClassNotFoundException cnfe) {
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
	
	@Override//查詢全部
	public List<MessageBoardVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageBoardVO mbVO = null;
		List<MessageBoardVO> list = new ArrayList<MessageBoardVO>();
		
		try {
			Class.forName(driver);//載入驅動
			conn = DriverManager.getConnection(url, user, password);//建立連線
			pstmt = conn.prepareStatement(QUERYALL);
			
			
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
			
		} catch (ClassNotFoundException cnfe) {
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
	
	

	
	public static void main(String[] args) {
		MessageBoardJDBCDAO mbdao = new MessageBoardJDBCDAO();
		Date rightNow = new Date();
		Long longtime = rightNow.getTime();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		DateFormat adddata = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//		String datestr = adddata.format(rightNow);
//		System.out.println(datestr);
		System.out.println("現在時間:"+timestamp);
//		System.out.println(longtime);
		 //新增
		
//		MessageBoardVO mbVO1 = new MessageBoardVO();
//		
//		mbVO1.setParentno("0");
//		mbVO1.setPostTitle("timestamp test");
//		mbVO1.setPostSort(1);
//		mbVO1.setPostDetail("我想吃甜甜圈");
//		mbVO1.setPostTime(timestamp);
//		mbVO1.setPostStatus(0);//預設1:顯示貼文
//		mbVO1.setMemno("M000000003");
//		mbdao.add(mbVO1);
//		
		
 //回覆
		
		MessageBoardVO mbVO1 = new MessageBoardVO();
		
		mbVO1.setParentno("MB00000026");
		mbVO1.setPostDetail("我想吃甜甜圈");
		mbVO1.setPostTime(timestamp);
		mbVO1.setPostStatus(1);//預設1:顯示貼文
		mbVO1.setMemno("M000000003");
		mbdao.reply(mbVO1);
//		
		
		// 修改
//		MessageBoardVO mbVO2 = new MessageBoardVO();
//		
//		mbVO2.setPostno("MB00000013");
//		mbVO2.setPostTitle("fake title");
//		mbVO2.setPostSort(2);
//		mbVO2.setPostDetail("fake detail");
//		mbVO2.setPostStatus(1);
//		mbdao.update(mbVO2);
		
//
//		// 刪除
//		dao.delete(7014);
//
		//查詢by留言單號
//		MessageBoardVO mbvo3 = mbdao.getByPostno("MB00000017");
//		String postdetail = mbvo3.getPostDetail();
//		String [] pd = splitSupport(postdetail);
//		
//		System.out.print(mbvo3.getPostno() + ",");
//		System.out.print(mbvo3.getParentno() + ",");
//		System.out.print(mbvo3.getPostTitle() + ",");
//		System.out.print(mbvo3.getPostSort() + ",");
//		System.out.print(mbvo3.getPostDetail() +",");
//		System.out.println("src=\"https:"+ pd[3]+",");
//		System.out.print(mbvo3.getPostTime() +",");
//		System.out.println(mbvo3.getPostStatus() + ",");
//		System.out.print(mbvo3.getMemno() + ",");
//		System.out.println("---------------------");

		//查詢by會員號碼
		//
//		List<MessageBoardVO> list = mbdao.getReply("MB00000001");
//		for (MessageBoardVO mbVO : list) {
//			System.out.print(mbVO.getPostno() + ",");
//			System.out.print(mbVO.getParentno() + ",");
//			System.out.print(mbVO.getPostTitle() + ",");
//			System.out.print(mbVO.getPostSort() + ",");
//			System.out.print(mbVO.getPostDetail() + ",");
//			System.out.print(mbVO.getPostTime()+ ",");
//			System.out.print(mbVO.getPostStatus() + ",");
//			System.out.print(mbVO.getMemno() + ",");
//			
//			System.out.println();
//		}
		
//		List<MessageBoardVO> list = mbdao.getAll();
//		
//		for (MessageBoardVO mbVO : list) {
//			String postdetail = mbVO.getPostDetail();
//			String [] img = splitSupport(postdetail);
//			String postimg = null;
//			//System.out.println(img[0]);
//			try{
//				
//				postimg = "src=\"https://"+ img[0]+".jpg";
//			}catch(NullPointerException ne){
//				postimg = "無此字串";
//			}
//			
//			System.out.println(postimg);
//			
//			System.out.print(mbVO.getPostno() + ",");
//			System.out.print(mbVO.getParentno() + ",");
//			System.out.print(mbVO.getPostTitle() + ",");
//			System.out.print(mbVO.getPostSort() + ",");
//			//System.out.print(mbVO.getPostDetail() + ",");
//			System.out.print(mbVO.getPostTime()+ ",");
//			System.out.print(mbVO.getPostStatus() + ",");
//			System.out.println(mbVO.getMemno() + ",");
//			
//			System.out.println();
//		}
		
		
		

	}
	
	private static String[] splitSupport(String postdetail) throws ArrayIndexOutOfBoundsException  {
		String str = postdetail; 
				
		String[] tokens = str.split("//");
		String[] img = null;
		for(int i=0;i<tokens.length;i++) {
			if(tokens[i].contains(".jpg")) {
				img = tokens[i].split(".jpg") ;
				break;
			};
			
		}
		
		return img;
	}

	

	

}
