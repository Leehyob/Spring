package org.zerock.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;

@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/")
public class BoardController {
	
	private final BoardService boardService;
	
	/*
	 * @GetMapping("/list") public void list(Model model){ log.info("list");
	 * 
	 * model.addAttribute("list",boardService.getList()); }
	 */
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list : " + cri);
		
		int total = boardService.getTotalCount(cri);
		
		log.info(total);
		
		model.addAttribute("pageMaker",new PageDTO(cri, boardService.getTotalCount(cri)));
		model.addAttribute("list",boardService.getListWithPaging(cri));
	}
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
		
	}
	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {
	
		log.info("register" + board);
		
		boardService.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("get");
		
		model.addAttribute("board",boardService.get(bno));
		
	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("modify");
		
		model.addAttribute("board",boardService.get(bno));
		
	}
	
	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify" + board);
		
		if(boardService.modify(board)) {
			rttr.addFlashAttribute("result","modify");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") long bno,  @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr,@RequestParam("writer") String writer) {
		log.info("remove" + bno);
		
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result","delete");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	
}
