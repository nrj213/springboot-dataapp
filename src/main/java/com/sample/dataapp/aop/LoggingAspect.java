package com.sample.dataapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Configuration
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("[yyyy-mm-dd hh:mm:ss:SSS]");
        }
    };

    @Pointcut("within(com.sample.dataapp..*) && execution(* com.sample.dataapp.topic.TopicController.*(..))")
    public void allMethods() {
    }

    @Before("allMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info(sdf.get().format(new Date()) + " Entering " + methodName);
    }

    @After("allMethods()")
    public void logMethodFinish(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info(sdf.get().format(new Date()) + " Exiting " + methodName);
    }

//    @Around("allMethods()")
//    public void logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//
//        joinPoint.proceed();
//
//        long timeTaken = System.currentTimeMillis() - startTime;
//        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
//    }

}
