package digerking.ThreadAndConcurrency;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleAnyCallableRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        List<CallableTask> tasks = List.of(new CallableTask("diger"), new CallableTask("song"), new CallableTask("dosong"));

        //invokeAny -> 가장 빨리 끝난 스레드를 반환
        String invokeAny = executorService.invokeAny(tasks);
        
        System.out.println(invokeAny);

        executorService.shutdown();
    }
}
