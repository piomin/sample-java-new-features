package pl.piomin.java.samples.stablevalues;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Stable Values (JEP 502, preview in Java 25): lazy, once-only initialization of values that the JVM
 * can treat as constants. Compared with the lazy holder idiom and double-checked locking.
 */
public class StableValueExample {

    private final AtomicInteger counter = new AtomicInteger();

    // StableValue: lazily set once, thread-safe, no explicit locking
    private final StableValue<String> stable = StableValue.of();

    // stable supplier: computed on first get()
    private final Supplier<String> stableSupplier = StableValue.supplier(this::expensive);

    // stable function: computed once per allowed key
    private final Function<Integer, String> stableFunction =
            StableValue.function(Set.of(1, 2, 3), i -> expensive() + "-" + i);

    // stable list and map: elements computed lazily
    private final List<String> stableList = StableValue.list(3, i -> expensive() + "-" + i);
    private final Map<String, Integer> stableMap =
            StableValue.map(Set.of("a", "bb", "ccc"), s -> {
                counter.incrementAndGet();
                return s.length();
            });

    // double-checked locking
    private volatile String dcl;

    // lazy holder idiom needs a separate class and is static only
    private static class Holder {
        static final String VALUE = "holder";
    }

    private String expensive() {
        return "value-" + counter.incrementAndGet();
    }

    public String getStable() {
        return stable.orElseSet(this::expensive);
    }

    public StableValue<String> stableValue() {
        return stable;
    }

    public String getFromSupplier() {
        return stableSupplier.get();
    }

    public String getFromFunction(int key) {
        return stableFunction.apply(key);
    }

    public String getFromList(int index) {
        return stableList.get(index);
    }

    public int getFromMap(String key) {
        return stableMap.get(key);
    }

    public String getDoubleChecked() {
        String result = dcl;
        if (result == null) {
            synchronized (this) {
                result = dcl;
                if (result == null) {
                    dcl = result = expensive();
                }
            }
        }
        return result;
    }

    public static String getHolder() {
        return Holder.VALUE;
    }

    public int invocations() {
        return counter.get();
    }
}
