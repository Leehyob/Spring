package org.zerock.controller;

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
import org.zerock.domain.pageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/")
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
	public void list(Model model,Criteria cri) {
		log.info("list");
		
		model.addAttribute("pageMaker",new pageDTO(cri, boardService.getTotalCount(cri)));
		model.addAttribute("list",boardService.getListWithPaging(cri));
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") long bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("get, modify"+bno);
		
		model.addAttribute("board",boardService.get(bno));
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register" + board);
		
		boardService.register(board);
		rttr.addFlashAttribute("result",board.getBno());
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr,@ModelAttribute("cri") Criteria cri) {
		log.info("modify"+board);
		
		if(boardService.modify(board)) {
			rttr.addFlashAttribute("result","seccess");
		}
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") long bno,@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove"+bno);
		
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		
		return "redirect:/board/list";
	}
	
}
