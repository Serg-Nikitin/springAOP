package ru.nikitin.aop.springAOP.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
public class Pointcuts {

    @Pointcut("execution(@ru.nikitin.aop.springAOP.aspects.annotations.TrackTime public * * (..)) ")
    public void forLoggingSync() {
    }

    @Pointcut("execution(@ru.nikitin.aop.springAOP.aspects.annotations.TrackAsyncTime public * * (..)) ")
    public void forLoggingAsync() {
    }

}
