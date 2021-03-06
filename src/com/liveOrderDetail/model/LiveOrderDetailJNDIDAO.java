package com.liveOrderDetail.model;

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

public class LiveOrderDetailJNDIDAO implements LiveOrderDetail_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static final String INSERT_STMT = "INSERT INTO LIVEORDERDETAIL (LIVEORDERNO, PNO , PP,LIVEORDERQTY)"
			+ " VALUES (?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE LIVEORDERDETAIL SET  PNO = ?, PP = ?, "
			+ "LIVEORDERQTY = ? WHERE LIVEORDERNO = ?";
	private static final String DELETE_STMT = "DELETE FROM LIVEORDERDETAIL WHERE LIVEORDERNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM LIVEORDERDETAIL WHERE LIVEORDERNO = ?";
	public static final String GET_ALL = "SELECT * FROM LIVEORDERDETAIL ORDER BY LIVEORDERNO DESC";
	private static final String GET_Product_Bypno_STMT = "SELECT PNAME, PP,PPIC,PDES,PDOFFER,INVSTATUS,pStatus,PTNO FROM PRODUCT where pno = ? order by pno";
	@Override
	public void add(LiveOrderDetailVO liveOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,liveOrderDetailVO.getLiveOrderno());
			pstmt.setString(2,liveOrderDetailVO.getPno());
			pstmt.setInt(3,liveOrderDetailVO.getPp());
			pstmt.setInt(4,liveOrderDetailVO.getLiveOrderQty());
			
			pstmt.executeUpdate();
					
		}catch (SQLException e) {
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
	public void update(LiveOrderDetailVO liveOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1,liveOrderDetailVO.getPno());
			pstmt.setInt(2,liveOrderDetailVO.getPp());
			pstmt.setInt(3,liveOrderDetailVO.getLiveOrderQty());
			pstmt.setString(4,liveOrderDetailVO.getLiveOrderno());
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
	public void delete(String liveOrderno) {
		Connection con = null;
		PreparedStatement pstmt = null;
try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1,liveOrderno);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
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
	public LiveOrderDetailVO findByPK(String liveOrderno) {
		
		LiveOrderDetailVO loddVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, liveOrderno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				loddVO = new LiveOrderDetailVO();
				loddVO.setLiveOrderno(rs.getString("LIVEORDERNO"));
				loddVO.setPno(rs.getString("PNO"));
				loddVO.setPp(rs.getInt("PP"));
				loddVO.setLiveOrderQty(rs.getInt("LIVEORDERQTY"));
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
		return loddVO;
	}

	@Override
	public List<LiveOrderDetailVO> getAll() {
		
		List<LiveOrderDetailVO> loddList = new ArrayList<LiveOrderDetailVO>();
		LiveOrderDetailVO loddVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				loddVO = new LiveOrderDetailVO();
				loddVO.setLiveOrderno(rs.getString("LIVEORDERNO"));
				loddVO.setPno(rs.getString("PNO"));
				loddVO.setPp(rs.getInt("PP"));
				loddVO.setLiveOrderQty(rs.getInt("LIVEORDERQTY"));
				loddList.add(loddVO);
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
		return loddList;
	}

	@Override
	public void insert2(LiveOrderDetailVO liveOrderDetailVO, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,liveOrderDetailVO.getLiveOrderno());
			pstmt.setString(2,liveOrderDetailVO.getPno());
			pstmt.setInt(3,liveOrderDetailVO.getPp());
			pstmt.setInt(4,liveOrderDetailVO.getLiveOrderQty());
			
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
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
}
