package kr.com.ezen.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.com.ezen.dto.MemberVO;
import kr.com.ezen.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/")
	public String member(Model model) {
		log.info("------------------");
		String msg = "이효빈";
		model.addAttribute("name", msg);
		return "member";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<MemberVO> list = memberService.selectAllMember();

		list.forEach(vo -> log.info(vo));

		model.addAttribute( "memberlist", list);
		return "memberList";
	}

	@PostMapping("/insert")
	public String insert(@RequestBody MemberVO vo) {
		
		log.info("-------------------------");
		log.info(vo);
		
		memberService.insertMember(vo);
		
		return null;
	}

	@DeleteMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		log.info(id);
		
		memberService.deleteMember(id);
		return null;
	}
	
}
