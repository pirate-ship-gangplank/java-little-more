package digerking.ThreadAndConcurrency;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultipleCallableRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        List<CallableTask> tasks = List.of(new CallableTask("diger"), new CallableTask("song"), new CallableTask("dosong"));

        //submit() --> callable 호출
        List<Future<String>> invokeAll = executorService.invokeAll(tasks);

        invokeAll.stream().forEach(e -> System.out.println(e));

        for (Future<String> invoke : invokeAll) {
            System.out.println(invoke.get());
        }

        executorService.shutdown();
    }
}
