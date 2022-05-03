package LHS11.multithreading;

//extends Thread
//implements Runnable

class Task1 extends Thread{
    public void run(){ //SIGNATURE

        System.out.print("\n Task1 Started");

        for(int i=101;i<=199;i++){
            System.out.print(i+" ");
        }

        System.out.print("\n Task1 Done");
    }
}

class Task2 implements Runnable{

    @Override
    public void run() {
        System.out.print("\n Task2 Started");

        for(int i=201;i<=299;i++){
            System.out.print(i+" ");
        }

        System.out.print("\n Task2 Done");
    }
}

public class ThreadBasicRunner {
    public static void main(String[] args) {

        // New -> start 메소드 실행 전의 Thread
        // Runnable -> 실행하려고 하지만 이미 다른 thread가 우선적으로 실행되고 있는 상태
        // Running -> 특정 지점의 상태를 Running
        // blocked/waiting
        // -> 외부 서비스의 응답을 기다리고 있거나 데이터베이스를 이용하고 있는데, 데이터 베이스가 느리다면 사용자는 차단당함
        // -> 어떤 입력을 위해 대기하고 있거나 실행완료 되지 않은 다른 쓰레드로부터 데이터 입력을 받아야함
        // terminated/dead -> 실행이 모두 끝난 상태


        //setPriority -> 구체적인 task에 대해서 우선순위를 정함, 무조건적인건 아님 (추천정도?)
        //join 메소드 -> join한 쓰레드가 다 끝난 후에야 다음 코드로 넘어감
        // sleep 메소드 -> (밀리초) // 1000 = 1초
        // yield -> 스케줄러에 대한 힌트, 현재의 thread가 현재 이용 가능한 상태로 양보하거나 양도
        //synchronized 가 붙으면 단 한개의 thread에 대해서만 실행이 가능
        //excutor service -> 모든 기능 제공

        //Task1
        System.out.print("\n Task1 Kicked Off");
        Task1 task1 = new Task1();
//        task1.setPriority(1);
        task1.start(); // thread로 사용하고 싶으면 start() 메소드 이용

        System.out.print("\n Task2 Kicked Off");


        //Task2
        Task2 task2 = new Task2();
        Thread thread = new Thread(task2);
//        thread.setPriority(10);
        thread.start();

        try {
            task1.join();
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.print("\n Task3 Kicked Off");

        //Task3
        for(int i=301;i<=399;i++){
            System.out.print(i+" ");
        }
        System.out.print("\n Task3 Done");

        System.out.print("\n Main Done");

    }
    //쓰레드를 사용하지 않으면 수행문이 차례대로 기다려함
    //Threads : 유사성을 가진 모든 수행문을 동시에 실행하도록 해줌, 병렬적으로 처리가능 (놀거나 비는 시간 없이 지속해서 코드를 진행)
}
