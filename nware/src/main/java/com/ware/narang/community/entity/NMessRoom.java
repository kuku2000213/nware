package com.ware.narang.community.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "nmess_room")
public class NMessRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nmess_room_no")
	private Long nMessRoom;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "nmess_room_name")
	private String nMessRoomName;
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "nmess_room_name")
//	private Set<NMessChat> nMessChatRoomName = new HashSet<NMessChat>();
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "user_id")
//	private Set<NMessChat>nMessChatUserId = new HashSet<NMessChat>();

}
