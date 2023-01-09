package pl.piomin.java.samples.spi;

public class HiGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Hi";
    }
}
