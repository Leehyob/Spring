package kr.trip.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	@GetMapping("/main")
	public void main() {
		
	}
	
	@GetMapping("/error")
	public void error(Authentication auth, Model model) {
		model.addAttribute("msg","요청이 거절되었습니다.");
	}
	
}
