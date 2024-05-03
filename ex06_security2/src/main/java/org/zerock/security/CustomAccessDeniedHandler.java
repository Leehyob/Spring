package org.zerock.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		log.error("Access Denied Handler");
		log.error("Redirect");
		
		response.sendRedirect("/accessError");
		
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		
		if(error != null) {
			model.addAttribute("error","아이디 or 비밀번호가 일치하지 않습니다.");
		}
		
		if(logout != null) {
			model.addAttribute("logout","로그아웃");
		}
	}

	
	
}
