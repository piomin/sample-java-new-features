package pl.piomin.java.samples.smallapi;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CollectorsStreamsTests {

    record MinMax(int min, int max) {}

    @Test
    void teeing() {
        MinMax result = Stream.of(5, 3, 9, 1)
                .collect(Collectors.teeing(
                        Collectors.minBy(Integer::compare),
                        Collectors.maxBy(Integer::compare),
                        (min, max) -> new MinMax(min.orElseThrow(), max.orElseThrow())));
        assertEquals(new MinMax(1, 9), result);
    }

    @Test
    void mapMulti() {
        List<Integer> result = Stream.of(1, 2, 3)
                .<Integer>mapMulti((i, consumer) -> {
                    for (int j = 0; j < i; j++) {
                        consumer.accept(i);
                    }
                })
                .toList();
        assertEquals(List.of(1, 2, 2, 3, 3, 3), result);
    }

    @Test
    void toListIsUnmodifiable() {
        List<String> list = Stream.of("a", "b").toList();
        assertEquals(2, list.size());
        assertThrows(UnsupportedOperationException.class, () -> list.add("c"));
    }
}
