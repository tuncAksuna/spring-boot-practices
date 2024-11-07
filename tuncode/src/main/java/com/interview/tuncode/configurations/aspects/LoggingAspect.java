package com.interview.tuncode.configurations.aspects;

import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.model.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
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


    /**
     * Runs before and after all methods using the "ProceedingJoinPoint" object
     * If it throws an exception while proceeding, this exception is caught,
     * an error log is printed on the console, and the exception is thrown back again (SourceNotFoundException).
     */
    @Around(value = "@annotation(calculatePerform)", argNames = "proceedingJoinPoint,calculatePerform")
    public Object aroundForAllServicesPackage(ProceedingJoinPoint proceedingJoinPoint, CalculatePerform calculatePerform) throws Throwable {
        Object result;
        MethodSignature ms = (MethodSignature) proceedingJoinPoint.getSignature();

        try {
            log.info("{} triggered !", ms.getMethod());
            result = proceedingJoinPoint.proceed();
            log.info("{} method successfully executed !", ms.getMethod());
        } catch (Exception exc) {
            long startTime = System.currentTimeMillis();
            long endTime = System.currentTimeMillis();
            log.error("An error occurred !\n{} !\nMethod args: {}\nMethod: {}\nDuration milliseconds: {}",
                    proceedingJoinPoint.getKind().toUpperCase(),
                    proceedingJoinPoint.getArgs(),
                    ms.getMethod(),
                    endTime - startTime / 1000.0);
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
