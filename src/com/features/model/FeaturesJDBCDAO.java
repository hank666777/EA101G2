package com.features.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeaturesJDBCDAO implements FeaturesDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G2";
	String password = "20200723";
	
	private static final String INSERT_STMT = 
			"INSERT INTO FEATURES (FEANO, FEANAME) VALUES ('F'||LPAD(to_char(features_seq.NEXTVAL), 4,'0'),?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM FEATURES";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM FEATURES WHERE FEANO = ?";
	private static final String UPDATE = 
			"UPDATE FEATURES SET FEANAME = ? WHERE FEANO = ?";
	
	@Override
	public void insert(FeaturesVO featuresVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, featuresVO.getFeaName());

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
	public void update(FeaturesVO featuresVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, featuresVO.getFeaName());
			pstmt.setString(2, featuresVO.getFeano());


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
	public FeaturesVO findByPrimaryKey(String feano) {
		FeaturesVO feaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, feano);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				feaVO = new FeaturesVO();
				feaVO.setFeano(rs.getString("FEANO"));
				feaVO.setFeaName(rs.getString("FEANAME"));
			}
		}catch(ClassNotFoundException e) {
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
		
		return feaVO;
	}
	
	@Override
	public List<FeaturesVO> getAll() {
		
		List<FeaturesVO> list = new ArrayList<FeaturesVO>();
		FeaturesVO feaVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				feaVO = new FeaturesVO();
				feaVO.setFeano(rs.getString(1));
				feaVO.setFeaName(rs.getString(2));
				list.add(feaVO);
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
	
	public static void main(String[] args) {
		FeaturesJDBCDAO dao = new FeaturesJDBCDAO();
		
//		FeaturesVO feaVO1 = new FeaturesVO();
//		feaVO1.setFeaname("優惠券管理");
//		dao.insert(feaVO1);
//		System.out.println("新增成功");
//		
//		//update
//		FeaturesVO feaVO2 = new FeaturesVO();
//		feaVO2.setFeano("T0006");
//		feaVO2.setFeaname("coupon券管理");
//		System.out.println("修改成功");
//		
//		//select one
//		FeaturesVO feaVO3 = new FeaturesVO();
//		System.out.println(feaVO3.getFeano());
//		System.out.println(feaVO3.getFeaname());
//		System.out.println("查詢成功");
		
		//select all
		List<FeaturesVO> list = dao.getAll();
		System.out.println(list.get(3).getFeano());
		for(FeaturesVO fea : list) {
			System.out.println(fea.getFeano());
			System.out.println(fea.getFeaName());
		}
		System.out.println("查詢全部成功");
		
	}
}
