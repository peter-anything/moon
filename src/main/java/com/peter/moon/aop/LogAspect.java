package com.peter.moon.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(* com.peter.moon.controller.*.*(..))")
    public void perform() {
    }


    @Around("perform()")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        obj = joinPoint.proceed();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        System.out.println(httpServletRequest);
        System.out.println(joinPoint.getTarget());
        System.out.println(joinPoint.getSignature().getName());
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        return obj;
    }
}
