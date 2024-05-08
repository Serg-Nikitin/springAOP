package ru.nikitin.aop.springAOP.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitin.aop.springAOP.model.Log;
import ru.nikitin.aop.springAOP.repository.LogRepository;

import java.util.concurrent.CompletableFuture;
@Slf4j
@Service
public class LogService {
    @Autowired
    private final LogRepository repository;


    public LogService(LogRepository repository) {
        this.repository = repository;
    }

    public void save(Log note) {
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                repository.save(note);
                log.info(note.toString());
            }
        }).exceptionally(ex-> {
            log.error(ex.toString());
          return null;
        });
    }
}
