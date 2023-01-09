package pl.piomin.java.samples.spi;

public class HelloGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Hello";
    }
}
