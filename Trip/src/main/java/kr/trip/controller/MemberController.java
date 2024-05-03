package kr.trip.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.trip.domain.AuthVO;
import kr.trip.domain.MemberVO;
import kr.trip.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bc;
	
	
	
	@GetMapping("/member/join")
	public void join() {
		
	}
	
	
	@PostMapping("/member/join")
	@Transactional
	public String join(MemberVO member, RedirectAttributes rttr) {
		
		member.setPwd(bc.encode(member.getPwd()));
		
		memberService.register(member);
		memberService.insertAuth(new AuthVO(member.getMember_email(),"ROLE_MEMBER"));
		
		
		return "redirect:/customLogin";
	}
	
	@Autowired
	private kakaoOAuth kakaoOAuth; 
	
	@GetMapping("/lkakao")
	public void getKakaoAuthUrl(HttpServletResponse response) throws IOException{
		response.sendRedirect(kakaoOAuth.responseUrl());
	}
	


	
}
