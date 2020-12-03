package com.ware.narang.community.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ware.narang.community.entity.NMessChat;
import com.ware.narang.community.entity.NMessRoom;
import com.ware.narang.community.service.NMessService;
import com.ware.narang.security.entity.Users;
import com.ware.narang.security.service.UsersService;

import lombok.extern.java.Log;

@Log
@Controller
public class NMessController {
	
	@Autowired
	private NMessService nService;
	
//	@Autowired
//	private NMessRoomListMappingRepository nRepo;
	
	@Autowired
	private UsersService uService;
	
	@RequestMapping("/roomList")
	public String roomList(Model model)throws Exception {
		
		log.info("[ LOG ] NMessController/roomList start...  " );
		
//		List<NMessRoom> nmessroomlist =
//		log.info("[ LOG ] NMessController/roomList   " );
//		log.info("[ LOG ] ------> nmessroomlist : " + nmessroomlist );
		
//		List<NMessRoomListMapping> nmessroomnamelist = nService.selectRoomList();
		
		//실제로직
//		 List<NMessRoom> list = nService.selectList();
//		 log.info("[ LOG ] ------> list : " + list);
//		model.addAttribute("nmessroomnamelist", list);
//		log.info("[ LOG ] NMessController/roomList end...  " );
		return "community/nMessRoomList";
	}
	
//	@RequestMapping("/nMess/createRoom")
//	public String createRoom(
//			@RequestParam(value = "nMessRoomName")String nMessRoomName,
//			HttpSession session,
//			Principal principal,
//			Model model) throws Exception {
//			//principal 현재유저정보
//		log.info("[ LOG ] NMessController/createRoom   " );
//		log.info("[ LOG ] ------> nMessRoomName : " + nMessRoomName);
//		
////		String username = (String) session.getAttribute("Username");
//		
////		Users users = new Users();
////		String username = users.getUsername();
//		
//		String username = principal.getName();
//		log.info("[ LOG ] ------> username : " + username);
//
//		
////		String username = loginUser.getUsername();
////		log.info("[ LOG ] ------> username" + username);
//		
//		Users users = uService.selectAllFromUsername(username);
//		log.info("[ LOG ] ------> users : " + users);
//		
//		Long user_id = users.getUser_id();
////		Long user_id = uService.selectUserId(username);
//		log.info("[ LOG ] ------> user_id : " + user_id);
//		
//		if(user_id != null) {
//			log.info("[ LOG ] ------> user_id" + user_id);
//			
//			NMessRoom nmRoom = new NMessRoom();
//			
//			nmRoom.setUsername(username);
//			nmRoom.setUserId(user_id);
//			nmRoom.setNMessRoomName(nMessRoomName);
//			
//			nService.createRoom(nmRoom);
//		}
//		
//		//Long user_id = loginUser.getUser_id();
//		
//		return "community/nMessChatRoom";
//	}
	
	@MessageMapping("messlist")
	@SendTo("/nmess/list")
	public List<NMessRoom> showNMessList(String roomName,Principal principal, String wakeup)throws Exception{
		log.info("[ LOG ] NMessRoom/showNMessList [ LOGIC ] start....");
		List<NMessRoom> nmrList = new ArrayList<NMessRoom>();
		log.info("[ LOG ] NMessRoom/showNMessList  roomName : " + roomName);
		
		String parseroomName1 = roomName.substring(13);
		String[]mobNum = parseroomName1.split("\"");
		String parseroomName2 = mobNum[0];
		log.info("[ LOG ] ------> parseroomName2 :" + parseroomName2);
		String keup = "keup";
		
		if(parseroomName2.equals(keup)) {
			log.info("[ LOG ] ------> !wakeup!");
			nmrList = nService.selectAll();
			
		}else {
			NMessRoom nmr = new NMessRoom();
			
			String username = principal.getName();
			log.info("[ LOG ] ------> username : " + username);

			
//			String username = loginUser.getUsername();
//			log.info("[ LOG ] ------> username" + username);
			
			Users users = uService.selectAllFromUsername(username);
			log.info("[ LOG ] ------> users : " + users);
			
			Long user_id = users.getUser_id();
//			Long user_id = uService.selectUserId(username);
			log.info("[ LOG ] ------> user_id : " + user_id);
			
			if(user_id != null) {
				
				nmr.setUsername(username);
				nmr.setUserId(user_id);
				nmr.setNMessRoomName(parseroomName2);
//				nmr.setNMessRoomName(roomName);
				nService.createRoom(nmr);
				nmrList = nService.selectAll();	
			}
			
		}
		return nmrList;
	}
	
	// 해당 메시지 정보보내기
	@RequestMapping(value = "/nMess/chatRoom", method = RequestMethod.GET)
	public String chatRoom(@RequestParam(value = "chatroomno")String roomno, Model model
			,Principal principal) {
		
		String username = principal.getName();
		log.info("[ LOG ] NMessController/chatRoom  roomno : " + roomno);
		log.info("[ LOG ] NMessController/chatRoom  username : " + username);
		
		model.addAttribute("roomno", roomno);
		model.addAttribute("username", username);
		
		return "community/nMessChatRoom";
	}
	
	@MessageMapping("welcomchat")
	@SendTo("/nmess/welcome")
	public String shownmesschat(String username){
		
		log.info("[ LOG ] NMessController/shownmesschat  username : " + username);
		
//		String[]mobNum = username.split("\"");
//		String username2 = mobNum[3];
		
//		String welcome = username2 + " 님이 입장하셨습니다.";
//		String welcome = username + " 님이 입장하셨습니다.";
		
//		log.info("[ LOG ] NMessController/shownmesschat  welcome : " + welcome);
		
		return username;
		
	}
	
	@MessageMapping("chatlist")
	@SendTo("/nmess/chatinfo")
	public List<NMessChat> chatcontrol(String roomno, Principal principal)throws Exception{
		log.info("[ LOG ] NMessRoom/chatcontrol  roomno : " + roomno);
		
		List<NMessChat>nmclist = new ArrayList<NMessChat>();
		NMessChat nmc = new NMessChat();
		
		String[]mobNum = roomno.split("\"");
		String parseno1 = mobNum[3];
		String parseconfirm = mobNum[5];
		String parsechat = mobNum[7];
		
		Long parseno = Long.parseLong(parseno1);
		
		String username = principal.getName();
//		Users users = uService.selectAllFromUsername(username);
//		log.info("[ LOG ] NMessRoom/sendcontrol  username : " + username);
//		Long user_id = users.getUser_id();
		
		if(parseconfirm.equals("chatno")) {
			log.info("[ LOG ] NMessRoom/chatcontrol equals Completed! ");
			
			log.info("[ LOG ] NMessRoom/chatcontrol  parseno : " + parseno);
			log.info("[ LOG ] NMessRoom/chatcontrol  parsechat : " + parseconfirm);
			nmclist = nService.selectChatList(parseno);
		}else {
			log.info("[ LOG ] NMessRoom/chatcontrol !!equals Completed! ");
			log.info("[ LOG ] NMessRoom/chatcontrol  parsechat : " + parsechat);
			
//			nmc.setUserId(user_id);
			nmc.setUsername(username);
			nmc.setNMessRoomNo(parseno);
			nmc.setNMessChatContent(parsechat);
			nService.createMessage(nmc);
			nmclist = nService.selectChatList(parseno);
		}
		
		
		
		return nmclist;
		
	}
	
//	@MessageMapping("chatsend")
//	@SendTo("/nmess/chatcon")
//	public NMessChat sendcontrol(String sendcon, Principal principal)throws Exception{
//		log.info("[ LOG ] NMessRoom/sendcontrol  sendcon : " + sendcon);
//		
//		String[]mobNum = sendcon.split("\"");
//		String msgcontent = mobNum[3];
//		String chatroomno = mobNum[7];
//		
//		log.info("[ LOG ] NMessRoom/sendcontrol  msgcontent : " + msgcontent);
//		log.info("[ LOG ] NMessRoom/sendcontrol  chatroomno : " + chatroomno);
//		
//		NMessChat nmc = new NMessChat();
//		
//		String username = principal.getName();
//		Users users = uService.selectAllFromUsername(username);
//		log.info("[ LOG ] NMessRoom/sendcontrol  username : " + username);
//		
//		Long user_id = users.getUser_id();
//		Long parseno = Long.parseLong(chatroomno);
//		
//		if(user_id != null) {
//			
//			nmc.setUserId(user_id);
//			nmc.setUsername(username);
//			nmc.setNMessRoomNo(parseno);
//			nmc.setNMessChatContent(msgcontent);
//			nService.createMessage(nmc);
//		}
//		
//		return nmc;
//		
//	}

}
