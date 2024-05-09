package ru.nikitin.aop.springAOP.aspects;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nikitin.aop.springAOP.model.TypeExecution;
import ru.nikitin.aop.springAOP.services.LogService;

import java.util.Date;

/**
 * Aspect for logging methods
 */
@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Autowired
    private final LogService service;

    public LoggingAspect(LogService service) {
        this.service = service;
    }

    @Around("ru.nikitin.aop.springAOP.aspects.Pointcuts.forLoggingSync()")
    public void aroundSyncMethods(ProceedingJoinPoint joinPoint) {
        writeLog(joinPoint, TypeExecution.SYNC);
    }

    @Around("ru.nikitin.aop.springAOP.aspects.Pointcuts.forLoggingAsync()")
    public void aroundAsyncMethods(ProceedingJoinPoint joinPoint) {
        writeLog(joinPoint, TypeExecution.ASYNC);
    }

    private void writeLog(ProceedingJoinPoint joinPoint, TypeExecution type) {
        Signature signature = null;
        Date before = new Date();
        try {
            joinPoint.proceed();
            signature = joinPoint.getSignature();
        } catch (Throwable e) {
            log.error("writeLog type = ".concat(type.getTypeName()), e);
        }
        Date after = new Date();
        service.save(before, after, type, signature);
    }
}