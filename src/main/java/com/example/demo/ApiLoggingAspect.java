package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ApiLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(ApiLoggingAspect.class);

    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void controllerPointcut() {}

    @Around("controllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String httpMethod = "";
        String requestURI = "";

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            httpMethod = request.getMethod();
            requestURI = request.getRequestURI();
        }

        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[API 요청 수행 완료] method={} url={} elapsedTime={}ms", httpMethod, requestURI, elapsedTime);

            return result;

        } catch (Throwable throwable) {
            long elapsedTime = System.currentTimeMillis() - startTime;

            log.error("[API 요청 수행 완료] method={} url={} elapsedTime={}ms | Exception: {}", httpMethod, requestURI, elapsedTime, throwable.getMessage());

            throw throwable;
        }
    }
}