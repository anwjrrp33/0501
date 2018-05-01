package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class LogAdvice {
	
	@Around("execution(* org.zerock.service.CalcService*.*(..))")
	public Object checkTime(ProceedingJoinPoint pjp) throws Throwable {
		
		long start = System.currentTimeMillis();
		
		Object result = null;
		
		log.info("Signature: " + pjp.getSignature());
		
		log.info("Target: " + pjp.getTarget());
		
		log.info("Param: " + Arrays.toString(pjp.getArgs()));
		
		result = pjp.proceed();
		
		long end = System.currentTimeMillis();
		
		log.info("Time: " + (end - start));
		
		return result;
	}
	
	@Before("execution(* org.zerock.service.CalcService*.*(..))")
	public void sample() {
		
		log.info("===================");
	}
}
