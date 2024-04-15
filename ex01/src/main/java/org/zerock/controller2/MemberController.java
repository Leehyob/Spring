package org.zerock.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@RequestMapping("/hello")
	public String hello() {
		log.info("hello");
		return "hello";
	}
}
	
