package pl.piomin.java.samples.stringtemplates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTemplatesTests {

    @Test
    void shouldBePrinted() {
        String firstName = "Piotr";
        String lastName = "Minkowski";
        String greeting = "Greetings from \{firstName} \{lastName}";
        assertEquals("Greetings from Piotr Minkowski", greeting);
    }
}
