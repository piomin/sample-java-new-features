package pl.piomin.java.samples.delay;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pl.piomin.java.samples.helper.DelayedEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.logging.Logger;

public class DelayTest {

    Logger log = Logger.getLogger("DelayTest");

    @Test
    void delay() throws InterruptedException {
        final DelayQueue<DelayedEvent> delayQueue = new DelayQueue<>();
        final long timeFirst = System.currentTimeMillis() + 10000;
        delayQueue.offer(new DelayedEvent(timeFirst, "1"));
        log.info("Done");
        log.info(delayQueue.take().msg());
    }

    @Test
    void formatter() {
        String s = DateTimeFormatter.ofPattern("B").format(LocalDateTime.now());
        System.out.println(s);
    }
}
