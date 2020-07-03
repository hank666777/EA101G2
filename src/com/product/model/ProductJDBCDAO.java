package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductJDBCDAO implements Product_interface{
	
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // Oracle�X�ʮM��
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER = "EA101_G2";
	public static final String PASSWORD = "123456";
	
	public static final String INSERT_STMT = "INSERT INTO PRODUCT (PNO, PNAME, PP,PPIC,PDES,PDOFFER,"
			+ "INVSTATUS,PSTATUS,PTNO) VALUES ('P'||LPAD(TO_CHAR(PT_SEQ.NEXTVAL),9,'0'),?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE PRODUST SET  PNAME = ?, PP = ?, PPIC=?,PDES=?,"
			+ "PDOFFER = ?, INVSTATUS = ?, PSTATUS = ?,PTNO = ? WHERE PNO = ?";
	private static final String DELETE_STMT = "DELETE FROM PRODUCT WHERE PNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM PRODUCT WHERE PNO = ?";
	public static final String GET_ALL = "SELECT * FROM PRODUCT ORDER BY PNO DESC";
	private static final String GET_ALL_STATUS = "SELECT * FROM PRODUCT where pStatus=?";
	
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
//	public static void main(String[] args) {
//		ProductJDBCDAO pdDAO = new ProductJDBCDAO();
//		List<ProductVO> pd = pdDAO.getProductByStatus(1);
//		for(ProductVO product : pd) {
//			System.out.println(product.getpno());
//			System.out.println(product.getpname());
//			System.out.println(product.getpP());
//			System.out.println(product.getpDes());
//			System.out.println(product.getpDoffer());
//			System.out.println(product.getINVStatus());
//			System.out.println(product.getpTno());
//		}
//	}

}
