package com.actparticipation.model;

import java.sql.Timestamp;
import java.util.List;

public class ParticipationTest {
	
	public static void main (String[] args) {
		ParticipationDAO PD = new ParticipationDAO();
		//新增
//		ParticipationVO pv = new ParticipationVO();
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		
//		pv.setMemno("M000000001");
//		pv.setActno("20200610-A00006");
//		pv.setActPatTime(t);
//		pv.setActParEnr(2);
//		pv.setActTalFee(800);
//		
//		PD.add(pv);
		//修改
//		ParticipationVO pv = new ParticipationVO();
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		
//		pv.setMemno("M000000002");
//		pv.setActno("20200610-A00006");
//		pv.setActPatTime(t);
//		pv.setActParEnr(4);
//		pv.setActTalFee(1600);
//		pv.setAvPartno("20200611-AP0006");
//		PD.update(pv);
//		//刪除
//		PD.delete("20200611-AP0006");
//		查詢PK
//		ParticipationVO pv = PD.findByPK("20200609-AP0004");
//		System.out.println(pv.getAvPartno()+",");
//		System.out.println(pv.getMemno()+",");
//		System.out.println(pv.getActno()+",");
//		System.out.println(pv.getActPatTime()+",");
//		System.out.println(pv.getActParEnr()+",");
//		System.out.println(pv.getActTalFee());
//		System.out.println("------------------------------------");
		//查詢全部
//		List<ParticipationVO> list = PD.getAll();
//		for(ParticipationVO pv : list) {
//			System.out.println(pv.getAvPartno()+",");
//			System.out.println(pv.getMemno()+",");
//			System.out.println(pv.getActno()+",");
//			System.out.println(pv.getActPatTime()+",");
//			System.out.println(pv.getActParEnr()+",");
//			System.out.println(pv.getActTalFee());
//			System.out.println("------------------------------------");
//		}
		
		
		List<String> memnoList = PD.getAllMemno();
		System.out.println(memnoList);
		
		List<ParticipationVO> memnoByList = PD.getAllByMemno();
		System.out.println(memnoByList);
		
		
	}

}
