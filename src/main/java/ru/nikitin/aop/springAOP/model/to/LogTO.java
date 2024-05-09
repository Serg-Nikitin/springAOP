package ru.nikitin.aop.springAOP.model.to;

import java.util.Date;

public record LogTO(Long id, Date start, Date end, Long execute, String type, String className, String methodName) {
}
