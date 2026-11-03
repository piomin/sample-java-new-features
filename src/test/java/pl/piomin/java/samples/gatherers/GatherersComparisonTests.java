package pl.piomin.java.samples.gatherers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GatherersComparisonTests {

    private static final List<Integer> NUMBERS = List.of(1, 2, 3, 4, 5);

    private final GatherersComparison gatherers = new GatherersComparison();

    @Test
    void windowFixed() {
        List<List<Integer>> expected = List.of(List.of(1, 2), List.of(3, 4), List.of(5));
        Assertions.assertEquals(expected, gatherers.oldWindowFixed(NUMBERS, 2));
        Assertions.assertEquals(expected, gatherers.newWindowFixed(NUMBERS, 2));
    }

    @Test
    void windowSliding() {
        Assertions.assertEquals(List.of(List.of(1, 2, 3), List.of(2, 3, 4), List.of(3, 4, 5)),
                gatherers.newWindowSliding(NUMBERS, 3));
    }

    @Test
    void fold() {
        Assertions.assertEquals(List.of(15), gatherers.newFold(NUMBERS));
    }

    @Test
    void scan() {
        Assertions.assertEquals(List.of(1, 3, 6, 10, 15), gatherers.newScan(NUMBERS));
    }

    @Test
    void mapConcurrent() {
        Assertions.assertEquals(List.of(2, 4, 6, 8, 10), gatherers.newMapConcurrent(NUMBERS));
    }

    @Test
    void customGatherer() {
        Assertions.assertEquals(List.of(1, 2, 1, 3), gatherers.newCustomGatherer(List.of(1, 1, 2, 2, 2, 1, 3, 3)));
    }

}
