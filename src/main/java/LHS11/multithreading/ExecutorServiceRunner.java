package LHS11.multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Task extends Thread{

    private int number;

    public Task(int number) {
        this.number = number;
    }

    public void run(){ //SIGNATURE
        System.out.print("\n Task "+number+" Started");

        for(int i=number*100;i<=number*100+99;i++){
            System.out.print(i+" ");
        }

        System.out.print("\n Task "+number+" Done");
    }
}

public class ExecutorServiceRunner {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); // -> 한번에 하나의 쓰레드 실행하도록 하는 기능 제공
        ExecutorService executorService = Executors.newFixedThreadPool(3); // -> 사용되는 쓰레드의 개수가 n개
        executorService.execute(new Task(1));
        executorService.execute(new Task(2));
        executorService.execute(new Task(3));
        executorService.execute(new Task(4));

//        executorService.execute(new Thread(new Task2()));

//        System.out.print("\n Task3 Kicked Off");
//
//        //Task3
//        for(int i=301;i<=399;i++){
//            System.out.print(i+" ");
//        }
//        System.out.print("\n Task3 Done");
//
//        System.out.print("\n Main Done");
//
        executorService.shutdown();
    }
}
