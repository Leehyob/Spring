package org.zerock.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.rules.Stopwatch;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

@Aspect
@Service
@Log4j
public class LogAdvice {
	
	@Pointcut("execution(* org.zerock.service.BoardServiceImpl.modify*(..))")
	public void allPointCut() {	}
	
	@Around("allPointCut()")
	public Object logAdvice(ProceedingJoinPoint pjp) throws Throwable{
		/*
		 * String method = pjp.getSignature().getName(); 
		 * Object[] arg = pjp.getArgs();
		 * 
		 * log.info("-----------------------------"); 
		 * log.info("method : " + method);
		 * log.info("arg : " + arg);
		 * 
		 * StopWatch watch = new StopWatch(); 
		 * watch.start(); //함수 시작 시간 
		 * Object obj =pjp.proceed(); //함수 호출 
		 * watch.stop(); //함수 종료 시간
		 * 
		 * log.info(method + "() 메소드 수행에 걸린 시간 : " + watch.getTotalTimeMillis());
		 * log.info(obj); log.info("-----------------------------");
		 */
		
		String method = pjp.getSignature().getName();
		log.info(method + "-------------------------");
		Object obj = pjp.proceed();
		
		return obj;
	}
	
}
