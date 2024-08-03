package com.interview.tuncode.configurations.aspects;

import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.exceptions.SourceAlreadyExistsException;
import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
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
    public void afterReturningGetAllAddresses(JoinPoint joinPoint, AppResponse<List<Address>> response) {
        log.info("{} ! \t Method name: {}  \t Response: {} \t Status Code: {}",
                joinPoint.getKind().toUpperCase(),
                joinPoint.getSignature().getName(),
                response.getData(),
                response.getStatus());
    }

    /**
     * For all methods in the "Address Package", it is triggered if an exception occurs after it runs.
     */
    @AfterThrowing(pointcut = "execution(* com.interview.tuncode.services.address..*(..))",
            throwing = "exc")
    public void afterThrowingForServicePackage(JoinPoint joinPoint, Throwable exc) {
        log.error("An error occured ! \t Method name: {} \t Declaring Type: {} \t EXCEPTION Message: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringTypeName(),
                exc.toString());
    }

    @Around("execution(public void updateStudent(..))")
    public Object aroundForAllServicesPackage(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        try {
            long startTime = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long endTime = System.currentTimeMillis();
            log.info("\n {} ! \n Duration milliseconds: {} \n RESULT: {} ",
                    proceedingJoinPoint.getKind().toUpperCase(),
                    endTime - startTime / 1000.0,
                    result);

        } catch (Exception exc) {
            log.error("@Around Exception: {}", exc.toString());
            throw new SourceNotFoundException(exc.getMessage());
        }

        return result;
    }


    @Pointcut("execution(* com.interview.tuncode.controllers..*(..))")
    private void pointcutExpForControllerPackage() {

    }

    @Pointcut("execution(* com.interview.tuncode.services.student.StudentServiceImp.*(..))")
    private void pointCutExpForMethodsOfStudentServiceImpClass() {

    }

}
