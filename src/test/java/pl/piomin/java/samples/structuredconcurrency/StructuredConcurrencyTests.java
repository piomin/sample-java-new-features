package pl.piomin.java.samples.structuredconcurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StructuredConcurrencyTests {

    @Test
    void shouldFinish() throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<String> user  = scope.fork(this::findUser);
            Supplier<Integer> order = scope.fork(this::fetchOrder);

            scope.join().throwIfFailed();

            Response r = new Response(user.get(), order.get());
            assertNotNull(r.getUser());
            assertNotNull(r.getOrder());
        }
    }

    private String findUser() {
        return "user1";
    }

    private Integer fetchOrder() {
        return 100;
    }

    class Response {
        String user;
        Integer order;

        public Response(String user, Integer order) {
            this.user = user;
            this.order = order;
        }

        public String getUser() {
            return user;
        }

        public Integer getOrder() {
            return order;
        }
    }
}
