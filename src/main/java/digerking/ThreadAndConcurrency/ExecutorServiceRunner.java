package digerking.ThreadAndConcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceRunner {
    public static void main(String[] args) {

        // Step 09
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        ExecutorService executorService2 = Executors.newFixedThreadPool(2);

//        executorService1.execute(new Task1());
//        executorService1.execute(new Thread(new Task2()));

//        executorService2.execute(new Task1());
//        executorService2.execute(new Thread(new Task2()));

        executorService2.execute(new Task(1));
        executorService2.execute(new Task(2));
        executorService2.execute(new Task(3));
        executorService2.execute(new Task(4));
        executorService2.execute(new Task(5));
        executorService2.execute(new Task(6));

        //Task3
        for (int i = 301; i <= 399; i++) {
            System.out.println(i + " ");
        }

        System.out.println("\n Task3 Done");

        // step 09. executor 소개
        // shutdown 을 하지 않으면 쓰레드가 계속 활성화 되어있음
        executorService1.shutdown();


    }
}
