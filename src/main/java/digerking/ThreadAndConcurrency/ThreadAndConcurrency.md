# Step 01. 스레드의 필요
> 병렬적으로 메서드를 실행할 수 있게 해주어 CPU 의 효율을 높일 수 있다.
---

# Step 02. 스레드 클래스 확장

### 스레드를 생성하는 방법은 아래와 같은 두가지 이다.
- extends Thread
- implements Runnable

> Extends Thread (Thread class) 를 통한 쓰레드 사용법

<br>

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

<br>

    Task1 task1 = new Task1();
    task1.start();

    // task1.run();
    // run() 을 실행하면 병렬 처리를 해주지 않음 -> 쓰레드 형식으로 실행시켜주지 않음

---
# Step 03. 스레드 인터페이스 구현

> Implements Runnable 를 통한 쓰레드 사용법

<br>

### Task2.class

    class Task2 implements Runnable {
        @Override
        public void run() {
            //Task2
            for (int i = 201; i <= 299; i++) {
                System.out.println(i + " ");
            }
    
            System.out.println("\n Task2 Done");
        }
    }    

<br>

### Main.class

    Task2 task2 = new Task2();
    Thread task2Thread = new Thread(task2);
    task2Thread.start();

---

> Implements Runnable 를 통한 쓰레드 사용법

<br>

### Task2.class

    class Task2 implements Runnable {
        @Override
        public void run() {
            //Task2
            for (int i = 201; i <= 299; i++) {
                System.out.println(i + " ");
            }
    
            System.out.println("\n Task2 Done");
        }
    }    

<br>

### Main.class

    Task2 task2 = new Task2();
    Thread task2Thread = new Thread(task2);
    task2Thread.start();

---

# Step 04. 스레드 상태

- NEW
- RUNNABLE
- RUNNING
- BLOCKED/WAITING
- TERMINATED/DEAD

<br>

### 상태 - NEW
> Start 메서드의 실행 전 Thread 의 상태이다.

### 상태 - RUNNABLE
> 스레드가 생성되었지만 아직 실행되지는 않은 상태이다. (다른 스레드가 우선적으로 실행되고 있음)

### 상태 - RUNNING
> 스레드 기능이 수행중인 상태이다.

### 상태 - BLOCKED/WAITING
> 외부 서비스의 응답을 기다리거나, 입력을 기다릴 때 등의 상황일때

### 상태 - TERMINATED/DEAD
> Start 이후 쓰레드의 기능을 모두 수행한 후의 상태이다.

---

# Step 05. 스레드 우선순위 부여

> 우선 순위는 반영될수도 안될 수도 있음

    // Max Priority = 10; //정의된 값
    // Min Priority = 1; //정의된 값

    //우선순위 부여 메서드 (setPriority)
    task1.setPriority(Thread.MIN_PRIORITY); 


---
# Step 06. 스레드 join (스레드 간 소통)

    //wait for task1 to complete

    task1.join();
    task2Thread.join();

위와 같이 join() 메서드로 해당 스레드가 끝날 때 까지 대기하는 메서드가 제공된다.

> 이때 클래스에 **throws InterruptedException** 로 인터럽트 예외처리를 해줘야 한다.

---

# Step 07. 스레드 활용 메서드, 동시성 키워드 (sleep, yield)

    //스레드 일시정지 (밀리초 단위)
    Thread.sleep(1000);

    // CPU 가 여러개일 때 특정 CPU 가 스레드를 처리하려 할 때 이를 양보한다 -> 특정 CPU 의 처리를 회피(양보)
    Thread.yield();

<br>

> 동기화 키워드를 사용하면, 단 한 가지 스레드만 특정 상황에 해당하는 코드를 실행


    public class SynchThread extends Thread {
    int total = 0;

        @Override
        public void run() {
            synchronized (this) { // 해당 객체(this)에 Lock 이 걸린다.
                for (int i = 0; i < 5; i++) {
                    System.out.println(i + "를 더한다.");
                    total += i;
    
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notify(); // 위 작업이 모두 끝나면 notify()를 호출하여 다른 쓰레드를 실행 대기 상태로 만든다.
            }
        }
    }

출처: https://link2me.tistory.com/1732 [소소한 일상 및 업무TIP 다루기]

---