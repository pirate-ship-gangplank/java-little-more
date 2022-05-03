Thread의 필요성 => 쓰레드를 사용하지 않으면 수행문이 차례대로 기다려함

Thread 낭비를 줄이기 위해 MultiThread가 필요!
- Threads : 유사성을 가진 모든 수행문을 동시에 실행하도록 해줌, 병렬적으로 처리가능 (놀거나 비는 시간 없이 지속해서 코드를 진행)

### 1. Thread를 생성하는 2가지 방법
>
```
class Task1 extends Thread{
    public void run(){ //SIGNATURE
        System.out.print("\n Task1 Started");
        for(int i=101;i<=199;i++){
            System.out.print(i+" ");
        }
        System.out.print("\n Task1 Done");
    }
}
```
```
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
```
- Thread class 상속
- Runnable interface 구현

### 2. Thread가 가질 수 있는 다양한 상태
>
- New : start 메소드 실행 전 상태의 Thread
- Runnable : 실행하려고 하지만 이미 다른 Thread가 실행되고 있는 상태
- Running : 특정 지점의 상태를 실행
- Blocked/Waiting : 데이터베이스를 이용하고 있는데, 데이터베이스가 느려 차단당함 / 입력을 위해 대기하고 있거나 실행완료 되지 않은 쓰레드로부터 데이터 입력을 받아야함
- Terminated/dead : 실행이 모두 끝난 상태

### 3. Thread에 대해 각각 다른 우선순위를 부여
>
- Thread 클래스의 setPriority 메소드
- 구체적인 task에 대해서 우선순위를 정함
- 무조건적 X (추천)
- MAX_PRIORITY : 10, MIN_PRIORITY = 1 (높을 수록 우선)
```
//Task1
        System.out.print("\n Task1 Kicked Off");
        Task1 task1 = new Task1();
        task1.setPriority(1);
        task1.start(); // thread로 사용하고 싶으면 start() 메소드 이용
        System.out.print("\n Task2 Kicked Off");
//Task2
        Task2 task2 = new Task2();
        Thread thread = new Thread(task2);
        thread.setPriority(10);
        thread.start();
```

### 4. Thread사이의 소통
>
- Thread 클래스의 join 메소드 : 해당 Thread가 다 끝난 후에야 다음 코드로 넘어감
```
try {
            task1.join();
            thread.join(); // task2
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("\n Task3 Kicked Off");4
//Task3
        for(int i=301;i<=399;i++){
            System.out.print(i+" ");
        }
        System.out.print("\n Task3 Done");
        System.out.print("\n Main Done");
```
=> task1,task2를 모두 실행한 후에 task3가 실행됨

### 5. ExecutorService에서 몇 개의 쓰레드를 동시 사용하여 task를 진행
```
ExecutorService executorService = Executors.newSingleThreadExecutor()
```
> - 한번에 하나의 Thread 실행

```
ExecutorService executorService = Executors.newFixedThreadPool(n);
```
>- 사용되는 Thread의 개수가 n개

### 6. 쓰레드에서 결과가 얻어지는 지점 설정 가능
```
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

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> welcomeFuture = executorService.submit(new CallableTask("in28minutes"));

        System.out.println("\n new CallableTask(\"in28Minutes\") executed");

        String welcomeMessage = welcomeFuture.get(); // 이 코드가 실행될 때까지 기다렸다가 실행하고 다음 줄의 코드가 실행됨

        System.out.print(welcomeMessage);

        System.out.print("\n Main completed");

    }
}
```
>- Callable을 통해 결과가 얻어지는 시점을 결정할 수 있다
-  Future : 결과가 나올 것을 예측
- get(): 실행이 완료될 때까지 기다림, 그 다음 줄의 코드가 실행

### 7. invokeAll, invokeAny
> - invokeAll : 다량의 task 한번에 처리

```
		ExecutorService executorService = Executors.newFixedThreadPool(1);
        
 		List<CallableTask> tasks
                = List.of(new CallableTask("in28Minutes"),
                new CallableTask("Ranga"), new CallableTask("Adam"));


        List<Future<String>> results = executorService.invokeAll(tasks);

        for (Future<String> result : results) {
            System.out.println(result.get());
            
        }
```
> - invokeAny : 하나의 task의 실행이 완료된 것을 기다린 후 결과를 얻는 메소드

```
		ExecutorService executorService = Executors.newFixedThreadPool(2);
        
        List<CallableTask> tasks
                = List.of(new CallableTask("in28Minutes"), 
                new CallableTask("Ranga"), new CallableTask("Adam"));

        String result = executorService.invokeAny(tasks);

        
        System.out.println(result);
```
> - 세가지 종류의 서비스를 사용하고 있는데, 소비자에게 가장 빠른 결과를 제공하고 싶다면, 가장 먼저 나오는 결과를 반환
