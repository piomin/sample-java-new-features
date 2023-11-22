package pl.piomin.java.samples.stringtemplates;

import org.junit.jupiter.api.Test;

import static java.lang.StringTemplate.STR;

public class StringTemplatesTests {

    @Test
    void shouldBePrinted() {
        String firstName = "Piotr";
        String lastName = "Minkowski";
        String greetings = STR."Greetings from \{firstName} \{lastName}";
        assert greetings.equals("Greetings from Piotr Minkowski");
    }
}
