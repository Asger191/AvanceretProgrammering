package concurrency.executors;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Result from task";
        };

        Future<String> future = executor.submit(task);

        System.out.println("Doing something else while the task runs...");

        String result = future.get(); // Blokerer til opgaven er færdig
        System.out.println("Got result: " + result);

        executor.shutdown();
    }
}
