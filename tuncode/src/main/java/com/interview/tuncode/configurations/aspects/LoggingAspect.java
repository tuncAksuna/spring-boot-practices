package com.interview.tuncode.configurations.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * TODO :  pointcutExpForServicesPackage ÇALIŞMIYOR
     */

    @Before("pointcutExpForControllerPackage()")
    public void logBefore() {
        log.info("testLogging method executed ! @Before controller");
        System.out.println("testLogging method executed ! @Before controller");
    }

    @Pointcut("execution(* com.interview.tuncode.controllers..*(..))")
    private void pointcutExpForControllerPackage() {

    }

    @Pointcut("execution(* com.interview.tuncode.controllers.student.StudentController.testLoggingAspect(..))")
    private void pointcutExpForTestLoggingAspectMethod() {
    }

    @Pointcut("execution(* com.interview.tuncode.services.student.StudentServiceImp.*(..))")
    private void pointCutExpForMethodsOfStudentServiceImpClass() {

    }

}
