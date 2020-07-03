package com.employee.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.permission.model.PermissionDAO;
import com.permission.model.PermissionVO;

public class EmployeeDAO implements EmployeeDAO_interface{
	
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
			"INSERT INTO EMPLOYEE (EMPNO, EACCOUNT, EPW, ENAME, EPHONE, EEMAIL, EPIC, ETITLE, ESTATUS) VALUES "
			+ "('E'||LPAD(to_char(employee_seq.NEXTVAL),7,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM EMPLOYEE";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM EMPLOYEE WHERE EMPNO = ?";
	private static final String DELETE = 
			"DELETE FROM EMPLOYEE WHERE EMPNO = ?";
	private static final String UPDATE = 
			"UPDATE EMPLOYEE SET EACCOUNT = ?, EPW = ?, ENAME = ? , EPHONE = ?, EEMAIL = ?, EPIC = ?, ETITLE = ?, ESTATUS = ? WHERE EMPNO = ?";
	private static final String GET_ONE_BY_EACCOUNT_EPW = 
			"SELECT * FROM EMPLOYEE WHERE EACCOUNT = ? AND EPW = ?";
	
	/*
	 *  sTRING XX = REQ.GETp*("XXX")
	"SELECT * FROM EMPLOYEE WHERE LIKE ENAME =XX OR LIKE EMPNO =XX"
	
	pstmt.setString(1, "%" + ENAME + "%");
	pstmt.setString(2, "%" + EMPNO + "%");
	*/
	
	@Override
	public void insert(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, employeeVO.geteAccount());
			pstmt.setString(2, employeeVO.getePw());
			pstmt.setString(3, employeeVO.geteName());
			pstmt.setString(4, employeeVO.getePhone());
			pstmt.setString(5, employeeVO.geteEmail());
			pstmt.setBytes(6, employeeVO.getePic());
			pstmt.setString(7, employeeVO.geteTitle());
			pstmt.setInt(8, employeeVO.geteStatus());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, employeeVO.geteAccount());
			pstmt.setString(2, employeeVO.getePw());
			pstmt.setString(3, employeeVO.geteName());
			pstmt.setString(4, employeeVO.getePhone());
			pstmt.setString(5, employeeVO.geteEmail());
			pstmt.setBytes(6, employeeVO.getePic());
			pstmt.setString(7, employeeVO.geteTitle());
			pstmt.setInt(8, employeeVO.geteStatus());
			pstmt.setString(9, employeeVO.getEmpno());

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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String empno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empno);
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
	public EmployeeVO findByPrimaryKey(String empno) {

		EmployeeVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmployeeVO();
				empVO.setEmpno(rs.getString("EMPNO"));
				empVO.seteAccount(rs.getString("EACCOUNT"));
				empVO.setePw(rs.getNString("EPW"));
				empVO.seteName(rs.getString("ENAME"));
				empVO.setePhone(rs.getString("EPHONE"));
				empVO.seteEmail(rs.getString("EEMAIL"));
				empVO.setePic(rs.getBytes("EPIC"));
				empVO.seteTitle(rs.getString("ETITLE"));
				empVO.seteStatus(rs.getInt("ESTATUS"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return empVO;

	}

	@Override
	public List<EmployeeVO> getAll() {

		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmployeeVO();
				empVO.setEmpno(rs.getString(1));
				empVO.seteAccount(rs.getString(2));
				empVO.setePw(rs.getString(3));
				empVO.seteName(rs.getString(4));
				empVO.setePhone(rs.getString(5));
				empVO.seteEmail(rs.getString(6));
				empVO.setePic(rs.getBytes(7));
				empVO.seteTitle(rs.getString(8));
				empVO.seteStatus(rs.getInt(9));
				list.add(empVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

	//for backlogin.jsp
	@Override
	public EmployeeVO findBy_eAccount_ePw(String eAccount, String ePw) {
		
		EmployeeVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_EACCOUNT_EPW);

			pstmt.setString(1, eAccount);
			pstmt.setString(2, ePw);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmployeeVO();
				empVO.setEmpno(rs.getString("EMPNO"));
				empVO.seteAccount(rs.getString("EACCOUNT"));
				empVO.setePw(rs.getNString("EPW"));
				empVO.seteName(rs.getString("ENAME"));
				empVO.setePhone(rs.getString("EPHONE"));
				empVO.seteEmail(rs.getString("EEMAIL"));
				empVO.setePic(rs.getBytes("EPIC"));
				empVO.seteTitle(rs.getString("ETITLE"));
				empVO.seteStatus(rs.getInt("ESTATUS"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return empVO;
	}

	
	@Override
	public void insertWithPermission(EmployeeVO employeeVO, List<PermissionVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			String[] cols = {"EMPNO"};
			
			//先新增employee
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1,employeeVO.geteAccount());
			pstmt.setString(2,employeeVO.getePw());
			pstmt.setString(3,employeeVO.geteName());
			pstmt.setString(4,employeeVO.getePhone());
			pstmt.setString(5,employeeVO.geteEmail());
			pstmt.setBytes(6,employeeVO.getePic());
			pstmt.setString(7,employeeVO.geteTitle());
			pstmt.setInt(8,employeeVO.geteStatus());
			
			pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			String next_empno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_empno = rs.getString(1);
//				System.out.println("自增PK值= " + next_empno + "(剛新增成功的employee編號)");
			}else {
//				System.out.println("未取得自增PK值");
			}
			
			rs.close();
			
			//同時新增權限(permission)
//此處改為PermissionDAO(原為PermissionJDBCDAO)
			PermissionDAO dao = new PermissionDAO();
			System.out.println("list.size()-A=" + list.size());
			for(PermissionVO aPer: list) {
//				System.out.println("next_empno: " + next_empno);
//				System.out.println("feano: " + aPer.getFeano());
				aPer.setEmpno(next_empno);
				dao.insert2(aPer,con);
			}
			
			//2.設定於pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
//			System.out.println("list.size()-B=" + list.size());
//			System.out.println("新增employee編號 " + next_empno 
//					 + " 時，共有permission " + list.size() + " 種同時被新增");
			
			//Handle any driver errors
		} catch (SQLException e) {
			if(con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-front-permission");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			//Clean up JDBC resources
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
	
}
