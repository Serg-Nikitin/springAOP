package ru.nikitin.aop.springAOP.testdata;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nikitin.aop.springAOP.aspects.annotations.TrackAsyncTime;
import ru.nikitin.aop.springAOP.aspects.annotations.TrackTime;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Slf4j
public class TestService {

    @TrackTime
    public void service() {
        log.info("service()");
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @TrackAsyncTime
    public void asyncService() throws InterruptedException {
        log.info("asyncService");
        Thread.sleep(1500);
        new CompletableFuture().completeAsync(() -> "asyncService");
    }

}
