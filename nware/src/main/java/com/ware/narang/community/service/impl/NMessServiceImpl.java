package com.ware.narang.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ware.narang.community.entity.NMessChat;
import com.ware.narang.community.entity.NMessRoom;
import com.ware.narang.community.repository.NMessChatRepository;
import com.ware.narang.community.repository.NMessRoomRepository;
import com.ware.narang.community.service.NMessService;

import lombok.extern.java.Log;

@Log
@Service
public class NMessServiceImpl implements NMessService{
	
	@Autowired
	private NMessRoomRepository nmrRepo;
	
	@Autowired
	private NMessChatRepository nmcRepo;
	
	@Override
	public void createRoom(NMessRoom nmRoom) throws Exception {

		log.info("[ LOG ] NMessServiceImpl/createRoom  nmRoom : " + nmRoom);
		
		nmrRepo.save(nmRoom);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<NMessRoom> selectList() throws Exception {
		log.info("[ LOG ] NMessServiceImpl/selectList ");
		return nmrRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<NMessRoom> selectAll() throws Exception {
		// TODO Auto-generated method stub
		log.info("[ LOG ] NMessServiceImpl/selectAll ");
		
		return nmrRepo.findAll();
	}

	@Override
	public List<NMessChat> selectChatList(Long roomno) throws Exception {

		return nmcRepo.findAllBynMessRoomNo(roomno);
	}

	@Override
	public void createMessage(NMessChat nmc) throws Exception {

		nmcRepo.save(nmc);
		
	}

	

	
//
//	@Override
//	public List<NMessRoomListMapping> selectRoomList() throws Exception {
//		log.info("[ LOG ] NMessServiceImpl/selectRoomList ");
//		return nmrlmRepo.findAll();
//	}

	

//	@Override
//	@Transactional(readOnly = true)
//	public String nMessRoomNameList() throws Exception {
//		// TODO Auto-generated method stub
//		log.info("[ LOG ] NMessServiceImpl/nMessRoomNameList ");
//		//
//		return nmrRepo.findNMessRoomName();
//	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<NMessRoom> nMessRoomList() throws Exception {
//
//		log.info("[ LOG ] NMessServiceImpl/nMessRoomList ");
//
//		return nmrRepo.findAll();
//	}

}
