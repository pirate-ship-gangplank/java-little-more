package doo.thread;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiAnyCallableExample {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(1);

    List<CallableTask> tasks = List.of(new CallableTask(), new CallableTask(), new CallableTask());

    System.err.println("start");
    String result = executorService.invokeAny(tasks);

    executorService.shutdown();
  }
}
