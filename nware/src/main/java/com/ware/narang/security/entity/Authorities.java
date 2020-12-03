package com.ware.narang.security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "authorities")
@ToString
public class Authorities {
	
	@Id
	@Column(name = "user_id")
	private long user_id;
	
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "authority")
	private String authority;
	
//	@ManyToOne
//	@JoinColumn(name = "id")
//	private Users users;
//	

}
