package com.cloudcuisine.customerservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    @Pointcut("execution(* com.cloudcuisine.customerservice.service..*(..))")
    public void serviceMethods() {}

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logException(JoinPoint jp, Throwable ex) {
        System.out.println("Exception in " + jp.getSignature().getName() + ": " + ex.getMessage());
        ex.printStackTrace(System.out);
    }
}