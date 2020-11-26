package com.ware.narang.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ware.narang.board.entity.Board;
import com.ware.narang.board.service.BoardService;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bService;
	
	@RequestMapping(value = "/register",  method = RequestMethod.GET)
	public String registerForm(Board board, Model model)throws Exception{
		
		log.info("[ LOG ] BoardController/registerForm GET");
		
		return "board/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Board board, Model model)throws Exception{
		
		log.info("[ LOG ] BoardController/register POST");
		
		bService.register(board);
		
		model.addAttribute("msg", "등록이 완료되었습니다");
		
		return "board/success";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model)throws Exception{
		
		log.info("[ LOG ] BoardController/list GET");
		
		model.addAttribute("list", bService.list());
		
		return "board/list";
	}
	
	
}
