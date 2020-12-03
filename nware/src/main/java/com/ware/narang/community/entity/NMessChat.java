package com.ware.narang.community.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "nmess_chat")
public class NMessChat {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "nmess_chat_no")
//	private Long nMessChatNo;
//	
//	@Column(name = "user_id")
//	private Long userId;
//	
//	@Column(name = "username")
//	private String username;
//	
//	@Column(name = "nmess_room_no")
//	private Long nMessRoomNo;
//	
//	@Column(name = "nmess_chat_content")
//	private String nMessChatContent;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nmess_chat_no")
	private Long nMessChatNo;
	
//	@Column(name = "user_id")
//	private Long userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "nmess_room_no")
	private Long nMessRoomNo;
	
	@Column(name = "nmess_chat_content")
	private String nMessChatContent;


}
