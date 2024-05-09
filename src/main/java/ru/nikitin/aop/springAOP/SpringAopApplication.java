package ru.nikitin.aop.springAOP;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import ru.nikitin.aop.springAOP.testdata.TestService;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching
@Slf4j
public class SpringAopApplication {

    @Autowired
    TestService service;

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void success() {
        log.info("onSuccess");
        service.service(new TestService());
        service.service();
        service.service(34);
        try {
            Thread.sleep(500);
            service.asyncService();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("complete".concat("\n"));
    }

}
