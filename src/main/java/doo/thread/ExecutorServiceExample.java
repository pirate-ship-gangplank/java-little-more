package doo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Thread thread1 = new Thread();
    Thread thread2 = new Thread();
    executorService.execute(thread1);
    executorService.execute(thread2);

    ExecutorService executorService1 = Executors.newFixedThreadPool(2);
    Future<String> future = executorService.submit(new CallableTask());
    String result = future.get();

    System.out.println("job finish");
  }
}

class CallableTask implements Callable<String> {

  @Override
  public String call() throws Exception {
    Thread.sleep(3000);
    return "hello";
  }
}