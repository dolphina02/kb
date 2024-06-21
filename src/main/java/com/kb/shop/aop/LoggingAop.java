package com.kb.shop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAop {

    // 해당 서비스 수행전에 로그를 남김
    @Before("execution(* com.kb.shop.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before execution: " + joinPoint.getSignature());
    }

    // 해당 서비스 수행후에 로그를 남김
    @After("execution(* com.kb.shop.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {

        System.out.println("After execution: " + joinPoint.getSignature());
    }
}
