package pl.piomin.java.samples.records;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordPatternsTests {

    @Test
    void shouldMatchSimpleRecordPattern() {
        Assertions.assertEquals("John (50)", RecordPatterns.describePerson(new Person("John", 50)));
        Assertions.assertEquals("unknown", RecordPatterns.describePerson("John"));
    }

    @Test
    void shouldMatchNestedRecordPattern() {
        var p = new PersonWithAddress("John", new Address("Krakow", "Main"));
        Assertions.assertEquals("John lives in Krakow", RecordPatterns.cityOf(p));
        Assertions.assertEquals("unknown", RecordPatterns.cityOf(new Person("John", 50)));
    }

    @Test
    void shouldDeconstructInSwitch() {
        Assertions.assertEquals("John lives in the capital",
                RecordPatterns.describe(new PersonWithAddress("John", new Address("Warsaw", "Main"))));
        Assertions.assertEquals("John lives in Krakow, Main",
                RecordPatterns.describe(new PersonWithAddress("John", new Address("Krakow", "Main"))));
        Assertions.assertEquals("Ann is an adult", RecordPatterns.describe(new Person("Ann", 30)));
        Assertions.assertEquals("Tim is a minor", RecordPatterns.describe(new Person("Tim", 10)));
        Assertions.assertEquals("null", RecordPatterns.describe(null));
        Assertions.assertEquals("unknown", RecordPatterns.describe(1));
    }

}
