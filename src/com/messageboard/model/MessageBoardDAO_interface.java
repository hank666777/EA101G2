package com.messageboard.model;

import java.util.List;

public interface MessageBoardDAO_interface {
	public void add(MessageBoardVO mbVO);
	public void reply(MessageBoardVO mbVO);//回覆
	public void update(MessageBoardVO mbVO);
	public MessageBoardVO getByPostno(String postno);
	public List <MessageBoardVO> getReply(String postno);//查詢某留言底下回覆
	
	public List <MessageBoardVO> getByMemno(String memno);//查詢某會員全部留言	
	public List <MessageBoardVO> getAll();
	

	
	
}
