package digerking.ThreadAndConcurrency;

class Task1 extends Thread {
    public void run() {

        System.out.println("\n Task1 Started");

        //Task1
        for (int i = 101; i <= 199; i++) {
            System.out.println(i + " ");
        }
        System.out.println("\n Task1 Done");
    }
}

class Task2 implements Runnable {

    @Override
    public void run() {

        System.out.println("\n Task2 Started");

        //Task2
        for (int i = 201; i <= 299; i++) {
            System.out.println(i + " ");
        }

        System.out.println("\n Task2 Done");
    }
}


public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Step 02. 쓰레드 생성
        Task1 task1 = new Task1();

        // Step 05. 멀티스레드 (우선순위생성)
        // 우선 순위는 반영될수도 안될 수도 있음
        // Max Priority = 10;
        // Min Priority = 1;
        task1.setPriority(Thread.MIN_PRIORITY);
        task1.start();

        // run() 을 실행하면 병렬 처리를 해주지 않음 -> 쓰레드 형식으로 실행시켜주지 않음
        // task1.run();

        
        //Step 03. 인터페이스를 통한 쓰레드 생성
        Task2 task2 = new Task2();
        Thread task2Thread = new Thread(task2);
        task2Thread.setPriority(Thread.MAX_PRIORITY);
        task2Thread.start();


        //Step 07. 스레드 활용 메서드, 동시성 키워드
        //스레드 일시정지 (밀리초 단위)
        Thread.sleep(1000);

        // CPU 가 여러개일 때 특정 CPU 가 스레드를 처리하려 할 때 이를 양보한다 -> 특정 CPU 의 처리를 회피(양보)
        Thread.yield();


        //wait for task1 to complete
        task1.join();
        task2Thread.join();


        //Task3
        for (int i = 301; i <= 399; i++) {
            System.out.println(i + " ");
        }

        System.out.println("\n Task3 Done");
    }
}
