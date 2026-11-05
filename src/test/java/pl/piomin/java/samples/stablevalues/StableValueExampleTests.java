package pl.piomin.java.samples.stablevalues;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class StableValueExampleTests {

    @Test
    void shouldInitializeOnlyOnce() {
        StableValueExample example = new StableValueExample();
        Assertions.assertFalse(example.stableValue().isSet());
        String first = example.getStable();
        Assertions.assertEquals(first, example.getStable());
        Assertions.assertTrue(example.stableValue().isSet());
        Assertions.assertEquals(1, example.invocations());
    }

    @Test
    void shouldNotOverrideSetValue() {
        StableValue<String> value = StableValue.of();
        Assertions.assertTrue(value.trySet("a"));
        Assertions.assertFalse(value.trySet("b"));
        Assertions.assertEquals("a", value.orElseThrow());
        Assertions.assertThrows(IllegalStateException.class, () -> value.setOrThrow("c"));
    }

    @Test
    void shouldReturnFallbackWhenNotSet() {
        StableValue<String> value = StableValue.of();
        Assertions.assertEquals("none", value.orElse("none"));
        Assertions.assertThrows(NoSuchElementException.class, value::orElseThrow);
    }

    @Test
    void shouldComputeSupplierOnce() {
        StableValueExample example = new StableValueExample();
        Assertions.assertEquals(example.getFromSupplier(), example.getFromSupplier());
        Assertions.assertEquals(1, example.invocations());
    }

    @Test
    void shouldComputeFunctionOncePerKey() {
        StableValueExample example = new StableValueExample();
        String one = example.getFromFunction(1);
        Assertions.assertEquals(one, example.getFromFunction(1));
        example.getFromFunction(2);
        Assertions.assertEquals(2, example.invocations());
        Assertions.assertThrows(IllegalArgumentException.class, () -> example.getFromFunction(5));
    }

    @Test
    void shouldComputeListAndMapElementsLazily() {
        StableValueExample example = new StableValueExample();
        Assertions.assertEquals(0, example.invocations());
        Assertions.assertEquals(example.getFromList(1), example.getFromList(1));
        Assertions.assertEquals(1, example.invocations());
        Assertions.assertEquals(2, example.getFromMap("bb"));
        Assertions.assertEquals(2, example.getFromMap("bb"));
        Assertions.assertEquals(2, example.invocations());
    }

    @Test
    void shouldInitializeOnceConcurrently() {
        StableValueExample example = new StableValueExample();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 100).forEach(i -> executor.submit(example::getStable));
        }
        Assertions.assertEquals(1, example.invocations());
    }

    @Test
    void shouldMatchDoubleCheckedLockingAndHolder() {
        StableValueExample example = new StableValueExample();
        Assertions.assertEquals(example.getDoubleChecked(), example.getDoubleChecked());
        Assertions.assertEquals(1, example.invocations());
        Assertions.assertSame(StableValueExample.getHolder(), StableValueExample.getHolder());
    }
}
