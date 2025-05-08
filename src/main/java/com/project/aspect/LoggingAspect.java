package com.project.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.project.controller.*.*(..)) || execution(* com.project.service.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String typeClass = joinPoint.getTarget().getClass().isAnnotationPresent(Service.class) ?
                "сервис" : "контроллер";
        String args = (joinPoint.getArgs() == null) ? "null" : Arrays.toString(joinPoint.getArgs());
        log.info("Вызван {}: {} c аргументами: {}", typeClass, joinPoint.getSignature(), args);
    }

    @Around("pointcut()")
    public Object LogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object result = joinPoint.proceed();

        log.info("Метод {}.{} выполнен. Результат: {}", className, methodName, result);

        return result;
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Исключение в {}.{}() с причиной = {} - ({})",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                e.getClass().getSimpleName(),
                e.getMessage());
    }
}
