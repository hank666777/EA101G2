package com.mycoupon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.utility.tool.JDBCUtilites;



public class MyCouponJNDIDAO implements MyCouponDAO {

	private static final String INSERT_STMT = "INSERT INTO MYCOUPON VALUES"+
			"(to_char(sysdate,'yyyymmdd')||'-CS'||LPAD(to_char(MYCOUPON_SEQ.NEXTVAL),4,'0'),1,?,?)";
	private static final String UPDATE_STMT = "UPDATE MYCOUPON SET COUPONSTATUS=0 WHERE COUPONSNO = ?";
	private static final String GET_ALL = "SELECT * FROM MYCOUPON" ;
	private static final String GET_PIC="SELECT COUPONPIC FROM COUPON WHERE COUPONNO=?";
	private static final String GET_MY_COUPON="SELECT COUPONNO,COUNT(1)as COUNTS FROM MYCOUPON WHERE MEMNO=? AND COUPONSTATUS=1 GROUP BY COUPONNO";
	private static final String GET_MY_COUPON2="SELECT COUPONSNO,COUPONNO FROM MYCOUPON WHERE MEMNO=? AND COUPONSTATUS=1";
	@Override
	public void insert(MyCouponVO mcvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JDBCUtilites.getConnectionJNDI();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mcvo.getCouponno() );
			pstmt.setString(2, mcvo.getMemno());
			pstmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			JDBCUtilites.close(con, pstmt);
		}
	}

	@Override
	public void update(String csno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JDBCUtilites.getConnectionJNDI();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, csno);
			pstmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			JDBCUtilites.close(con, pstmt);
		}

	}

	@Override
	public List<MyCouponVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyCouponVO> list = new ArrayList<>();

		try {
			con = JDBCUtilites.getConnectionJNDI();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MyCouponVO mcvo = new MyCouponVO();
				mcvo.setCouponsno(rs.getString("couponsno"));
				mcvo.setCouponStatus(rs.getInt("couponStatus"));
				mcvo.setCouponno(rs.getString("couponno"));
				mcvo.setMemno(rs.getString("memno"));
				list.add(mcvo);
			}
				return list;
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			JDBCUtilites.close(con, pstmt, rs);
		}
				return null;
	}

	@Override
	public byte[] getOnePic(String couponno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtilites.getConnectionJNDI();
			pstmt = con.prepareStatement(GET_PIC);
			pstmt.setString(1, couponno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			byte[] bytes = rs.getBytes("COUPONPIC");
			return bytes;
			}			
		} 
		catch (Exception e) {
			e.printStackTrace();
	    } 
		finally {
			JDBCUtilites.close(con, pstmt, rs);
	    }
		
				return null;
	}

	@Override
	public List<MyCouponVO> getMyCoupon(String memno) {	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyCouponVO> list = new ArrayList<>();

		try {
			con = JDBCUtilites.getConnectionJNDI();
			pstmt = con.prepareStatement(GET_MY_COUPON);
			pstmt.setString(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MyCouponVO mcvo = new MyCouponVO();
				mcvo.setCouponno(rs.getString("couponno"));
				mcvo.setCount(rs.getString("counts"));
				list.add(mcvo);
			}
				return list;
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			JDBCUtilites.close(con, pstmt, rs);
		}
				return null;
	}

	@Override
	public List<MyCouponVO> getMyCoupon2(String memno)  {	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyCouponVO> list = new ArrayList<>();

		try {
			con = JDBCUtilites.getConnectionJNDI();
			pstmt = con.prepareStatement(GET_MY_COUPON2);
			pstmt.setString(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MyCouponVO mcvo = new MyCouponVO();
				mcvo.setCouponno(rs.getString("couponno"));
				mcvo.setCouponsno(rs.getString("couponsno"));
				list.add(mcvo);
			}
				return list;
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			JDBCUtilites.close(con, pstmt, rs);
		}
				return null;
	}
	

}
