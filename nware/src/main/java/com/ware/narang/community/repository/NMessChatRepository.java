package com.ware.narang.community.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ware.narang.community.entity.NMessChat;

public interface NMessChatRepository extends JpaRepository<NMessChat, Long>{

	List<NMessChat> findAllBynMessRoomNo(Long roomno);

	void save(List<NMessChat> nmc);

}
