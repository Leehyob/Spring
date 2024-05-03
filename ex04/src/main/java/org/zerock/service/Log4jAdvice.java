package org.zerock.service;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Aspect
@Service
@Log4j
public class Log4jAdvice {

	@Pointcut("execution(* org.zerock.service.ReplyServiceImpl.*(..))")
	public void allPointCut() {	}
	
	@After("allPointCut()")
	public void log4jAdvice() {
		log.info("[ReplyController] 로그 기록");
	}
}
