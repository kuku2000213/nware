package com.ware.narang.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ware.narang.board.entity.Board;
import com.ware.narang.board.repository.BoardRepository;
import com.ware.narang.board.service.BoardService;

import lombok.extern.java.Log;

@Log
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	@Transactional
	public void register(Board board) throws Exception {
		
		log.info("[ LOG ] BoardServiceImpl/register  board : " + board);
		
		boardRepository.save(board);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Board> list() throws Exception {
		
		log.info("[ LOG ] BoardService/list");
		
		return boardRepository.findAll(Sort.by(Direction.DESC, "boardNo"));
	}

}
