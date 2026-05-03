package com.cloudcuisine.customerservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityCheckAspect {
    
    @Pointcut("within(com.cloudcuisine.customerservice.controller..*) && " +
            "(@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping))")
    public void modifyingEndpoints() {}

    @Before("modifyingEndpoints()")
    public void checkAuthentication(JoinPoint jp) {
        // placeholder auth check
        boolean authenticated = true; // replace with real check later
        if (!authenticated) {
            System.out.println("Unauthorized access attempt to " + jp.getSignature().getName());
            throw new SecurityException("User not authenticated");
        }
        System.out.println("Authenticated request to " + jp.getSignature().getName());
    }
}
