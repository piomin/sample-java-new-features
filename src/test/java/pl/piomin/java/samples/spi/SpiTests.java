package pl.piomin.java.samples.spi;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

public class SpiTests {

    @Test
    void greeting() {
        ServiceLoader<GreetingService> sl = ServiceLoader.load(GreetingService.class);
        sl.iterator().forEachRemaining(gs -> System.out.println(gs.greet()));
    }

//    @Test
    void shutdownHook() {
        Thread shutdownThread = new Thread(() -> System.out.println("Hook"));
        shutdownThread.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(shutdownThread);
        System.exit(0);
    }

}
