package com.mem.model;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemDAO implements MemDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
    

	private static final String INSERT_STMT =
			  "INSERT INTO MEMBER (Memno,MAccount,MPw,MPic,Mname,Mgender,Mphone,MEmail,MRegDate,MStatus) VALUES ('M'||LPAD(to_char(Memno_seq.NEXTVAL), 9,'0'),?,?,?,?,?,?,?,SYSDATE,?)";
		private static final String GET_ALL_STMT = 
			  "SELECT Memno,MAccount,MPw,MPic,Mname,Mgender,Mphone,MEmail,to_char(MRegDate,'yyyy-mm-dd') MRegDate,MStatus FROM member order by memno";
		private static final String GET_ONE_STMT =
			  "SELECT Memno,MAccount,MPw,MPic,Mname,Mgender,Mphone,MEmail,to_char(MRegDate,'yyyy-mm-dd') MRegDate,MStatus FROM member where memno = ?";
		private static final String DELETE = 
			  "DELETE FROM MEMBER where memno = ?";
		private static final String UPDATE = 
			  "UPDATE MEMBER set MAccount=?, MPw=?, MPic=?, Mname=?, Mgender=?, Mphone=?, MEmail=?,Mregdate=?, MStatus=? where Memno= ?";
		private static final String memberLogin = 
				"SELECT memno,mAccount,mPw,mPic,mName,mGender,mPhone,mEmail,mRegDate,MStatus FROM member where mAccount = ? and  mPw=? ";
		private static final String identify = 
				  "UPDATE MEMBER set MStatus=? where MACCOUNT= ?";
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
			con = ds.getConnection();
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
				String key = rs.getString(1);
				return key;
			}
		} catch (SQLException e) {	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memVO.getmAccount());
			pstmt.setString(2, memVO.getmPw());	
			pstmt.setBytes(3, memVO.getmPic());	
			pstmt.setString(4, memVO.getmName());
			pstmt.setString(5, memVO.getmGender());
			pstmt.setString(6,memVO.getmPhone());
			pstmt.setString(7, memVO.getmEmail());
			pstmt.setDate(8, memVO.getmRegDate());
			pstmt.setInt(9, memVO.getmStatus());
			pstmt.setString(10, memVO.getMemno());

			
			pstmt.executeUpdate();
		} catch (SQLException e) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, memno);
			
			pstmt.executeUpdate();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, memno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemno(rs.getString("memno"));
				memVO.setmAccount(rs.getString("mAccount"));
				memVO.setmPw(rs.getString("mPw"));
				memVO.setmPic(rs.getBytes("mPic"));
				memVO.setmName(rs.getString("mName"));
				memVO.setmGender(rs.getString("mGender"));
				memVO.setmPhone(rs.getString("mPhone"));
				memVO.setmEmail(rs.getString("mEmail"));
				memVO.setmRegDate(rs.getDate("mRegDate"));
				memVO.setmStatus(rs.getInt("mStatus"));
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
         while(rs.next()) {
			memVO = new MemVO();
			memVO.setMemno(rs.getString("memno"));
			memVO.setmAccount(rs.getString("mAccount"));
			memVO.setmPw(rs.getString("mPw"));
			memVO.setmPic(rs.getBytes("mPic"));
			memVO.setmName(rs.getString("mName"));
			memVO.setmGender(rs.getString("mGender"));
			memVO.setmPhone(rs.getString("mPhone"));
			memVO.setmEmail(rs.getString("mEmail"));
			memVO.setmRegDate(rs.getDate("mRegDate"));
			memVO.setmStatus(rs.getInt("mStatus"));
			list.add(memVO); // Store the row in the list
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
	public static void main(String[] args) {
		MemJDBCDAO dao =new MemJDBCDAO();
		List<MemVO> list = dao.getALL();
		for(MemVO aMem : list) {
			System.out.print(aMem.getMemno() + ",");
			System.out.print(aMem.getmAccount() + ",");
			System.out.print(aMem.getmPw() + ",");
			System.out.print(aMem.getmPic() + ",");
			System.out.print(aMem.getmName() + ",");
			System.out.print(aMem.getmGender() + ",");
			System.out.print(aMem.getmName() + ",");
			System.out.print(aMem.getmEmail() + ",");
			System.out.print(aMem.getmRegDate() + ",");
			System.out.print(aMem.getmStatus());
			System.out.println();
		}
	}

	@Override
	public MemVO memberLogin(String mAccount, String mPw) {
		
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(memberLogin);
			
			pstmt.setString(1, mAccount);
			pstmt.setString(2, mPw);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemno(rs.getString("memno"));
				memVO.setmAccount(rs.getString("mAccount"));
				memVO.setmPw(rs.getString("mPw"));
				memVO.setmPic(rs.getBytes("mPic"));
				memVO.setmName(rs.getString("mName"));
				memVO.setmGender(rs.getString("mGender"));
				memVO.setmPhone(rs.getString("mPhone"));
				memVO.setmEmail(rs.getString("mEmail"));
				memVO.setmRegDate(rs.getDate("mRegDate"));
				memVO.setmStatus(rs.getInt("mStatus"));
				
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
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(identify);

			pstmt.setInt(1, memVO.getmStatus());
			pstmt.setString(2,memVO.getmAccount());


			
			pstmt.executeUpdate();
		} catch (SQLException e) {
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


	
}
