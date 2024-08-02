package com.interview.tuncode.configurations.aspects;

import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * It is triggered
     * When all classes in all packages under the Controller package and all methods within the classes are called.
     */
    @Before("pointcutExpForControllerPackage()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("{} ! \t Method name: {} \t Declaring type: {}",
                joinPoint.getKind().toUpperCase(),
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringTypeName());
    }

    /**
     * Triggered after "getAllAddresses" method runs successfully
     */
    @AfterReturning(pointcut = "execution(* com.interview.tuncode.controllers.address.AdressController.getAllAdresses(..))",
            returning = "response")
    public void afterReturningCreateStudent(JoinPoint joinPoint, AppResponse<List<Address>> response) {
        log.info("{} ! \t Method name: {}  \t Response: {} \t Status Code: {}",
                joinPoint.getKind().toUpperCase(),
                joinPoint.getSignature().getName(),
                response.getData(),
                response.getStatus());
    }

    @Pointcut("execution(* com.interview.tuncode.controllers..*(..))")
    private void pointcutExpForControllerPackage() {

    }

    @Pointcut("execution(* com.interview.tuncode.services.student.StudentServiceImp.*(..))")
    private void pointCutExpForMethodsOfStudentServiceImpClass() {

    }

}
