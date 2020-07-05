package com.employee.model;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.permission.model.PermissionJDBCDAO;
import com.permission.model.PermissionVO;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G2";
	String password = "20200723";

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (EMPNO, EACCOUNT, EPW, ENAME, EPHONE, EEMAIL, EPIC, ETITLE, ESTATUS) VALUES "
			+ "('E'||LPAD(to_char(employee_seq.NEXTVAL),7,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM EMPLOYEE";
	private static final String GET_ONE_STMT = "SELECT * FROM EMPLOYEE WHERE empno = ?";
	private static final String DELETE = "DELETE FROM EMPLOYEE WHERE EMPNO = ?";
	private static final String UPDATE = "UPDATE EMPLOYEE SET EACCOUNT = ?, EPW = ?, ENAME = ? , EPHONE = ?, EEMAIL = ?, EPIC = ?, ETITLE = ?, ESTATUS = ? WHERE EMPNO = ?";
	private static final String GET_ONE_BY_EACCOUNT_EPW = "SELECT * FROM EMPLOYEE WHERE EACCOUNT = ? AND EPW = ?";

	@Override
	public void insert(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load datavase driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empno);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	//for emplogin
	@Override
	public EmployeeVO findBy_eAccount_ePw(String eAccount, String ePw) {

		EmployeeVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			
			con.setAutoCommit(false);
			
			String[] cols = {"EMPNO"};
			
			//先新增employee
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1,employeeVO.geteAccount());
			pstmt.setString(1,employeeVO.getePw());
			pstmt.setString(2,employeeVO.geteName());
			pstmt.setString(3,employeeVO.getePhone());
			pstmt.setString(4,employeeVO.geteEmail());
			pstmt.setBytes(5,employeeVO.getePic());
			pstmt.setString(6,employeeVO.geteTitle());
			pstmt.setInt(7,employeeVO.geteStatus());
			
			pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			String next_empno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_empno = rs.getString(1);
				System.out.println("自增PK值= " + next_empno + "(剛新增成功的employee編號)");
			}else {
				System.out.println("未取得自增PK值");
			}
			
			rs.close();
			
			//同時新增權限(permission)
			PermissionJDBCDAO dao = new PermissionJDBCDAO();
			System.out.println("list.size()-A=" + list.size());
			for(PermissionVO aPer: list) {
				System.out.println("next_empno: " + next_empno);
				aPer.setEmpno(next_empno);
				System.out.println("feano: " + aPer.getFeano());
				dao.insert2(aPer,con);
			}
			
			//2.設定於pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("新增employee編號 " + next_empno 
					 + " 時，共有permission " + list.size() + " 種同時被新增");
			
			//Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
			+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {
		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();

		//新增一名員工
//		EmployeeVO emp1 = new EmployeeVO();
//		emp1.seteAccount("PeterWu");
//		emp1.setePw(UUID.randomUUID().toString().substring(0,8));
//		emp1.seteName("吳永志");
//		emp1.setePhone("0987654321");
//		emp1.seteEmail("pererwu@aaa.com");
////		emp1.setePic(null);
//		try {
//			FileInputStream fis = new FileInputStream("D:\\importImages\\peter.PNG");
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			byte[] buf = new byte[8192];
//			int len;
//			while((len = fis.read(buf)) != -1) {
//				baos.write(buf,0,len);
//			}
//			emp1.setePic(baos.toByteArray());
//			baos.close();
//			fis.close();
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		} 
//		emp1.seteTitle("店長");
//		emp1.seteStatus(1);
//		////////////////////////////
//		//放權限
//		List<PermissionVO> perList = new ArrayList<>();
//		
//		PermissionVO per1 = new PermissionVO();
//		per1.setFeano("F0010");
//		
//		PermissionVO per2 = new PermissionVO();
//		per2.setFeano("F0020");
//		
//		PermissionVO per3 = new PermissionVO();
//		per3.setFeano("F0030");
//		
//		PermissionVO per4 = new PermissionVO();
//		per4.setFeano("F0040");
//		
//		PermissionVO per5 = new PermissionVO();
//		per5.setFeano("F0050");
//		
//		PermissionVO per6 = new PermissionVO();
//		per6.setFeano("F0060");
//		
//		PermissionVO per7 = new PermissionVO();
//		per7.setFeano("F0070");
//		
//		PermissionVO per8 = new PermissionVO();
//		per8.setFeano("F0080");
//
//		perList.add(per1);
//		perList.add(per2);
//		perList.add(per3);
//		perList.add(per4);
//		perList.add(per5);
//		perList.add(per6);
//		perList.add(per7);
//		perList.add(per8);
//		
//		//此處是進入資料庫，Servlet新增步驟的2......
//		dao.insertWithPermission(emp1, perList);
		
		// insert
//		EmployeeVO empVO1 = new EmployeeVO();
//		empVO1.seteAccount("AAA");
//		empVO1.setePw("AAA");
//		empVO1.seteName("路人甲");
//		empVO1.setePhone("0987654321");
//		empVO1.seteEmail("AAA@gmail.com");

//		byte[] epic = getPicByteArray("");

//		empVO1.setePic(null);
//		empVO1.seteTitle("店長");
//		empVO1.seteStatus(1);
//		
//		dao.insert(empVO1);
//		System.out.println("新增成功");

		// update
//		EmployeeVO empVO2 = new EmployeeVO();
//		empVO2.setEmpno("E0000006");
//		empVO2.seteAccount("AAA");
//		empVO2.setePw("BBB");
//		empVO2.seteName("路人甲");
//		empVO2.setePhone("0987654321");
//		empVO2.seteEmail("AAA@gmail.com");
////		empVO2.setePic(ePic);
//		empVO2.seteTitle("店長");
//		empVO2.seteStatus(1);
//		dao.update(empVO2);
//		System.out.println("修改成功");
//
//		// delete ---沒測試
//		dao.delete(10000006);
//		System.out.println("刪除成功");
//
//		// select one
//		EmployeeVO empVO3 = dao.findByPrimaryKey("E0000006");
//		System.out.println(empVO3.getEmpno() + ",");
//		System.out.println(empVO3.geteAccount() + ",");
//		System.out.println(empVO3.getePw() + ",");
//		System.out.println(empVO3.geteName() + ",");
//		System.out.println(empVO3.getePhone() + ",");
//		System.out.println(empVO3.geteEmail() + ",");
//		System.out.println(empVO3.getePic() + ",");
//		System.out.println(empVO3.geteTitle() + ",");
//		System.out.println(empVO3.geteStatus());
//		System.out.println("SQL查詢一筆資料成功");
//		
//		//select all
		List<EmployeeVO> list = dao.getAll();
		for (EmployeeVO emp : list) {
			System.out.println(emp.getEmpno());
			System.out.println(emp.geteAccount());
			System.out.println(emp.getePw());
			System.out.println(emp.geteName());
			System.out.println(emp.getePhone());
			System.out.println(emp.geteEmail());
			System.out.println(emp.getePic());
			System.out.println(emp.geteTitle());
			System.out.println(emp.geteStatus());
			System.out.println();
			System.out.println("CLASS: "+(emp instanceof EmployeeVO));
		}
		System.out.println("查詢全部成功");
	}

//	public static byte[] getPicByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}

}
