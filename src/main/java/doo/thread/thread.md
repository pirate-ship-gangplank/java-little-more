# 스레드

스레드 : 프로세스가 할당받은 자원을 이용하는 실행의 단위

멀티 스레드 : 하나의 프로세스 내에서 둘 이상의 스레드가 동시에 작업을 수행하는 것

## 스레드를 생성하는 3가지 방법

### 1. extends Thread

```java
public class ExtendsThread extends Thread {  // Thread 자체가 생성
  public void run() {
    // 수행시킬 로직
  }
}

--------------------------------------------------------------------------------

// 실행
Thread extendsThread = new ExtendsThread();
extendsThread.start();
```

### 2. implements Runnable

```java
class ImplementsRunnable implements Runnable { // Thread가 수행할 Runnable
  @Override
  public void run() {
    System.out.println("하이");
  }
}

--------------------------------------------------------------------------------

// 실행 
Runnable implementsRunnable = new ImplementsRunnable();
Thread thread2 = new Thread(implementsRunnable); // Thread에 Runnable 객체를 넣어줘야 한다.
thread2.start();
```

### 3. Runnable 람다 함수 사용

```java
Thread thread3 = new Thread(() -> System.out.println("thread3")); // 클래스 생성할 필요 없음
thread3.start();
```

### tips

- thread.run()은 동기로 동작하므로, 병렬로 실행시키고 싶으면 thread.start()를 실행해야 한다.

## 스레드 상태

- NEW : 스레드 객체가 생성, 아직 start() 메소드가 호출되지 않은 상태
- RUNNABLE : 실행 상태로 언제든지 갈 수 있는 상태. 다른 스레드가 우선적으로 실행되고 있는 상태
- RUNNING : cpu를 점유하고 있는 상태. 스레드의 작업을 수행한다.
- BLOCKED/WAITING : 외부 자원을 기다리고 있는 상태거나, 실행이 완료되지 않은 다른 스레드로부터 데이터를 받아야하는 상태
- TERMINATED/DEAD : 실행이 완료된 상태

## 스레드 우선 순위

```java
Thread newThread = new Thread();
newThread.setPriority(3);

---------------- Thread class ----------------
public static final intMIN_PRIORITY = 1;
public static final intNORM_PRIORITY = 5;  // default priority
public static final intMAX_PRIORITY = 10;
```

- setPriority로 우선순위 설정(제안) 가능하다. (숫자가 높을수록 높은 우선 순위를 제안할 수 있다.)
- 우선 순위를 설정했다고 항상 해당 우선순위가 되진 않고, `제안` 정도의 의미이다. (힌트의 느낌)
- 우선 순위가 더 높다고하여 우선 순위가 높은 작업이 완전히 끝난 뒤에야 다음 작업을 진행하는 것은 아니다.

## 스레드의 제어

### join

지정한 스레드의 작업이 모두 완료(die)될 때까지 기다린다.

```java
Thread thread1 = new Thread();
thread1.start();

thread1.join();  // thread1이 모든 작업을 끝낼 때까지 대기한다.

Thread thread2 = new Thread(); // thread1의 작업이 모두 끝난 후에 작업 수행
thread2.start();
```

### sleep & yield

```java
Thread.sleep(1000); // 숫자는 milli second 단위로, 1초간 잠자기가 된다.
Thread.yield(); // 현재의 스레드가 이용가능한 이 상태를 양보하거나 양도하겠다는 의미
```

- yield()는 스케줄러에 대한 힌트로, 반드시 수행된다는 보장은 없다. (힌트 정도의 역할)

### synchronized 키워드

메서드에 붙는 키워드로, 오직 하나의 thread만이 해당 메서드를 수행할 수 있다.

```java
// synchronized 메서드
public synchronized int size() {
    return count;
}
```

- synchronized 메서드 안의 코드 중 한줄이라도 어떤 스레드가 수행하고 있다면, 다른 스레드는 해당 메서드를 수행하지 못하고 대기한다.
→ 단 하나의 스레드만 접근 가능하기 때문에 오버헤드가 발생할 가능성이 높다.
- 특정 자원을 여러 스레드가 동시에 접근 가능할 때 사용가능하다.

# Executor Service

### execute 메서드

```java
Thread thread1 = new Thread();
Thread thread2 = new Thread();

ExecutorService singleExecutor = Executors.newSingleThreadExecutor(); //싱글 스레드로 작업 수행
singleExecutor.execute(thread1); // 먼저 수행
singleExecutor.execute(thread2); // thread1 작업이 끝난 후에 수행된다. (executorService가 싱글 스레드이므로)

ExecutorService multiExecutor = Executors.newFixedThreadPool(2); // thread 2개 할당
multiExecutor.execute(thread1);
multiExecutor.execute(thread2); // thread1과 thread2가 함께 수행된다.
```

- newFixedThreadPool에 지정된 숫자에 따라 스레드 갯수가 할당된다.
- 스레드를 5개 할당했다고 하면 동시에 5개의 작업이 수행되고, 각 작업이 각 스레드에서 종료되면 대기중인 다음 작업을 수행한다. 
→ 즉, 동시에 수행되는 작업수는 5개까지이며, 다음 작업을 하려면 각 스레드에서 수행되고 있던 작업이 끝나야한다.

### submit 메서드

```java
class CallableTask implements Callable<String> {  // callback 받을 내용이 있으므로, Callable 클래스 implements 

  @Override
  public String call() throws Exception { // 위의 Callable<??> 제네릭 타입에 따라 return type이 바뀐다.
    Thread.sleep(1000);
    return "hello";
  }
}

--------------------------------------------------------------------------------

// 실행
Future<String> future = executorService.submit(new CallableTask());
String result = future.get(); // 여기서 future의 작업이 끝날 때까지 기다린다.
System.out.println("job finish"); // future의 작업이 모두 끝난 후에 출력된다.
```

- `Future`는 특정 시점에서 결과가 나올 것을 약속하는, 계약의 개념이다.
- Runnable은 return type이 void인 반면, Callable은 지정한 반환하려는 타입을 return 해준다.
- `future.get()`을 하면, future의 작업이 끝날 때까지 기다린다.

### invokeAll 메서드

```java
ExecutorService executorService = Executors.newFixedThreadPool(1);

// 하나의 CallableTask마다 3초의 작업 시간이 걸린다고 가정한다.
List<CallableTask> tasks = List.of(new CallableTask(), new CallableTask(), new CallableTask());

List<Future<String>> futures = executorService.invokeAll(tasks); // 모든 Callable 작업을 마칠 때까지 기다린다.

for (Future<String> future : futures) {
  System.err.println(future.get()); // future.get()할 때마다 하나씩 작업을 수행할 것 같지만, 그렇지 않다.
}
```

- invokeAll 메서드는 모든 Callable 작업을 마칠 때까지 기다린다. (전달된 작업이 전부 끝날 때까지 holding)
    - executorService에 스레드를 1개만 할당했으면 총 9초의 작업 시간이 걸리고, 9초 후에 모든 결과를 한번에 반환한다.
    - 만약 executorService에 스레드를 3개 할당했으면 각 Callable 작업이 스레드를 하나씩 점유할 수 있으므로, 모든 Callable 작업이 병렬로 수행되어 총 3초의 작업시간이 소요된다.
- future.get()할 때마다 하나씩 작업을 수행할 것 같지만, 그렇지 않다.
- `invokeAll(tasks, long timeout, TimeUnit unit)` 을 사용하여 지정된 timeout 까지 완료되지 못한 작업은 취소 처리 시킬 수도 있다.

### invokeAny 메서드

```java
ExecutorService executorService = Executors.newFixedThreadPool(1);

List<CallableTask> tasks = List.of(new CallableTask(), new CallableTask(), new CallableTask());

String result = executorService.invokeAny(tasks); // 하나의 결과만 도출된다.
```

- 가장 빨리 수행된 작업이 반환되기 때문에 결과는 1개만 있으며, 실행이 첫번째로 끝난 것의 값을 얻고 나머지 작업들은 취소한다.
- 어떤 작업이 가장 먼저 끝날지는 매 실행마다 다르기 때문에, 어떤 결과가 나올지를 확신하기는 어렵다.