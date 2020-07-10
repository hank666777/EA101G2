package com.messagereport.model;

import java.sql.Timestamp;
import java.util.List;





public class MessageReportService {
	private MessageReportDAO_interface dao;//後面框架用
	
	public MessageReportService() {
		dao = new MessageReportDAO();
	}

	public MessageReportVO addReport(String reportdetail, java.sql.Timestamp reporttime,
			Integer reportstatus, String memno, String postno) {

		MessageReportVO mrVO = new MessageReportVO();

		mrVO.setReportDetail(reportdetail);
		mrVO.setReportTime(reporttime);
		mrVO.setReportStatus(reportstatus);
		mrVO.setMemno(memno);
		mrVO.setPostno(postno);
		
		dao.add(mrVO);

		return mrVO;
	}

	public MessageReportVO updateReport(String reportno,String reportdetail,Timestamp reporttime,Integer reportstatus,String memno,String postno) {

		MessageReportVO mrVO = new MessageReportVO();
		
		mrVO.setReportno(reportno);
		mrVO.setReportDetail(reportdetail);
		mrVO.setReportTime(reporttime);
		mrVO.setReportStatus(reportstatus);
		mrVO.setMemno(memno);
		mrVO.setPostno(postno);
		dao.updateStatus(mrVO);

		return mrVO;
	}

	public MessageReportVO getOneReport(String reportno) {
		return dao.getByno(reportno);
	}

	public List<MessageReportVO> getAll() {
		return dao.getAll();
	}
}
