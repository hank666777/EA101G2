package com.liveOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liveOrderDetail.model.LiveOrderDetailJDBCDAO;
import com.liveOrderDetail.model.LiveOrderDetailVO;




public class LiveOrderJDBCDAO implements LiveOrder_interface {

	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // Oracle�X�ʮM��
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER = "EA101_G2";
	public static final String PASSWORD = "123456";

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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, liveOrderVO.getEmpno());
			pstmt.setString(2, liveOrderVO.getTableno());
			pstmt.setTimestamp(3, liveOrderVO.getLiveOrderTime());
			pstmt.setDouble(4, liveOrderVO.getLiveOrderTotal());
			pstmt.setInt(5, liveOrderVO.getLiveOrderPayment());
			pstmt.setInt(6, liveOrderVO.getLiveOrderStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors	
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, liveOrderno);

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
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
	public LiveOrderVO findByPK(String liveOrderno) {
		
		LiveOrderVO lod = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, liveOrderno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lod = new LiveOrderVO();
				lod.setLiveOrderno(rs.getString("LIVEORDERNO"));
				lod.setEmpno(rs.getString("EMPNO"));
				lod.setTableno(rs.getString("TABLENO"));
				lod.setLiveOrderTime(rs.getTimestamp("LIVEORDERTIME"));
				lod.setLiveOrderTotal(rs.getDouble("LIVEORDERTOTAL"));
				lod.setLiveOrderPayment(rs.getInt("LIVEORDERPAYMENT"));
				lod.setLiveOrderStatus(rs.getInt("LIVEORDERSTATUS"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors	
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
		return lod;
	}

	@Override
	public List<LiveOrderVO> getAll() {
		List<LiveOrderVO> lodList = new ArrayList<>();
		LiveOrderVO lod = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lod = new LiveOrderVO();
				lod.setLiveOrderno(rs.getString("LIVEORDERNO"));
				lod.setEmpno(rs.getString("EMPNO"));
				lod.setTableno(rs.getString("TABLENO"));
				lod.setLiveOrderTime(rs.getTimestamp("LIVEORDERTIME"));
				lod.setLiveOrderTotal(rs.getDouble("LIVEORDERTOTAL"));
				lod.setLiveOrderPayment(rs.getInt("LIVEORDERPAYMENT"));
				lod.setLiveOrderStatus(rs.getInt("LIVEORDERSTATUS"));
				lodList.add(lod);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors	
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
		return lodList;
	}

	@Override
	public LiveOrderVO insertWithLiveOrderDetail(LiveOrderVO liveOrderVO, List<LiveOrderDetailVO> list) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
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
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	
	public static void main(String[] args) {
		
		Timestamp d = new Timestamp(System.currentTimeMillis());	
		LiveOrderJDBCDAO dao = new LiveOrderJDBCDAO();
		
		LiveOrderVO loVO = new LiveOrderVO();
		loVO.setEmpno("E0000004");
		loVO.setTableno("T0001");
		loVO.setLiveOrderTime(d);
		loVO.setLiveOrderTotal(1600.0);
		loVO.setLiveOrderPayment(1);
		loVO.setLiveOrderStatus(1);
		
		List<LiveOrderDetailVO> testList = new ArrayList<LiveOrderDetailVO>();// 準備置入訂單明細
		LiveOrderDetailVO lodVO = new LiveOrderDetailVO(); //訂單1
		lodVO.setLiveOrderno(loVO.getLiveOrderno());
		lodVO.setPno("P0003");
		lodVO.setPp(1200);
		lodVO.setLiveOrderQty(8);
		
		LiveOrderDetailVO lodVO2 = new LiveOrderDetailVO();//訂單2

		lodVO2.setPno("P0002");
		lodVO2.setPp(800);
		lodVO2.setLiveOrderQty(12);
		
		testList.add(lodVO);
		testList.add(lodVO2);
		
		dao.insertWithLiveOrderDetail(loVO , testList);
		
		
		// 查詢訂單
				List<LiveOrderVO> list = dao.getAll();
				for (LiveOrderVO aDept : list) {
					System.out.print(aDept.getLiveOrderno() + ",");
					System.out.print(aDept.getEmpno() + ",");
					System.out.print(aDept.getTableno() + ",");
					System.out.print(aDept.getLiveOrderTime() + ",");
					System.out.print(aDept.getLiveOrderTotal() + ",");
					System.out.print(aDept.getLiveOrderPayment() + ",");
					System.out.print(aDept.getLiveOrderStatus() + ",");
					System.out.println();
				}
	}

	@Override
	public List<LiveOrderVO> getAll(Map<String, String[]> map) {
		
		
		
		
		
		
		return null;
	}
	
	
	

}