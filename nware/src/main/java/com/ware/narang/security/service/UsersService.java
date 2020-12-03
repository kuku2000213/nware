package com.ware.narang.security.service;

import java.util.List;
import java.util.Map;

import com.ware.narang.security.entity.Authorities;
import com.ware.narang.security.entity.Users;

public interface UsersService {

	public List<Users> uList() throws Exception;

	public List<Authorities> aList() throws Exception;

	public void uSignUp(Users users) throws Exception;

	public void aSignup(Authorities auth) throws Exception;

//	public Long selectUserId(String username) throws Exception;

	public Users selectAllFromUsername(String username) throws Exception;



}
