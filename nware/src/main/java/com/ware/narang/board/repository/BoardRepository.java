package com.ware.narang.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ware.narang.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
