package LHS11.multithreading;


import java.util.concurrent.*;

class CallableTask implements Callable<String>{

    private String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return " Hello " + name;
    }
}
public class CallableRunner {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> welcomeFuture = executorService.submit(new CallableTask("in28minutes"));
        //Future -> 결과가 나올 것이라고 예측

        System.out.println("\n new CallableTask(\"in28Minutes\") executed");

        String welcomeMessage = welcomeFuture.get(); // get() -> 실행이 완료될 때까지 기다림 , 그 다음에 다음 줄의 코드가 실행

        System.out.print(welcomeMessage);

        System.out.print("\n Main completed");

    }
}
