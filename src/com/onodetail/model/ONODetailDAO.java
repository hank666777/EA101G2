package com.onodetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class ONODetailDAO implements ONODetailDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ONODetail (onono, pno, onoQty, onoPrice)  VALUES (?, ?, ?, ?) ";
	private static final String GET_ALL_STMT = "SELECT onono, pno, onoQty, onoPrice FROM ONODetail ORDER BY onono";
	private static final String GET_ONE_STMT = "SELECT onono, pno, onoQty, onoPrice FROM ONODetail WHERE onono =? ";
	private static final String DELETE = "DELETE FROM ONODetail WHERE (onono=? and pno=?)";
	private static final String UPDATE = "UPDATE ONODetail SET onoQty= ?, onoPrice= ? WHERE (onono = ? and  pno = ?)";
	private static final String GET_Product_Bypno_STMT = "SELECT PNAME, PP,PPIC,PDES,PDOFFER,INVSTATUS,pStatus,PTNO FROM PRODUCT where pno = ? order by pno";
	private static final String	GET_ONODETAILS_BY_ONONO =
			"SELECT * FROM ONODETAIL WHERE = ? ORDER BY PNO";
	
	@Override
	public void insert(ONODetailVO ondVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ondVO.getonono());
			pstmt.setString(2, ondVO.getpno());
			pstmt.setInt(3, ondVO.getonoQty());
			pstmt.setInt(4, ondVO.getonoPrice());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
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
	public void update(ONODetailVO ondVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ondVO.getonoQty());
			pstmt.setInt(2, ondVO.getonoPrice());
			pstmt.setString(3, ondVO.getonono());
			pstmt.setString(4, ondVO.getpno());

			pstmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A database error occured." + se.getMessage());

		} finally {
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
	public void delete(String onono, String pno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, onono);
			pstmt.setString(2, pno);

			pstmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
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
	public ONODetailVO findByPrimaryKey(String onono, String pno) {

		ONODetailVO ONODetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, onono);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ONODetailVO = new ONODetailVO();
				ONODetailVO.setonono(rs.getString("onono"));
				ONODetailVO.setpno(rs.getString("pno"));
				ONODetailVO.setonoQty(rs.getInt("onoQty"));
				ONODetailVO.setonoPrice(rs.getInt("onoPrice"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return ONODetailVO;
	}

	@Override
	public List<ONODetailVO> getAll() {
		List<ONODetailVO> list = new ArrayList<ONODetailVO>();
		ONODetailVO ONODetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ONODetailVO = new ONODetailVO();
				ONODetailVO.setonono(rs.getString("onono"));
				ONODetailVO.setpno(rs.getString("pno"));
				ONODetailVO.setonoQty(rs.getInt("onoQty"));
				ONODetailVO.setonoPrice(rs.getInt("onoPrice"));

				list.add(ONODetailVO);
			}

		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
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

	@Override
	public void insert2(ONODetailVO ondVO, Connection con) {

		PreparedStatement pstmt = null;
		try {
			System.out.println(ondVO);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ondVO.getonono());
			pstmt.setString(2, ondVO.getpno());
			pstmt.setInt(3, ondVO.getonoQty());
			pstmt.setInt(4, ondVO.getonoPrice());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-qty");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Set<ProductVO> getProductBypno(String pno) {
		Set<ProductVO> set = new LinkedHashSet<ProductVO>();
		ProductVO pdVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Product_Bypno_STMT);
			pstmt.setString(1, pno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pdVO = new ProductVO();
				pdVO.setpname(rs.getString("pname"));
				pdVO.setpP(rs.getInt("pP"));
				pdVO.setpPic(rs.getBytes("pPic"));
				pdVO.setpDes(rs.getString("pDes"));
				pdVO.setpDoffer(rs.getInt("pDoffer"));
				pdVO.setINVStatus(rs.getInt("INVStatus"));
				pdVO.setpStatus(rs.getInt("pStatus"));
				pdVO.setpTno(rs.getString("pTno"));
				set.add(pdVO);// Store the row in the vector
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}

	@Override
	public Set<ONODetailVO> getONODetails(String onono) {
		Set<ONODetailVO> set = new LinkedHashSet<>();
		ONODetailVO onlineOrderDetail = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONODETAILS_BY_ONONO);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				onlineOrderDetail = new ONODetailVO();
				onlineOrderDetail.setonono(rs.getString(1));
				onlineOrderDetail.setpno(rs.getString(2));
				onlineOrderDetail.setonoQty(rs.getInt(3));
				onlineOrderDetail.setonoPrice(rs.getInt(4));
				set.add(onlineOrderDetail);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}
	
	
}
