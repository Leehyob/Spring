package kr.trip.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;


@Controller
@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		log.info("요청이 거절되었습니다.");
		
		response.sendRedirect("/error");
	}
	
	
	@GetMapping("/customLogin")
	public void login(String error, String logout, Model model) {
		
		if(error != null) {
			model.addAttribute("error","아이디 or 비밀번호가 일치하지 않습니다.");
		}
		
		if(logout != null) {
			model.addAttribute("logout","로그아웃");
		}
	}
	
	

	
	
}
