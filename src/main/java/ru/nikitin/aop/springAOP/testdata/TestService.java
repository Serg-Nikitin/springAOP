package ru.nikitin.aop.springAOP.testdata;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nikitin.aop.springAOP.aspects.annotations.TrackAsyncTime;
import ru.nikitin.aop.springAOP.aspects.annotations.TrackTime;

import java.util.concurrent.CompletableFuture;

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

    @TrackTime
    public void service(int id) {
        log.info("service(int id) id = {}", id);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @TrackTime
    public void service(TestService serv) {
        log.info("service(TestService serv) serv = {}", serv);
        try {
            Thread.sleep(300);
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
