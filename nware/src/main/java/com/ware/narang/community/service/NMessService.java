package com.ware.narang.community.service;

import java.util.List;

import com.ware.narang.community.entity.NMessChat;
import com.ware.narang.community.entity.NMessRoom;

public interface NMessService {

	public void createRoom(NMessRoom nmRoom) throws Exception;

	public List<NMessRoom> selectList() throws Exception;

	public List<NMessRoom> selectAll() throws Exception;

	public List<NMessChat> selectChatList(Long parseno) throws Exception;

	public void createMessage(NMessChat nmc)throws Exception;


	
}
