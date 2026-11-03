package pl.piomin.java.samples.virtualthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Virtual threads (JEP 444, Java 21) are lightweight threads scheduled by the JVM instead of the OS,
 * so one can be created per task, even for thousands of blocking tasks.
 */
public class ThreadComparison {

    public Thread oldThread(Runnable task) {
        return new Thread(task);
    }

    public Thread newVirtualThread(Runnable task) {
        return Thread.ofVirtual().unstarted(task);
    }

    public List<Integer> oldExecutor(int tasks) throws InterruptedException, ExecutionException {
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            return run(executor, tasks);
        }
    }

    public List<Integer> newVirtualExecutor(int tasks) throws InterruptedException, ExecutionException {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            return run(executor, tasks);
        }
    }

    private List<Integer> run(ExecutorService executor, int tasks) throws InterruptedException, ExecutionException {
        List<Callable<Integer>> callables = IntStream.range(0, tasks)
                .<Callable<Integer>>mapToObj(i -> () -> i)
                .toList();
        List<Integer> results = new ArrayList<>();
        for (Future<Integer> f : executor.invokeAll(callables)) {
            results.add(f.get());
        }
        return results;
    }
}
