package com.ono.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.onodetail.model.ONODetailJDBCDAO;
import com.onodetail.model.ONODetailVO;

public class ONODAO implements ONODAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
private static final String INSERT_STMT = "INSERT INTO ONO (onono, memno, couponSno, onoTime, onoTotal, onoStatus, onoPay)  VALUES (TO_CHAR(sysdate, 'yyyymmdd')||'-O'||LPAD(TO_CHAR(Ono_SEQ.NEXTVAL),5,'0'),?,?,?,?,?,?) ";
	
	private static final String GET_ALL_STMT = "SELECT onono, memno, couponSno, onoTime, onoTotal, onoStatus, onoPay   FROM ONO ORDER BY onono";
	private static final String GET_ONE_STMT = "SELECT onono, memno, couponSno, onoTime, onoTotal, onoStatus, onoPay FROM ONO WHERE onono =? ";
	private static final String GET_ONODetailByOnono_STMT = "SELECT onono, pno, onoQty, onoPrice FROM ONODetail WHERE onono =? order by onono";
	
	private static final String DELETE_ONODetail = "DELETE FROM ONODetail WHERE onono = ?";
	private static final String DELETE_ONO = "DELETE FROM ONO WHERE onono = ?";
	
	private static final String UPDATE = "UPDATE ONO SET  memno=?, couponSno=?, onoTime=?, onoTotal=?, onoStatus=?, onoPay=? WHERE onono =?";
	@Override
	public void insert(ONOVO ONOVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, ONOVO.getmemno());
			pstmt.setString(2, ONOVO.getcouponSno());
			pstmt.setTimestamp(3, ONOVO.getonoTime());
			pstmt.setInt(4, ONOVO.getonoTotal());
			pstmt.setInt(5, ONOVO.getonoStatus());
			pstmt.setInt(6, ONOVO.getonoPay());
		
			
			
			pstmt.executeUpdate();
		
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
			if(pstmt!= null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(ONOVO ONOVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

try {
			
	con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, ONOVO.getmemno());
			pstmt.setString(2, ONOVO.getcouponSno());
			pstmt.setTimestamp(3, ONOVO.getonoTime());
			pstmt.setInt(4, ONOVO.getonoTotal());
			pstmt.setInt(5, ONOVO.getonoStatus());
			pstmt.setInt(6, ONOVO.getonoPay());
			pstmt.setString(7, ONOVO.getonono());
			
			pstmt.executeUpdate();
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
			if(pstmt!= null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String onono) {
int updateCount_ONODetail = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			//先刪除明細
			pstmt = con.prepareStatement(DELETE_ONODetail);
			pstmt.setString(1, onono);
			pstmt.executeUpdate();
			updateCount_ONODetail = pstmt.executeUpdate();
			
			//再刪除訂單
			pstmt = con.prepareStatement(DELETE_ONO);
			pstmt.setString(1, onono);
			pstmt.executeUpdate();
			
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除訂單編號" + onono + "時，共有明細" + updateCount_ONODetail + "筆，被刪除");
			
	} catch (SQLException se) {
			if(con != null) {
				try {
					con.rollback();
				} catch(SQLException excep) {
					throw new RuntimeException("rollback error occured." + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
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
	public ONOVO findByPrimaryKey(String onono) {
		ONOVO ONOVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, onono);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				ONOVO = new ONOVO();
				
				ONOVO.setonono(rs.getString("onono"));
				ONOVO.setmemno(rs.getString("memno"));
				ONOVO.setcouponSno(rs.getString("couponSno"));
				ONOVO.setonoTime(rs.getTimestamp("onoTime"));
				ONOVO.setonoTotal(rs.getInt("onoTotal"));
				ONOVO.setonoStatus(rs.getInt("onoStatus"));
				ONOVO.setonoPay(rs.getInt("onoPay"));
			
			
			}
			
	} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
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
		return ONOVO;
	}

	@Override
	public List<ONOVO> getAll() {
		List<ONOVO>list = new ArrayList<ONOVO>();
		ONOVO ONOVO = null;
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ONOVO = new ONOVO();
				
				ONOVO.setonono(rs.getString("onono"));
				ONOVO.setmemno(rs.getString("memno"));
				ONOVO.setcouponSno(rs.getString("couponSno"));
				ONOVO.setonoTime(rs.getTimestamp("onoTime"));
				ONOVO.setonoTotal(rs.getInt("onoTotal"));
				ONOVO.setonoStatus(rs.getInt("onoStatus"));
				ONOVO.setonoPay(rs.getInt("onoPay"));

				list.add(ONOVO);
			}
	
		} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	public Set<ONODetailVO> getONODetailByOnono(String onono) {
		Set<ONODetailVO> set = new HashSet<ONODetailVO>();
		ONODetailVO onodetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONODetailByOnono_STMT);
			pstmt.setString(1, onono);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				onodetailVO = new ONODetailVO();
				onodetailVO.setonono(rs.getString("onono"));
				onodetailVO.setpno(rs.getString("pno"));
				onodetailVO.setonoQty(rs.getInt("onoQty"));
				onodetailVO.setonoPrice(rs.getInt("onoPrice"));
				
				set.add(onodetailVO);
			}
			
	} catch (SQLException se) {
			throw new RuntimeException ("A database error occured." + se.getMessage());
		} finally {
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
	public ONOVO insertWithONODetail(ONOVO onoVO, List<ONODetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
    		
    		// 先新增線上訂單	
    		String cols[] = {"ONONO"};
    		pstmt = con.prepareStatement(INSERT_STMT , cols);
    		pstmt.setString(1, onoVO.getmemno());
    		pstmt.setString(2,onoVO.getcouponSno());
    		pstmt.setTimestamp(3,onoVO.getonoTime());
    		pstmt.setInt(4,onoVO.getonoTotal());
    		pstmt.setInt(5,onoVO.getonoStatus());
    		pstmt.setInt(6,onoVO.getonoPay());
    		pstmt.executeUpdate();
    	
    		//掘取對應的自增主鍵值
    		String next_onono = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_onono = rs.getString(1);
    			System.out.println("自增主鍵值= " + next_onono +"(剛新增成功的訂單編號)");
    		}else {
				System.out.println("未取得自增主鍵值");
			}
    		rs.close();
    		// 再同時新增訂單明細
    		ONODetailJDBCDAO dao = new ONODetailJDBCDAO();
    		System.out.println("list.size()-A="+list.size());
    		for(ONODetailVO ondVO : list) {
    			System.out.println(ondVO);
    			ondVO.setonono(next_onono);
    			dao.insert2(ondVO,con);
    		}
    		
    		// 2●設定於 pstm.executeUpdate()之後
    		con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_onono + "時,共有訂單" + list.size()
					+ "張同時被新增");
    		
	} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-ONO");
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
		return onoVO;
	}

}
