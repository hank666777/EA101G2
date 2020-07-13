package com.activity.model;

import java.sql.Timestamp;
import java.util.List;

public class ActivityTest {

	public static void main(String[] args) {
		
		ActivityDAO AVO = new ActivityDAO();
		
		//新增
//		activityVO av = new activityVO();
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		av.setActTyno("AT005");
//		av.setActName("親子野餐");
//		av.setActDes("與孩子參與非常有所感觸BABABABABABABABABABA");
//		av.setActPic(null);
//		av.setActTalPeo(46);
//		av.setActHoDate(t);
//		av.setActStDate(t);
//		av.setActEdDate(t);
//		av.setActFee(400);
//		av.setActMode(1);
//		av.setActUpper(60);
//		av.setActLower(20);
//		AVO.add(av);
		//修改
//		activityVO av = new activityVO();
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		av.setActTyno("AT005");
//		av.setActName("親子遊湖畔");
//		av.setActDes("這活動是給予親子一個共同野餐的機會");
//		av.setActPic(null);
//		av.setActTalPeo(50);
//		av.setActHoDate(t);
//		av.setActStDate(t);
//		av.setActEdDate(t);
//		av.setActFee(200);
//		av.setActMode(1);
//		av.setActUpper(60);
//		av.setActLower(20);
//		av.setActno("20200610-A00005");
//		AVO.update(av);
		//刪除
//		AVO.delete("20200610-A00005");
		//查詢PK
//		activityVO av = AVO.findByPK("20200610-A00004");
//		System.out.println(av.getActno()+",");
//		System.out.println(av.getActTyno()+",");
//		System.out.println(av.getActName()+",");
//		System.out.println(av.getActDes()+",");
//		System.out.println(av.getActPic()+",");
//		System.out.println(av.getActTalPeo()+",");
//		System.out.println(av.getActHoDate()+",");
//		System.out.println(av.getActStDate()+",");
//		System.out.println(av.getActEdDate()+",");
//		System.out.println(av.getActFee()+",");
//		System.out.println(av.getActMode()+",");
//		System.out.println(av.getActUpper()+",");
//		System.out.println(av.getActLower());
//		System.out.println("----------------------");
		//查詢全部
//		List<activityVO> list = AVO.getAll();
//		for(activityVO av : list) {
//			System.out.println(av.getActno()+",");
//			System.out.println(av.getActTyno()+",");
//			System.out.println(av.getActName()+",");
//			System.out.println(av.getActDes()+",");
//			System.out.println(av.getActPic()+",");
//			System.out.println(av.getActTalPeo()+",");
//			System.out.println(av.getActHoDate()+",");
//			System.out.println(av.getActStDate()+",");
//			System.out.println(av.getActEdDate()+",");
//			System.out.println(av.getActFee()+",");
//			System.out.println(av.getActMode()+",");
//			System.out.println(av.getActUpper()+",");
//			System.out.println(av.getActLower());
//			System.out.println("----------------------");
//		}
	}

}
