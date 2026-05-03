package com.cloudcuisine.customerservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitoringAspect {

    @Pointcut("execution(* com.cloudcuisine.customerservice.service...*(..))")
    public void serviceLayer() {}

    @Around("serviceLayer()")
    public Object measureExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long durationMs = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Performance: " + proceedingJoinPoint.getSignature().getDeclaringType() + "#"
            + proceedingJoinPoint.getSignature().getName() + " took " + durationMs);
        }
    }
}
