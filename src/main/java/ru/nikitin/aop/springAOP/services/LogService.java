package ru.nikitin.aop.springAOP.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikitin.aop.springAOP.model.ClassName;
import ru.nikitin.aop.springAOP.model.Log;
import ru.nikitin.aop.springAOP.model.TypeExecution;
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
    private final ClassNameService classNameService;


    public LogService(LogRepository repository, ClassNameService classRepo) {
        this.repository = repository;
        this.classNameService = classRepo;
    }

    public void save(Date before, Date after, TypeExecution type) {
        String name = "Test";
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                ClassName byName = classNameService.getByName(name);
                ClassName clazz;
                        /*Optional.ofNullable(classNameService.getByName(name))
                                .orElse(classNameService.save(new ClassName(name)))*/

                if (byName != null) {
                    clazz = byName;
                } else {
                    clazz = classNameService.save(new ClassName(name));
                }

                Log note = new Log(before, after, type, clazz);
                repository.save(note);
                log.info(note.toString());
            }
        }).exceptionally(ex -> {
            log.error(ex.toString());
            return null;
        });
    }

    public Log getLogById(Long id) {
        return repository.findLogById(id);
    }
}
