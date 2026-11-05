package pl.piomin.java.samples.virtualthreads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

public class ThreadComparisonTests {

    private static final int TASKS = 1000;

    private final ThreadComparison comparison = new ThreadComparison();

    @Test
    void shouldCreateThreads() {
        Assertions.assertFalse(comparison.oldThread(() -> {}).isVirtual());
        Assertions.assertTrue(comparison.newVirtualThread(() -> {}).isVirtual());
    }

    @Test
    void shouldRunInVirtualThread() throws InterruptedException {
        boolean[] virtual = new boolean[1];
        Thread t = comparison.newVirtualThread(() -> virtual[0] = Thread.currentThread().isVirtual());
        t.start();
        t.join();
        Assertions.assertTrue(virtual[0]);
    }

    @Test
    void shouldRunTasksOnExecutors() throws ExecutionException, InterruptedException {
        Assertions.assertEquals(TASKS, comparison.oldExecutor(TASKS).size());
        Assertions.assertEquals(TASKS, comparison.newVirtualExecutor(TASKS).size());
    }

}
