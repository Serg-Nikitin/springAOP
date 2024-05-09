package ru.nikitin.aop.springAOP.services;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikitin.aop.springAOP.model.LogRow;
import ru.nikitin.aop.springAOP.model.TypeExecution;
import ru.nikitin.aop.springAOP.model.converter.LogHelperService;
import ru.nikitin.aop.springAOP.repository.LogRepository;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Transactional(readOnly = true)
public class LogService {
    @Autowired
    private final LogRepository repository;


    @Autowired
    private final LogHelperService logService;


    public LogService(LogRepository repository, LogHelperService logService) {
        this.repository = repository;
        this.logService = logService;
    }

    public void save(Date before, Date after, TypeExecution type, Signature signature) {
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                String className = logService.getClassName(signature);
                String methodName = logService.getMethodName(signature);
                LogRow row = new LogRow(
                        before,
                        after,
                        logService.calculateExecute(before, after),
                        type,
                        className,
                        methodName
                );
                repository.save(row);
                log.info(row.toString());
            }
        }).exceptionally(ex -> {
            log.error(ex.toString());
            return null;
        });
    }

    public LogRow getLogById(Long id) {
        return repository.findLogById(id);
    }
}
