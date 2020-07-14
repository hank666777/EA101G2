package com.mem.model;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemJDBCDAO implements MemDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G2";
	String passwd = "20200723";
	
	private static final String INSERT_STMT =
		  "INSERT INTO MEMBER (Memno,MAccount,MPw,MPic,Mname,Mgender,Mphone,MEmail,MRegDate,MStatus) VALUES ('M'||LPAD(to_char(member_seq.NEXTVAL), 9,'0'),?,?,?,?,?,?,?,SYSDATE,?)";
	private static final String GET_ALL_STMT = 
		  "SELECT Memno,MAccount,MPw,MPic,Mname,Mgender,Mphone,MEmail,to_char(MRegDate,'yyyy-mm-dd') MRegDate,MStatus FROM member order by memno";
	private static final String GET_ONE_STMT =
		  "SELECT Memno,MAccount,MPw,MPic,Mname,Mgender,Mphone,MEmail,to_char(MRegDate,'yyyy-mm-dd') MRegDate,MStatus FROM member where memno = ?";
	private static final String DELETE = 
		  "DELETE FROM MEMBER where memno = ?";
	private static final String UPDATE = 
		  "UPDATE MEMBER set MAccount=?, MPw=?, MPic=?, Mname=?, Mgender=?, Mphone=?, MEmail=?, MRegDate=?, MStatus=? where Memno= ?";
	private static final String memberLogin = 
			  "UPDATE MEMBER set MStatus=? where Memno= ?";
	//取得圖片方法
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
	@Override
	public String insert(MemVO memVO) {
		int []cols= {1};
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			
			pstmt.setString(1, memVO.getmAccount());
			pstmt.setString(2, memVO.getmPw());	
			pstmt.setBytes(3, memVO.getmPic());	
			pstmt.setString(4, memVO.getmName());
			pstmt.setString(5, memVO.getmGender());
			pstmt.setString(6, memVO.getmPhone());
			pstmt.setString(7, memVO.getmEmail());
			pstmt.setInt(8, memVO.getmStatus());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				String a = rs.getString(1);
				System.out.println(a);
				return a;
			}
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
		return null;	
      }

	@Override
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memVO.getmAccount());
			pstmt.setString(2, memVO.getmPw());	
			pstmt.setBytes(3, memVO.getmPic());	
			pstmt.setString(4, memVO.getmName());
			pstmt.setString(5, memVO.getmGender());
			pstmt.setString(6, memVO.getmPhone());
			pstmt.setString(7, memVO.getmEmail());
			pstmt.setDate(8, memVO.getmRegDate());
			pstmt.setInt(9, memVO.getmStatus());
			pstmt.setString(10, memVO.getMemno());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
	public void delete(String memno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, memno);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
	public MemVO findByPrimarykey(String memno) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, memno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemno(rs.getString("Memno"));
				memVO.setmAccount(rs.getString("MAccount"));
				memVO.setmPw(rs.getString("MPw"));
				memVO.setmPic(rs.getBytes("MPic"));
				memVO.setmName(rs.getString("Mname"));
				memVO.setmGender(rs.getString("Mgender"));
				memVO.setmPhone(rs.getString("Mphone"));
				memVO.setmEmail(rs.getString("MEmail"));
				memVO.setmRegDate(rs.getDate("MRegDate"));
				memVO.setmStatus(rs.getInt("MStatus"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
		return memVO;
	}

	@Override
	public List<MemVO> getALL() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemno(rs.getString("Memno"));
				memVO.setmAccount(rs.getString("MAccount"));
				memVO.setmPw(rs.getString("MPw"));
				memVO.setmPic(rs.getBytes("MPic"));
				memVO.setmName(rs.getString("Mname"));
				memVO.setmGender(rs.getString("Mgender"));
				memVO.setmPhone(rs.getString("Mphone"));
				memVO.setmEmail(rs.getString("MEmail"));
				memVO.setmRegDate(rs.getDate("MRegDate"));
				memVO.setmStatus(rs.getInt("MStatus"));
				list.add(memVO); // Store the row in the list
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
		return list;
	}
	
    public static void main(String[] args) throws IOException {
    	
		MemJDBCDAO dao =new MemJDBCDAO();
		
		//新增
//		MemVO memVO1 = new MemVO();
//		memVO1.setmAccount("peterwu18");
//		memVO1.setmPw("543");
//		
//		byte[] pic = getPictureByteArray("123.png");	
//		memVO1.setmPic(pic);	    
//		
//		memVO1.setmName("吳永志1");
//		memVO1.setmGender("男");
//		memVO1.setmPhone("0911999999");
//		memVO1.setmEmail("peter123@gmail.com");
//		memVO1.setmStatus(1);
//		dao.insert(memVO1);
		
		//修改
//		MemVO memVO1 = new MemVO();
//		memVO1.setmAccount("peterwu1888888");
//		memVO1.setmPw("54300000");
//		
//		byte[] pic = getPictureByteArray("456.png");	
//		memVO1.setmPic(pic);
//		
//		memVO1.setmName("吳永志2");
//		memVO1.setmGender("男");
//		memVO1.setmPhone("0911");
//		memVO1.setmEmail("peeeeeter123@gmail.com");
//		memVO1.setmRegDate(java.sql.Date.valueOf("1990-01-01"));
//		memVO1.setmStatus(0);
//		memVO1.setMemno("M000000011");
//		dao.update(memVO1);
		
//		//刪除
//		dao.delete("M000000011");
		
//		//查詢單一筆資料
//		MemVO memVO1 = dao.findByPrimarykey("M000000010");
//		System.out.println(memVO1.getMemno() + ",");
//		System.out.println(memVO1.getMaccount() + ",");
//		System.out.println(memVO1.getMpw() + ",");
//		System.out.println(memVO1.getMpic() + ",");
//		System.out.println(memVO1.getMname() + ",");
//		System.out.println(memVO1.getMgender() + ",");
//		System.out.println(memVO1.getMphone() + ",");
//		System.out.println(memVO1.getMemail() + ",");
//		System.out.println(memVO1.getMregDate() + ",");
//		System.out.println(memVO1.getMstatus() + ",");
		
	    //查詢所有資料
//		List<MemVO> list = dao.getALL();
//		for(MemVO aMem : list) {
//			System.out.print(aMem.getMemno() + ",");
//			System.out.print(aMem.getmAccount() + ",");
//			System.out.print(aMem.getmPw() + ",");
//			System.out.print(aMem.getmPic() + ",");
//			System.out.print(aMem.getmName() + ",");
//			System.out.print(aMem.getmGender() + ",");
//			System.out.print(aMem.getmPhone() + ",");
//			System.out.print(aMem.getmEmail() + ",");
//			System.out.print(aMem.getmRegDate() + ",");
//			System.out.print(aMem.getmStatus());
//			System.out.println();
//		}
		
		
  }

	@Override
	public MemVO memberLogin(String mAccount, String mPw) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(memberLogin);
			
			pstmt.setString(1, mAccount);
			pstmt.setString(2, mPw);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemno(rs.getString("Memno"));
				memVO.setmAccount(rs.getString("MAccount"));
				memVO.setmPw(rs.getString("MPw"));
				memVO.setmPic(rs.getBytes("MPic"));
				memVO.setmName(rs.getString("Mname"));
				memVO.setmGender(rs.getString("Mgender"));
				memVO.setmPhone(rs.getString("Mphone"));
				memVO.setmEmail(rs.getString("MEmail"));
				memVO.setmRegDate(rs.getDate("MRegDate"));
				memVO.setmStatus(rs.getInt("MStatus"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
		return memVO;
	}

	

	@Override
	public void identify(MemVO memVO) {
		// TODO Auto-generated method stub
		
	}
	
}
