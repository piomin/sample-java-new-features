package pl.piomin.java.samples.unnamed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Unnamed variables and patterns (JEP 456, final in Java 22): {@code _} marks a variable
 * or pattern that is required by syntax but never used.
 */
public class UnnamedVariables {

    public record Point(int x, int y) {}

    public sealed interface Shape permits Circle, Rectangle, Square {}
    public record Circle(Point center, int radius) implements Shape {}
    public record Rectangle(Point topLeft, Point bottomRight) implements Shape {}
    public record Square(Point topLeft, int side) implements Shape {}

    public static class Resource implements AutoCloseable {
        private boolean closed;

        public boolean isClosed() {
            return closed;
        }

        @Override
        public void close() {
            closed = true;
        }
    }

    // unnamed variable in catch block
    public int parseOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException _) {
            return defaultValue;
        }
    }

    // unnamed variable in enhanced for loop
    public int count(Iterable<?> items) {
        int total = 0;
        for (var _ : items) {
            total++;
        }
        return total;
    }

    // unnamed lambda parameter
    public Map<String, Integer> initCounters(List<String> keys) {
        Map<String, Integer> map = new HashMap<>();
        keys.forEach(k -> map.computeIfAbsent(k, _ -> 0));
        return map;
    }

    // unnamed variable in try-with-resources
    public boolean useResource(Supplier<Resource> supplier) {
        try (var _ = supplier.get()) {
            return true;
        }
    }

    // unnamed local variable
    public int firstOrZero(List<Integer> list) {
        var _ = list.size();
        return list.isEmpty() ? 0 : list.getFirst();
    }

    // unnamed patterns in record patterns
    public int xOfTopLeft(Shape shape) {
        return switch (shape) {
            case Circle(Point(var x, _), _) -> x;
            case Rectangle(Point(var x, _), _) -> x;
            case Square(Point(var x, _), _) -> x;
        };
    }

    // multiple patterns in one case label, possible thanks to unnamed variables
    public String describe(Shape shape) {
        return switch (shape) {
            case Rectangle _, Square _ -> "polygon";
            case Circle _ -> "round";
        };
    }

    public boolean isCircle(Object o) {
        return o instanceof Circle(_, _);
    }
}
