package com.ware.narang.community.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "chat")
public class Chat {

	@Id
	@Column(name = "name")
	private String name;
	
	@Column(name = "message")
	private String message;
}
