package com.coupon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.utility.tool.JDBCUtilites;



public class CouponDAOIpml implements CouponDAO {

	private static final String INSERT_STMT = "INSERT INTO COUPON VALUES"+
			"('C'||LPAD(to_char(COUPON_SEQ.NEXTVAL),4,'0'),?,?,?)";
	private static final String UPDATE_STMT = "UPDATE COUPON SET COUPONNAME = ?, COUPONDISCOUNT = ?, COUPONPIC = ? WHERE COUPONNO = ?";
	private static final String UPDATE_STMT2 = "UPDATE COUPON SET COUPONNAME = ?, COUPONDISCOUNT = ?WHERE COUPONNO = ?";
	private static final String DELETE_STMT = "DELETE FROM COUPON WHERE COUPONNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM COUPON WHERE COUPONNO = ?";
	private static final String FIND_BY_PK2 = "SELECT COUPONDISCOUNT FROM COUPON WHERE COUPONNAME = ?";
	private static final String FIND_BY_PK3 = "SELECT coupondiscount from coupon where couponno=(select couponno from mycoupon where couponsno=?)";
	private static final String GET_ALL = "SELECT * FROM COUPON";

	@Override
	public void insert(CouponVO cvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JDBCUtilites.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cvo.getCouponName());
			pstmt.setInt(2, cvo.getCouponDiscount());
			pstmt.setBytes(3, cvo.getCouponPic());	
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtilites.close(con, pstmt);
		}
	}

	@Override
	public void update(CouponVO cvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JDBCUtilites.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, cvo.getCouponName());
			pstmt.setDouble(2, cvo.getCouponDiscount());
			pstmt.setBytes(3, cvo.getCouponPic());
			pstmt.setString(4, cvo.getCouponno());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtilites.close(con, pstmt);
		}

	}
	@Override
	public void update2(CouponVO cvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JDBCUtilites.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT2);

			pstmt.setString(1, cvo.getCouponName());
			pstmt.setDouble(2, cvo.getCouponDiscount());
			pstmt.setString(3, cvo.getCouponno());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtilites.close(con, pstmt);
		}

	}
	@Override
	public void delete(String cno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JDBCUtilites.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, cno);
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtilites.close(con, pstmt);
		}

	}

	@Override
	public CouponVO findByPrimaryKey(String cno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtilites.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, cno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				CouponVO cvo = new CouponVO();
				cvo.setCouponno(rs.getString("couponno"));
				cvo.setCouponName(rs.getString("couponName"));
				cvo.setCouponDiscount(rs.getInt("couponDiscount"));
				cvo.setCouponPicture(rs.getBytes("couponPic"));
				return cvo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtilites.close(con, pstmt, rs);
		}
				return null;
	}

	@Override
	public List<CouponVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CouponVO> list = new ArrayList<>();

		try {
			con = JDBCUtilites.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CouponVO cvo = new CouponVO();
				cvo.setCouponno(rs.getString("couponno"));
				cvo.setCouponName(rs.getString("couponName"));
				cvo.setCouponDiscount(rs.getInt("couponDiscount"));
				cvo.setCouponPicture(rs.getBytes("couponPic"));
				list.add(cvo);
			}
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtilites.close(con, pstmt, rs);
		}
				return null;
	}

	@Override
	public Integer findByPrimaryKey2(String cpname) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtilites.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK2);
			pstmt.setString(1, cpname);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				return rs.getInt("couponDiscount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtilites.close(con, pstmt, rs);
		}
				return null;
	}

	@Override
	public Integer findByPrimaryKey3(String cpsno) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtilites.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK3);
			pstmt.setString(1, cpsno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				return rs.getInt("couponDiscount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtilites.close(con, pstmt, rs);
		}
				return null;
	}

}

