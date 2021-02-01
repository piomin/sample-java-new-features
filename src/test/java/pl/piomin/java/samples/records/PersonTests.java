package pl.piomin.java.samples.records;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTests {

    private static final String NAME = "John Smith";
    private static final int AGE = 50;

    @Test
    void shouldHaveGetters() {
        Person person = new Person(NAME, AGE);
        Assertions.assertEquals(NAME, person.name());
        Assertions.assertEquals(AGE, person.age());
    }

}
