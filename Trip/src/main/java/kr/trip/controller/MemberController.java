package kr.trip.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.trip.domain.AuthVO;
import kr.trip.domain.MemberVO;
import kr.trip.service.KakaoLoginService;
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
	public KakaoLoginService kakaoS;
	
	@GetMapping("/login/kakao")
	public void kakaoOauth(@RequestParam(required = false) String code) throws Throwable {
		System.out.println(code);
		
		String access_Token = kakaoS.getAccessToken(code);
		System.out.println("###access_Token#### : " + access_Token);
		
		// 3번
		HashMap<String, Object> userInfo = kakaoS.getUserInfo(access_Token);
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###email#### : " + userInfo.get("email"));
		
	}

}
