package pl.piomin.java.samples.structuredconcurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StructuredConcurrencyTests {

    @Test
    void shouldFinish() throws InterruptedException, ExecutionException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<Object>allSuccessfulOrThrow())) {
            Supplier<String> user  = scope.fork(this::findUser);
            Supplier<Integer> order = scope.fork(this::fetchOrder);

            scope.join();

            Response r = new Response(user.get(), order.get());
            assertNotNull(r.user());
            assertNotNull(r.order());
        }
    }

    @Test
    void shouldReturnFirstSuccessful() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>anySuccessfulResultOrThrow())) {
            scope.fork(() -> { throw new IllegalStateException("failed"); });
            scope.fork(this::findUser);

            assertEquals("user1", scope.join());
        }
    }

    @Test
    void shouldAwaitAll() throws InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<Object>awaitAll())) {
            Supplier<String> user = scope.fork(this::findUser);
            Supplier<Integer> order = scope.fork(this::fetchOrder);

            scope.join();

            assertEquals("user1", user.get());
            assertEquals(100, order.get());
        }
    }

    private String findUser() {
        return "user1";
    }

    private Integer fetchOrder() {
        return 100;
    }

    record Response(String user, Integer order) {}
}
