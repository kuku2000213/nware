package com.ware.narang.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ware.narang.security.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

//	Long findIdByName(@Param("username")String username);

//	Long findIdByUserName(@Param("username")String username);

//	@Query("select user_id from users where username = :username")
//	Long findUser_idByUsername(@Param("username")String username);

	Users findAllByUsername(@Param("username") String username);

//	Users findByName(@Param("username") String username);
//
//	Users findIdByName(@Param("username") String username);
}
