package com.ware.narang.board.service;

import java.util.List;

import com.ware.narang.board.entity.Board;

public interface BoardService {
	
	public void register(Board board)throws Exception;

	public List<Board> list() throws Exception;

}
