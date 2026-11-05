package pl.piomin.java.samples.smallapi;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DurationTests {

    @Test
    void toParts() {
        Duration d = Duration.ofSeconds(2 * 86400 + 3 * 3600 + 4 * 60 + 5).plusMillis(678);
        assertEquals(2, d.toDaysPart());
        assertEquals(3, d.toHoursPart());
        assertEquals(4, d.toMinutesPart());
        assertEquals(5, d.toSecondsPart());
        assertEquals(678, d.toMillisPart());
        assertEquals(678_000_000, d.toNanosPart());
        assertEquals(51, d.toHours());
    }
}
