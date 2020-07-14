package com.messageboard.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import support.SplitSupport;

public class MessageBoardService {
	private MessageBoardDAO_interface dao;

	public MessageBoardService() {
		dao = new MessageBoardDAO();
	}
	
	public MessageBoardVO addMessage(String posttitle,Integer postsort,String postdetail,Timestamp posttime,String memno,String parentno,Integer poststatus) {
		MessageBoardVO mbVO = new MessageBoardVO();
		
		mbVO.setPostTitle(posttitle);
		mbVO.setPostSort(postsort);
		mbVO.setPostDetail(postdetail);
		mbVO.setPostTime(posttime);
		mbVO.setMemno(memno);
		mbVO.setParentno(parentno);
		mbVO.setPostStatus(poststatus);
		dao.add(mbVO);
		return mbVO;
	}
		
	public MessageBoardVO replyMessage(String postdetail,Timestamp posttime,String memno,String parentno,Integer poststatus) {
		MessageBoardVO mbVO = new MessageBoardVO();
		
		
		mbVO.setPostDetail(postdetail);
		mbVO.setPostTime(posttime);
		mbVO.setMemno(memno);
		mbVO.setParentno(parentno);
		mbVO.setPostStatus(poststatus);
		dao.reply(mbVO);
		return mbVO;
	}
	
	public MessageBoardVO updateMessage(String postno,String posttitle,Integer postsort,String postdetail,Timestamp posttime,String memno,String parentno,Integer poststatus) {
		MessageBoardVO mbVO = new MessageBoardVO();
		mbVO.setPostno(postno);
		mbVO.setPostTitle(posttitle);
		mbVO.setPostSort(postsort);
		mbVO.setPostDetail(postdetail);
		mbVO.setPostTime(posttime);
		mbVO.setMemno(memno);
		mbVO.setParentno(parentno);
		mbVO.setPostStatus(poststatus);
		
		dao.update(mbVO);
		return mbVO;
	} 
	
	public List<MessageBoardVO> getByMemno(String memno){
		List<MessageBoardVO> list = dao.getByMemno(memno).stream()
				.filter(mb -> mb.getPostStatus().equals(1))
				.collect(Collectors.toList());
		return list;
	}
	
	public MessageBoardVO getOneMessage(String postno) {
		
		return dao.getByPostno(postno);
	}
	
	public List<MessageBoardVO> getReply(String postno) {
		List<MessageBoardVO> list = dao.getReply(postno).stream()			
				.filter(mb -> mb.getPostStatus().equals(1))
				.collect(Collectors.toList());
		
		return list;
	}
	
	public List<MessageBoardVO> getByPostSort(Integer postsort) {
		
		List<MessageBoardVO> list = dao.getAll().stream()
				.filter(mbvo -> mbvo.getParentno().equals("0"))
				.filter(mb -> mb.getPostSort().equals(postsort))
				.collect(Collectors.toList());
		return list;
	}
	

	public List<MessageBoardVO> getAll() {
		/*SE8 filter*/
		List<MessageBoardVO> list = dao.getAll().stream()
				.filter(mb -> mb.getPostStatus().equals(1))
				.filter(mbvo -> mbvo.getParentno().equals("0"))
				.collect(Collectors.toList());
		

		
		return list;
	}

}
