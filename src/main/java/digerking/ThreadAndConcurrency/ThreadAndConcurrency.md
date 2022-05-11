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

### Task2.java

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

### Main.java

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

# Step 08. 스레드 제어의 필요성

> 어느 시점에서 얼마나 많은 Thread 가 실행되고 있는지 알 수 없는 등 활용하기에 문제점이 있어, 스레드 흐름 처리가 필요함

---

# Step 09. Executor 서비스 소개

### ExecutorServiceRunnser.java

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    executorService.execute(new Task1());
    executorService.execute(new Thread(new Task2()));


    

위 코드를 실행시키면 Task1이 완전히 끝난 후 Task2가 실행된다.

executorService 가 Executors 에 의해 
싱글 스레드로 선언되었기 때문에 한 쓰레드만 동작하도록 서비스 해주는 것이다.

    //Task3
    for (int i = 301; i <= 399; i++) {
        System.out.println(i + " ");
    }

위의 코드를 하단에 추가하면, Task1과 함께 같이 돌아가는 것을 확인할 수 있다.

Executors 에 의해 실행되는 메서드가 아닌 것은 Executors 에 의한 제어에 영향받지 않는다.

---

# Step 10. Executor 서비스 - 사용자 정의 스레드 수

### ExecutorServiceRunnser.java

    ExecutorService executorService2 = Executors.newFixedThreadPool(2);

newFixedThreadPool 이라는 메서드를 이용하여 스레드 갯수를 지정할 수 있다.

<br>

### Main.java


    class Task extends Thread {

        private int number;
    
        public Task(int number) {
            this.number = number;
        }
    
        public void run() {
    
            System.out.println("\n Task" + number +  "Started");
    
            //Task1
            for (int i = number * 100; i <= number * 100 + 99; i++) {
                System.out.println(i + " ");
            }
            System.out.println("\n Task" + number +  "Done");
        }
    }

위와 같은 코드를 통해 작업을 여러개 생성할 수 있는 클래스를 만든 후

<br>

### ExecutorServiceRunner.java

    ExecutorService executorService2 = Executors.newFixedThreadPool(2);

    executorService2.execute(new Task(1));
    executorService2.execute(new Task(2));
    executorService2.execute(new Task(3));
    executorService2.execute(new Task(4));
    executorService2.execute(new Task(5));
    executorService2.execute(new Task(6));

아래와 같이 실행시켜서 출력창을 확인 해 보면

두가지 작업이 동시에 처리되고, 두가지 중 한가지의 처리가 끝나면 새로운 작업을 실행시키는 것을 볼 수 있다.


---

# Step 11. Callable 로 스레드에게 리턴 값 받기

### CallableRunner.java

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

Callable 인터페이스의 구현체인 CallableTask 를 만들어 문자열을 반환하는 메서드를 만든다.

    public class CallableRunner {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            
            ExecutorService executorService = Executors.newFixedThreadPool(1);
    
    
            //submit() --> callable 호출
            Future<String> welcomeFuture = executorService.submit(new CallableTask("diger"));
    
            String welcomeMessage = welcomeFuture.get();
    
            System.out.println(welcomeMessage);
    
            System.out.println("\n Main Done");
    }

<br>

> submit() : callable 객체 호출 [!] 반환 타입 = Future
> 
> get() : callable 객체 값 꺼내오기

---

# Step 12. invoke 를 사용한 다중 처리 코드 쓰기

### MultipleCallableRunner.java

    public class MultipleCallableRunner {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
    
            List<CallableTask> tasks = List.of(new CallableTask("diger"), new CallableTask("song"), new CallableTask("dosong"));
    
            List<Future<String>> invokeAll = executorService.invokeAll(tasks);
    
            invokeAll.stream().forEach(e -> System.out.println(e));
    
            for (Future<String> invoke : invokeAll) {
                System.out.println(invoke.get());
            }
    
            executorService.shutdown();
        }
    }

<br>

여러개의 작업을 생성하는 코드
> List<CallableTask> tasks = List.of(new CallableTask("diger"), new CallableTask("song"), new CallableTask("dosong"));

여러개의 작업을 리스트에 담는 코드
> List<Future<String>> invokeAll = executorService.invokeAll(tasks);

리스트에 담긴 객체를 출력하는 코드
> invokeAll.stream().forEach(e -> System.out.println(e));
    
리스트에 담긴 실제 내용을 출력하는 코드
> for (Future<String> invoke : invokeAll) { System.out.println(invoke.get()); }

---

# Step 12. invoke 를 사용한 다중 처리 코드 쓰기

### MultipleCallableRunner.java

    public class MultipleCallableRunner {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
    
            List<CallableTask> tasks = List.of(new CallableTask("diger"), new CallableTask("song"), new CallableTask("dosong"));
    
            List<Future<String>> invokeAll = executorService.invokeAll(tasks);
    
            invokeAll.stream().forEach(e -> System.out.println(e));
    
            for (Future<String> invoke : invokeAll) {
                System.out.println(invoke.get());
            }
    
            executorService.shutdown();
        }
    }

<br>

여러개의 작업을 생성하는 코드
> List<CallableTask> tasks = List.of(new CallableTask("diger"), new CallableTask("song"), new CallableTask("dosong"));

여러개의 작업을 리스트에 담는 코드
> List<Future<String>> invokeAll = executorService.invokeAll(tasks);

리스트에 담긴 객체를 출력하는 코드
> invokeAll.stream().forEach(e -> System.out.println(e));

리스트에 담긴 실제 내용을 출력하는 코드
> for (Future<String> invoke : invokeAll) { System.out.println(invoke.get()); }

---

# Step 13. invokeAny 로 가장 일찍 끝난 스레드 반환받기

### MultipleAnyCallableRunner.java

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        List<CallableTask> tasks = List.of(new CallableTask("diger"), new CallableTask("song"), new CallableTask("dosong"));

        //invokeAny -> 가장 빨리 끝난 스레드를 반환
        String invokeAny = executorService.invokeAny(tasks);
        
        System.out.println(invokeAny);

        executorService.shutdown();
    }

---

# Step 14. 스레드, 다중 스레드 결론

> Thread 생성 방법 -> Thread 상속, Runnable 인터페이스 구현

> setPriority() -> 스레드 우선순위 부여

> Start() -> 스레드 시작

> join() -> 다른 스레드 대기(스레드 간 소통)

> Executors -> newFixedThreadPool(int num)

> Executors -> newSingleThreadPool()

> ExecutorService - submit() //반환 타입은 Future

> ExecutorService - get()

> ExecutorService - invokeAll() //반환 타입은 List<Future<>>

> ExecutorService - invokeAny() //제일 빨리 끝난 스레드
