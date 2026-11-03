package pl.piomin.java.samples.gatherers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * Stream Gatherers (JEP 485, Java 24): custom intermediate operations. Before, the stream API had a fixed set
 * of intermediate operations, and anything stateful (windows, running totals) required a loop or a custom collector.
 */
public class GatherersComparison {

    public List<List<Integer>> oldWindowFixed(List<Integer> numbers, int size) {
        List<List<Integer>> windows = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i += size) {
            windows.add(numbers.subList(i, Math.min(i + size, numbers.size())));
        }
        return windows;
    }

    public List<List<Integer>> newWindowFixed(List<Integer> numbers, int size) {
        return numbers.stream().gather(Gatherers.windowFixed(size)).toList();
    }

    public List<List<Integer>> newWindowSliding(List<Integer> numbers, int size) {
        return numbers.stream().gather(Gatherers.windowSliding(size)).toList();
    }

    public List<Integer> newFold(List<Integer> numbers) {
        return numbers.stream().gather(Gatherers.fold(() -> 0, Integer::sum)).toList();
    }

    public List<Integer> newScan(List<Integer> numbers) {
        return numbers.stream().gather(Gatherers.scan(() -> 0, Integer::sum)).toList();
    }

    public List<Integer> newMapConcurrent(List<Integer> numbers) {
        return numbers.stream().gather(Gatherers.mapConcurrent(4, n -> n * 2)).toList();
    }

    public List<Integer> newCustomGatherer(List<Integer> numbers) {
        return numbers.stream().gather(distinctConsecutive()).toList();
    }

    private static <T> Gatherer<T, ?, T> distinctConsecutive() {
        return Gatherer.ofSequential(
                () -> new ArrayList<T>(1),
                (last, element, downstream) -> {
                    if (!last.isEmpty() && last.getFirst().equals(element)) {
                        return true;
                    }
                    last.clear();
                    last.add(element);
                    return downstream.push(element);
                });
    }
}
