package com.spring.boot.demo.aop;


import com.spring.boot.demo.model.Log;
import com.spring.boot.demo.service.LogService;
import com.spring.boot.demo.service.StudentService;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    // advice (pointcut expression)
    // Advice - before,after,around,AfterThrowing,AfterReturning
    // Pointcut types - execution,args,within

    //return_type package.classname.methodname(input_param)
    //(..) means arguments

    @Autowired
    LogService service;

    @Before(value = "execution(* com.spring.boot.demo.controller.StudentController.*(..))")
    public void logSomethingBefore(JoinPoint jp) {
        Signature signature = jp.getSignature();
        System.out.println("Logging before method " + signature.toString() + " is called");
    }

    @After(value = "execution(* com.spring.boot.demo.controller.StudentController.*(..))")
    public void logSomethingAfter(JoinPoint jp) {
        Signature signature = jp.getSignature();
        System.out.println("Logging after method " + signature.toString() + " is called");
    }

    @Around(value = "execution(* com.spring.boot.demo.controller.StudentController.*(..))")
    public void testAround(ProceedingJoinPoint jp) {
        System.out.println("before exec");
        try {
            jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("after exec");
    }

//    @Pointcut(value = "execution(* com.spring.boot.demo.controller.StudentController.*(..))")
//    public void around(){
//
//    }

    @AfterReturning(value = "execution(* com.spring.boot.demo.controller.LogController.dummyReturn(..))")
    public void testReturn(JoinPoint jp) {
        System.out.println("Testing output of AfterReturning advice");
    }

    @AfterThrowing(value = "execution(* com.spring.boot.demo.controller.LogController.dummyThrow(..))")
    public void testThrow(JoinPoint jp) {
        System.out.println("Testing output of AfterThrowing advice");
    }

    @Around(value = "execution(* com.spring.boot.demo.controller.LogController.timingMethod(..))")
    public void execTime(ProceedingJoinPoint jp) {
        StopWatch sw = new StopWatch();
        System.out.println("before exec");
        sw.start();
        try {
            jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        sw.stop();
        System.out.println(sw.toString());
        Log log = new Log();
        log.setMethod(jp.getSignature().toString());
        log.setTime(sw.getLastTaskTimeMillis());
        service.insertLog(log);
        System.out.println("after exec");
    }
}
