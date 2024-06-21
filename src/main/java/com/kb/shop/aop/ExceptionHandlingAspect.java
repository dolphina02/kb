package com.kb.shop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Aspect
@Component
public class ExceptionHandlingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    // 얘는 그냥 발생한 지점을 로깅한 후에 바로 globalExceptionHandler에 넘겨서 응답 유형대로 응답 한다.
    // 서비스에서는 exception잡을 필요가 없음
    @Around("execution(* com.kb.shop.service.*.*(..))")
    public Object logAndBypassExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            logger.error("Exception in {}: {}", joinPoint.getSignature().toShortString(), ex.getMessage(), ex);
            throw ex;
        }
    }
}
