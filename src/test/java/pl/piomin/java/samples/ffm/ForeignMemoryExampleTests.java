package pl.piomin.java.samples.ffm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class ForeignMemoryExampleTests {

    private final ForeignMemoryExample example = new ForeignMemoryExample();

    @Test
    void shouldCallStrlen() throws Throwable {
        Assertions.assertEquals(5, example.strlen("Hello"));
        Assertions.assertEquals(0, example.strlen(""));
    }

    @Test
    void shouldSumNativeInts() {
        Assertions.assertEquals(10, example.sumInts(1, 2, 3, 4));
    }

    @Test
    void shouldAllocateSegment() {
        Assertions.assertEquals(40, example.allocatedSize(10));
    }

    @Test
    void shouldFailAccessAfterArenaClosed() {
        MemorySegment segment;
        try (Arena arena = Arena.ofConfined()) {
            segment = arena.allocate(ValueLayout.JAVA_INT);
            segment.set(ValueLayout.JAVA_INT, 0, 42);
            Assertions.assertEquals(42, segment.get(ValueLayout.JAVA_INT, 0));
        }
        Assertions.assertThrows(IllegalStateException.class, () -> segment.get(ValueLayout.JAVA_INT, 0));
    }
}
