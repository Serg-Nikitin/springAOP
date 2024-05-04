package ru.nikitin.aop.springAOP.testdata;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nikitin.aop.springAOP.annotations.TrackTime;

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

}
