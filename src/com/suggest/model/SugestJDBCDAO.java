package com.suggest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SugestJDBCDAO implements SugestDAO_interface {
	
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "EA101G2";
		String password = "20200723";
		
	    //suggestDate使用動態產生
		public static final String INSERT_STMT = 
		     "INSERT INTO RESPONSELIST (SuggestNo,SuggestDate,SuggestDetail,ResStatus,MemNo) VALUES (to_char(sysdate,'yyyymmdd')||'-RL'||LPAD(to_char(Suggestno_seq.NEXTVAL),4,'0'),SYSDATE,?,?,?)";
		public static final String GET_ALL_STMT =
		     "SELECT SuggestNo,to_char(SuggestDate,'yyyy-mm-dd hh:mm:ss') SuggestDate,SuggestDetail,ResStatus,MemNo from ResponseList ";
		public static final String GET_ONE_STMT =
			 "SELECT SuggestNo,to_char(SuggestDate,'yyyy-mm-dd hh:mm:ss') SuggestDate,SuggestDetail,ResStatus,Memno from RESPONSELIST where suggestNo= ?";
		public static final String DELETE = 
			 "DELETE From ResponseList where suggestNo = ?";
		public static final String UPDATE =
			 "UPDATE ResponseList set suggestDate=?,suggestDetail=?,resStatus=?, memNo=? where suggestNO= ? ";

	@Override
	public void insert(SugestVO sugestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			 pstmt.setString(1,sugestVO.getSuggestDetail());
			 pstmt.setInt(2,sugestVO.getResStatus());
			 pstmt.setString(3, sugestVO.getMemno());
			 
			 pstmt.executeUpdate();
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
		}
		
	}

	@Override
	public void update(SugestVO sugestVO) {
		Connection con = null;
		PreparedStatement pstmt= null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setTimestamp(1, sugestVO.getSuggestDate());
			pstmt.setString(2, sugestVO.getSuggestDetail());
			pstmt.setInt(3, sugestVO.getResStatus());
			pstmt.setString(4, sugestVO.getMemno());
			pstmt.setString(5, sugestVO.getSuggestno());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}	
	}

	@Override
	public void delete(String sugetNo) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, sugetNo);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			} 
		}
	
	}

	@Override
	public SugestVO findbyPrimarykey(String sugetNo) {
		
		SugestVO sugestVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, sugetNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				  sugestVO = new SugestVO();
				  sugestVO.setSuggestno(rs.getString("SuggestNo"));
				  sugestVO.setSuggestDate(rs.getTimestamp("SuggestDate"));
				  sugestVO.setSuggestDetail(rs.getString("SuggestDetail"));
				  sugestVO.setResStatus(rs.getInt("ResStatus"));
				  sugestVO.setMemno(rs.getString("MemNo"));
				}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(rs!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			} 	
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			} 
		}
      return sugestVO;
	}

	@Override
	public List<SugestVO> getAll() {
		List<SugestVO> list = new ArrayList <SugestVO> ();
		SugestVO sugestVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
		while(rs.next()) {
			sugestVO = new SugestVO();
		    sugestVO.setSuggestno(rs.getString("SuggestNo"));
			sugestVO.setSuggestDate(rs.getTimestamp("SuggestDate"));
			sugestVO.setSuggestDetail(rs.getString("SuggestDetail"));
			sugestVO.setResStatus(rs.getInt("ResStatus"));
			sugestVO.setMemno(rs.getString("MemNo"));		
			list.add(sugestVO);
		}
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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

	public static void main(String[] args) {
		
		SugestJDBCDAO dao = new SugestJDBCDAO();
		
		SugestVO sugestVO = new SugestVO();
		
		//新增
		sugestVO.setSuggestDetail("建議送餐速度加快");
		sugestVO.setResStatus(1);
		sugestVO.setMemno("M000000005");
		dao.insert(sugestVO);
		
		//修改
//		sugetVO.setSuggestDate(java.sql.Timestamp.valueOf("2020-01-01 11:48:59"));
//		sugetVO.setSuggestDetail("建議送餐速度加快2");
//		sugetVO.setResStatus("2");
//		sugetVO.setMemNo("M000000005");
//		sugetVO.setSuggestNo("20200615-RL0003");
//		dao.update(sugetVO);
		
		//刪除
		dao.delete("20200615-RL0003");
		
		//查詢單一資料
//		sugetVO = dao.findbyPrimarykey("輸入一個suggestNo");
//		System.out.println(sugetVO.getSuggestNo()+",");
//		System.out.println(sugetVO.getSuggestDate()+",");;
//		System.out.println(sugetVO.getSuggestDetail()+",");
//		System.out.println(sugetVO.getMemNo());
		
		//查詢全部資料
		 List <SugestVO> list = dao.getAll();
	        for(SugestVO asuget : list) {
	        	System.out.print(asuget.getSuggestno()+",");
	        	System.out.print(asuget.getSuggestDate()+",");
	        	System.out.print(asuget.getSuggestDetail()+",");
	        	System.out.print(asuget.getResStatus()+",");
	        	System.out.print(asuget.getMemno());
	        	System.out.println();
	        }
	}


	@Override
	public List<SugestVO> findbyMemno(String memno) {
		// TODO Auto-generated method stub
		return null;
	}
}
