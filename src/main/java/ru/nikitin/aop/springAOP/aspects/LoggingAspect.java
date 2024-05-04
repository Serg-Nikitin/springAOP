package ru.nikitin.aop.springAOP.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging methods
 */
@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("ru.nikitin.aop.springAOP.aspects.Pointcuts.forLoggingSync()")
    public void aroundSyncMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long before = System.currentTimeMillis();
        joinPoint.proceed();
        String nameClass = joinPoint.getKind();
        long after = System.currentTimeMillis();
        log.info(("\n").concat(nameClass + '=').concat(String.valueOf(after - before)).concat("\n"));
    }

    @Around("ru.nikitin.aop.springAOP.aspects.Pointcuts.forLoggingAsync()")
    public void aroundAsyncMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long before = System.currentTimeMillis();
        joinPoint.proceed();
        long after = System.currentTimeMillis();
        log.info(("\n").concat(String.valueOf(after - before)).concat("\n"));
    }
}
