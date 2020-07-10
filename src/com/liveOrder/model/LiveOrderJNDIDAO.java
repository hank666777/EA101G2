package com.liveOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.liveOrder.controller.jdbcUtil_CompositeQuery_Emp2;
import com.liveOrderDetail.model.LiveOrderDetailJDBCDAO;
import com.liveOrderDetail.model.LiveOrderDetailVO;

public class LiveOrderJNDIDAO implements LiveOrder_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static final String INSERT_STMT = "INSERT INTO LIVEORDER (LIVEORDERNO, EMPNO , TABLENO,LIVEORDERTIME,LIVEORDERTOTAL,LIVEORDERPAYMENT,"
			+ "LIVEORDERSTATUS) VALUES ('20200401-L'||LPAD(to_char(LiveOrder_seq.NEXTVAL), 5, '0'),?,?,?,?,?,?)";
	public static final String UPDATE_STMT = "UPDATE LIVEORDER SET  EMPNO = ?, TABLENO = ?, "
			+ "LIVEORDERTIME = ?, LIVEORDERTOTAL = ?, LIVEORDERPAYMENT = ? ,LIVEORDERSTATUS=? WHERE LIVEORDERNO = ?";
	public static final String DELETE_STMT = "DELETE FROM LIVEORDER WHERE LIVEORDERNO = ?";
	public static final String FIND_BY_PK = "SELECT * FROM LIVEORDER WHERE LIVEORDERNO = ?";
	public static final String GET_ALL = "SELECT * FROM LIVEORDER ORDER BY LIVEORDERNO DESC";
	public static final String GET_LIVEORDERDETAIL_BYLIVEORDERNO_STMT = "SELECT LIVEORDERNO,PNO,PP,LIVEORDERQTY FROM LIVEORDERDETAIL WHERE LIVEORDERNO = ?";
	@Override
	public void add(LiveOrderVO liveOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, liveOrderVO.getEmpno());
			pstmt.setString(2, liveOrderVO.getTableno());
			pstmt.setTimestamp(3, liveOrderVO.getLiveOrderTime());
			pstmt.setDouble(4, liveOrderVO.getLiveOrderTotal());
			pstmt.setInt(5, liveOrderVO.getLiveOrderPayment());
			pstmt.setInt(6, liveOrderVO.getLiveOrderStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
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
	public void update(LiveOrderVO liveOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, liveOrderVO.getEmpno());
			pstmt.setString(2, liveOrderVO.getTableno());
			pstmt.setTimestamp(3, liveOrderVO.getLiveOrderTime());
			pstmt.setDouble(4, liveOrderVO.getLiveOrderTotal());
			pstmt.setInt(5, liveOrderVO.getLiveOrderPayment());
			pstmt.setInt(6, liveOrderVO.getLiveOrderStatus());
			pstmt.setString(7, liveOrderVO.getLiveOrderno());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
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
	public void delete(String liveOrderno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1,liveOrderno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
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
	public LiveOrderVO findByPK(String liveOrderno) {
		
		LiveOrderVO lodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setString(1,liveOrderno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lodVO = new LiveOrderVO();
				lodVO.setLiveOrderno(rs.getString("liveOrderno"));
				lodVO.setEmpno(rs.getString("empno"));
				lodVO.setTableno(rs.getString("tableno"));
				lodVO.setLiveOrderTime(rs.getTimestamp("LIVEORDERTIME"));
				lodVO.setLiveOrderTotal(rs.getDouble("liveOrderTotal"));
				lodVO.setLiveOrderPayment(rs.getInt("liveOrderPayment"));
				lodVO.setLiveOrderStatus(rs.getInt("liveOrderStatus"));
			}
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
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
		
		return lodVO;
	}

	@Override
	public List<LiveOrderVO> getAll() {
		
		List<LiveOrderVO> list = new ArrayList<LiveOrderVO>();
		LiveOrderVO lodVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lodVO = new LiveOrderVO();
				lodVO.setLiveOrderno(rs.getString("liveOrderno"));
				lodVO.setEmpno(rs.getString("empno"));
				lodVO.setTableno(rs.getString("tableno"));
				lodVO.setLiveOrderTime(rs.getTimestamp("LIVEORDERTIME"));
				lodVO.setLiveOrderTotal(rs.getDouble("liveOrderTotal"));
				lodVO.setLiveOrderPayment(rs.getInt("liveOrderPayment"));
				lodVO.setLiveOrderStatus(rs.getInt("liveOrderStatus"));
				list.add(lodVO);
			}
			

			// Handle any driver errors
		} catch (SQLException e) {
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
		return list;
	}

	@Override
	public LiveOrderVO insertWithLiveOrderDetail(LiveOrderVO liveOrderVO, List<LiveOrderDetailVO> list) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增現場訂單	
    		String cols[] = {"LIVEORDERNO"};
    		pstmt = con.prepareStatement(INSERT_STMT , cols);
    		pstmt.setString(1, liveOrderVO.getEmpno());
			pstmt.setString(2, liveOrderVO.getTableno());
			pstmt.setTimestamp(3, liveOrderVO.getLiveOrderTime());
			pstmt.setDouble(4, liveOrderVO.getLiveOrderTotal());
			pstmt.setInt(5, liveOrderVO.getLiveOrderPayment());
			pstmt.setInt(6, liveOrderVO.getLiveOrderStatus());
			pstmt.executeUpdate();
    		
			//掘取對應的自增主鍵值
			String next_liveOrderno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
    		if(rs.next()) {
    			next_liveOrderno = rs.getString(1);
    			System.out.println("自增主鍵值= " + next_liveOrderno +"(剛新增成功的訂單編號)");
    		}else {
				System.out.println("未取得自增主鍵值");
			}
    		rs.close();
    		
    		// 再同時新增訂單明細
    		LiveOrderDetailJDBCDAO dao = new LiveOrderDetailJDBCDAO();
    		System.out.println("list.size()-A="+list.size());
    		for(LiveOrderDetailVO lodVO : list) {
    			lodVO.setLiveOrderno(next_liveOrderno);
    			dao.insert2(lodVO,con);
    		}
    		
    		// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_liveOrderno + "時,共有訂單" + list.size()
					+ "張同時被新增");
    		
		}catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return liveOrderVO;
		
	}

	@Override
	public List<LiveOrderVO> getAll(Map<String, String[]> map) {
		
		List<LiveOrderVO> list = new ArrayList<LiveOrderVO>();
		LiveOrderVO loVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from liveorder "
		          + jdbcUtil_CompositeQuery_Emp2.get_WhereCondition(map)
		          + "order by empno";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				loVO = new LiveOrderVO();
				loVO.setLiveOrderno(rs.getString("liveOrderno"));
				loVO.setEmpno(rs.getString("empno"));
				loVO.setTableno(rs.getString("tableno"));
				loVO.setLiveOrderTime(rs.getTimestamp("liveOrderTime"));
				loVO.setLiveOrderTotal(rs.getDouble("liveOrderTotal"));
				loVO.setLiveOrderPayment(rs.getInt("liveOrderPayment"));
				loVO.setLiveOrderStatus(rs.getInt("liveOrderStatus"));
				list.add(loVO);    // Store the row in the List
			}
			
		}catch (SQLException se) {
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
		return list;
	}
}
