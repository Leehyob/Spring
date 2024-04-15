package org.zerock.exception;

import java.net.BindException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

@Log4j
@ControllerAdvice
public class CommonExceptionAdvice {
	
	@ExceptionHandler
	public String except(Exception ex, Model model) {
		
		log.error("Exception : " + ex.getMessage());
		model.addAttribute("exception",ex);
		log.error(model);
		
		return "error_page";
	}
	
	@ExceptionHandler(BindException.class)	//이미 사용중인 주소
	public String bindException(BindException ex, Model model) {
		model.addAttribute("msg", ex);
		return "error_page2";			
	}
}
