package com.kb.shop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProcessingTimeCal {


    @Around("execution(* com.kb.shop.controller.*.*(..)) ") // 패키지 및 메서드 경로에 맞게 변경
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime; // 시간차를 파악하여

        System.out.println("Method : " + joinPoint.getSignature() + " RunningTime : " + executionTime + "ms");

        return result;
    }
}
