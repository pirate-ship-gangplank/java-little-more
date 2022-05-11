package digerking.ThreadAndConcurrency;

import java.util.concurrent.*;

class CallableTask implements Callable<String> {

    private String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);

        return "Hello " + name;
    }
}

public class CallableRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        
        //submit() --> callable 호출
        Future<String> welcomeFuture = executorService.submit(new CallableTask("diger"));

        String welcomeMessage = welcomeFuture.get();
        
        //////////////////////////////////////////////////////////
        // get() 메서드가 끝날 때 까지 스레드 일시정지 중

        System.out.println(welcomeMessage);

        System.out.println("\n Main Done");

    }
}
