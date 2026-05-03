package com.cloudcuisine.customerservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.cloudcuisine.customerservice.service.impl.CustomerServiceImpl.*(..))")
    public void logBeforeCustomerServicesExecution(JoinPoint joinPoint) {
        System.out.println("Before "+ joinPoint.getSignature().getName());
        System.out.println("Args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* com.cloudcuisine.customerservice.service.impl.CustomerServiceImpl.*(..))")
    public void logAfterCustomerServicesExecution(JoinPoint joinPoint) {
        System.out.println("After "+ joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "execution(* com.cloudcuisine.customerservice.service.impl.CustomerServiceImpl.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method "+ joinPoint.getSignature().getName());
        System.out.println("Returned "+ result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.cloudcuisine.customerservice.service.impl.CustomerServiceImpl.*(..))",
            throwing = "exceptionnnn"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Exception exceptionnnn) {
        System.out.println("Method "+ joinPoint.getSignature().getName());
        System.out.println("Threw "+ exceptionnnn.getMessage());
    }

    @Around("execution(* com.cloudcuisine.customerservice.service.impl.CustomerServiceImpl.*(..))")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around before Method "+ proceedingJoinPoint.getSignature().getName());
        Object result = proceedingJoinPoint.proceed();
//        something like this also we can manipulate
//        if (result instanceof String) {
//            result = ((String) result).toUpperCase(); // change the result
//        }
        System.out.println("Around after Method "+ proceedingJoinPoint.getSignature().getName());
        System.out.println("Around returned "+ result);
        return result;
    }

/* Reusable Pointcuts
    // You create a method annotated with @Pointcut.
    //The method body is empty — it’s just a marker.
    @Pointcut("execution(* com.cloudcuisine.customerservice.service.impl.CustomerServiceImpl.*(..))")
    public void customerServiceMethods() {
        // reusable pointcut definition
    }

    Reference the pointcut in advices
    Instead of repeating the execution(...) expression, you reference the method name.

    java
    @Before("customerServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "customerServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Returned: " + result);
    }

    @Around("customerServiceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        System.out.println("Around returned: " + result);
        return result;
    }

    Flexibility → you can combine pointcuts using logical operators (&&, ||, !).
    @Pointcut("customerServiceMethods() || daoMethods()")
    public void serviceOrDaoMethods() { }
*/

}
