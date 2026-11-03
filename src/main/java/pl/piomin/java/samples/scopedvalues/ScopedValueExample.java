package pl.piomin.java.samples.scopedvalues;

import java.util.concurrent.StructuredTaskScope;

/**
 * Scoped Values (JEP 506, final in Java 25): immutable and bound only for the duration of a call,
 * unlike a mutable {@link ThreadLocal}. They are inherited by forks of {@link StructuredTaskScope}.
 */
public class ScopedValueExample {

    public static final ScopedValue<String> USER = ScopedValue.newInstance();

    public String getUser() {
        return USER.get();
    }

    public String getUserInFork() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>anySuccessfulResultOrThrow())) {
            scope.fork(() -> USER.get());
            return scope.join();
        }
    }
}
