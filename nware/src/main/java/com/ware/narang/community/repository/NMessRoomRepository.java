package com.ware.narang.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ware.narang.community.entity.NMessRoom;

public interface NMessRoomRepository extends JpaRepository<NMessRoom, Long>{

//	List<NMessRoom> findNMessRoomNameAndUserId();

//	List<NMessRoom> getNMessRoomNameAndUserId();

//	String findNMessRoomName();

}
