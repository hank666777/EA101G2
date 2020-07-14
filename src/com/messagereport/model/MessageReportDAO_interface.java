package com.messagereport.model;

import java.util.List;

import com.messageboard.model.MessageBoardVO;

public interface MessageReportDAO_interface {
	public void add(MessageReportVO mrVO);
	public void updateStatus(MessageReportVO mrVO);
	
	public MessageReportVO getByno(String reportno);
	public List <MessageReportVO> getAll();
}
