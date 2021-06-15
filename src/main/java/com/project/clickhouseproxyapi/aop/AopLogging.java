package com.project.clickhouseproxyapi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class AopLogging {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("@annotation(com.project.clickhouseproxyapi.aop.annotations.CustomLogging)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Method Name : {} | Args => {} Time: {}", joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()), LocalDateTime.now());
    }

    @AfterReturning(value = "@annotation(com.project.clickhouseproxyapi.aop.annotations.CustomLogging)", returning = "returnValue")
    public void logAfterReturning(JoinPoint joinPoint, Object returnValue) {
        if (returnValue != null) {
            logger.info("Result: {} Time: {}", returnValue, LocalDateTime.now());
        } else {
            logger.info("No value");
        }
    }

    @AfterThrowing(value = "@annotation(com.project.clickhouseproxyapi.aop.annotations.CustomLogging)", throwing = "throwing")
    public void logAfter(JoinPoint joinPoint, Throwable throwing) {
        if(throwing != null) {
            logger.info("Throw: {} ", throwing);
        } else {
            logger.info("No throw");
        }
    }
}
