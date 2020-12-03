package com.ware.narang.security.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import com.ware.narang.community.entity.NMessRoom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long user_id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private char enabled = '1';
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Set<Authorities> authorities = new HashSet<Authorities>();
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Set<NMessRoom> nMessRoom = new HashSet<NMessRoom>();
	
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "username")
//	private List<NMessRoom> nMessRoomName = new ArrayList<NMessRoom>();
//	
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "username")
//	private List<NMessChat> nMessRoomChatName = new ArrayList<NMessChat>();

}
