package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jdbcUtil_CompositeQuery.jdbcUtil_CompositeQuery_Product;

public class ProductJDBCDAO implements Product_interface{
	
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // Oracle�X�ʮM��
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER = "EA101_G2";
	public static final String PASSWORD = "123456";
	
	public static final String INSERT_STMT = "INSERT INTO PRODUCT (PNO,PNAME,PP,PPIC,PDES,PDOFFER,"
			+ "INVSTATUS,PSTATUS,PTNO) VALUES ('P'||LPAD(TO_CHAR(Pno_SEQ.NEXTVAL),4,'0'),?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE PRODUCT SET  PNAME = ?, PP = ?, PPIC=?,PDES=?,"
			+ "PDOFFER = ?, INVSTATUS = ?, PSTATUS = ?,PTNO = ? WHERE PNO = ?";
	private static final String DELETE_STMT = "DELETE FROM PRODUCT WHERE PNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM PRODUCT WHERE PNO = ?";
	public static final String GET_ALL = "SELECT * FROM PRODUCT ORDER BY PNO";
	private static final String GET_ALL_STATUS = "SELECT * FROM PRODUCT where pStatus=?";
	private static final String FIND_BY_PNAME = "SELECT * FROM PRODUCT where pname=?";
	private static final String GET_ALL_STATUSANDTYPE = "SELECT * FROM PRODUCT where pStatus=? and pTno=?";
	public static final String GET_CATEGORY = "SELECT * FROM PRODUCT WHERE PTNO = ?";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void add(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productVO.getpname());
			pstmt.setInt(2,productVO.getpP());
			pstmt.setBytes(3,productVO.getpPic());
			pstmt.setString(4,productVO.getpDes());
			pstmt.setInt(5,productVO.getpDoffer());
			pstmt.setInt(6,productVO.getINVStatus());
			pstmt.setInt(7,productVO.getpStatus());
			pstmt.setString(8,productVO.getpTno());
			
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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, productVO.getpname());
			pstmt.setInt(2,productVO.getpP());
			pstmt.setBytes(3,productVO.getpPic());
			pstmt.setString(4,productVO.getpDes());
			pstmt.setInt(5,productVO.getpDoffer());
			pstmt.setInt(6,productVO.getINVStatus());
			pstmt.setInt(7,productVO.getpStatus());
			pstmt.setString(8,productVO.getpTno());
			pstmt.setString(9, productVO.getpno());
			
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
	public void delete(String Pno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, Pno);
			
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
	public ProductVO findByPK(String pno) {
		
		ProductVO pd = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, pno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pd = new ProductVO();
				pd.setpno(rs.getString("PNO"));
				pd.setpname(rs.getString("PNAME"));
				pd.setpP(rs.getInt("PP"));
				pd.setpPic(rs.getBytes("PPIC"));
				pd.setpDes(rs.getString("PDES"));
				pd.setpDoffer(rs.getInt("PDOFFER"));
				pd.setINVStatus(rs.getInt("INVSTATUS"));
				pd.setpStatus(rs.getInt("PSTATUS"));
				pd.setpTno(rs.getString("PTNO"));
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
		return pd;
	}

	@Override
	public List<ProductVO> getAll() {
		
		List<ProductVO> pdList = new ArrayList<ProductVO>();
		ProductVO pd = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pd = new ProductVO();
				pd.setpno(rs.getString("PNO"));
				pd.setpname(rs.getString("PNAME"));
				pd.setpP(rs.getInt("PP"));
				pd.setpPic(rs.getBytes("PPIC"));
				pd.setpDes(rs.getString("PDES"));
				pd.setpDoffer(rs.getInt("PDOFFER"));
				pd.setINVStatus(rs.getInt("INVSTATUS"));
				pd.setpStatus(rs.getInt("PSTATUS"));
				pd.setpTno(rs.getString("PTNO"));
				pdList.add(pd);
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
		return pdList;
	}

	@Override
	public List<ProductVO> getProductByStatus(int pStatus) {
		List<ProductVO> pdList = new ArrayList<ProductVO>();
		ProductVO pd = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STATUS);
			pstmt.setInt(1, pStatus);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pd = new ProductVO();
				pd.setpno(rs.getString("PNO"));
				pd.setpname(rs.getString("PNAME"));
				pd.setpP(rs.getInt("PP"));
				pd.setpDes(rs.getString("PDES"));
				pd.setpDoffer(rs.getInt("PDOFFER"));
				pd.setINVStatus(rs.getInt("INVSTATUS"));
				pd.setpTno(rs.getString("PTNO"));
				pdList.add(pd);
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
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
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return pdList;
	}

	

	@Override
	public ProductVO findByName(String pname) {
		
		ProductVO pd = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PNAME);
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pd = new ProductVO();
				pd.setpno(rs.getString("PNO"));
				pd.setpname(rs.getString("PNAME"));
				pd.setpP(rs.getInt("PP"));
				pd.setpPic(rs.getBytes("PPIC"));
				pd.setpDes(rs.getString("PDES"));
				pd.setpDoffer(rs.getInt("PDOFFER"));
				pd.setINVStatus(rs.getInt("INVSTATUS"));
				pd.setpStatus(rs.getInt("PSTATUS"));
				pd.setpTno(rs.getString("PTNO"));
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
		return pd;
	}

	@Override
	public List<ProductVO> getProductByStatusAndType(int pStatus, String pTno) {
		
		List<ProductVO> pdList = new ArrayList<ProductVO>();
		ProductVO pd = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STATUSANDTYPE);
			pstmt.setInt(1, pStatus);
			pstmt.setString(2, pTno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pd = new ProductVO();
				pd.setpno(rs.getString("PNO"));
				pd.setpname(rs.getString("PNAME"));
				pd.setpP(rs.getInt("PP"));
				pd.setpDes(rs.getString("PDES"));
				pd.setpDoffer(rs.getInt("PDOFFER"));
				pd.setINVStatus(rs.getInt("INVSTATUS"));
				pdList.add(pd);
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
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
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return pdList;
	}

	@Override
	public Set<ProductVO> getProductBypno(String pno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getProductByCategory(String pTno) {
		List<ProductVO> pdList = new ArrayList<ProductVO>();
		ProductVO pd = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_CATEGORY);
			pstmt.setString(1, pTno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pd = new ProductVO();
				pd.setpno(rs.getString("PNO"));
				pd.setpname(rs.getString("PNAME"));
				pd.setpP(rs.getInt("PP"));
				pd.setpPic(rs.getBytes("PPIC"));
				pd.setpDes(rs.getString("PDES"));
				pd.setpDoffer(rs.getInt("PDOFFER"));
				pd.setINVStatus(rs.getInt("INVSTATUS"));
				pd.setpStatus(rs.getInt("PSTATUS"));
				pd.setpTno(rs.getString("PTNO"));
				pdList.add(pd);
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
		return pdList;
	}

	@Override
	public List<ProductVO> getAll(Map<String, String[]> map) {
		List<ProductVO> pdList = new ArrayList<ProductVO>();
		ProductVO pd = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String finalSQL = "select * from product"
					+ jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
					+ "order by pno";
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				
					pd = new ProductVO();
					pd.setpno(rs.getString("PNO"));
					pd.setpname(rs.getString("PNAME"));
					pd.setpP(rs.getInt("PP"));
					pd.setpPic(rs.getBytes("PPIC"));
					pd.setpDes(rs.getString("PDES"));
					pd.setpDoffer(rs.getInt("PDOFFER"));
					pd.setINVStatus(rs.getInt("INVSTATUS"));
					pd.setpStatus(rs.getInt("PSTATUS"));
					pd.setpTno(rs.getString("PTNO"));
					pdList.add(pd);
				
			}
			
			
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return pdList;
	}

	@Override
	public List<ProductVO> getAllselect(String str) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public static void main(String[] args) {
//		ProductJDBCDAO dao = new ProductJDBCDAO();
//		List<ProductVO> list = dao.getProductByStatusAndType(1,"PT001");
//		for(ProductVO pd : list) {
//			System.out.println(pd.getpno());
//			System.out.println(pd.getpname());
//			System.out.println(pd.getpP());
//			System.out.println(pd.getpPic());
//			System.out.println(pd.getpDes());
//			System.out.println(pd.getpDoffer());
//			System.out.println(pd.getINVStatus());
//			System.out.println(pd.getpStatus());
//			System.out.println(pd.getpTno());
//		}
//	}
}
