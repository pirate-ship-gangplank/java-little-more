# 동시 컬렉션의 동시성과 활용한 원자

### Thread Safe

멀티 스레드 프로그래밍에서 공유 자원을 여러 스레드가 동시에 접근해도, 프로그램 실행에 문제가 없는 특징 혹은 상태

### Thread Safe 하지 않는 경우

i++ 연산의 경우 thread safe 하지 않다. 

```java
public class Counter {

  private int num = 0; // 0으로 초기화

  public void increase() {
    num++; // ++연산은 스레드 세이프하지 않다.
  }

  public int getNum() {
    return num;
  }
}

--------------------------------------------------------------------

Counter counter = new Counter();
Thread thread1 = new Thread(() -> {
  for (int i = 0; i < 1000; i++) {
    counter.increase();
  }
});
Thread thread2 = new Thread(() -> {
  for (int i = 0; i < 1000; i++) {
    counter.increase();
  }
});

thread1.start();
thread2.start(); // thread1과 thread2를 병렬로 실행
thread1.join();
thread2.join(); // thread1과 thread2가 모두 끝날 때까지 대기

System.out.println(counter.getNum()); // 1616 (매번 결과는 다르지만, 2000보다 낮은 결과값이 나온다.)
```

위 문제를 synhcronized 키워드를 사용하여 스레드 세이프하도록 변경할 수 있다.

```java
public class Counter {

  private int num = 0;

  public synchronized void increase() { // synchronized 연산자를 사용하여 오직 하나의 스레드만 접근할 수 있도록 할 수 있다.
    num++;
  }

  public int getNum() {
    return num;
  }
}

--------------------------------------------------------------------

Counter counter = new Counter();
Thread thread1 = new Thread(() -> {
  for (int i = 0; i < 1000; i++) {
    counter.increase();
  }
});
Thread thread2 = new Thread(() -> {
  for (int i = 0; i < 1000; i++) {
    counter.increase();
  }
});

thread1.start();
thread2.start(); // thread1과 thread2를 병렬로 실행
thread1.join();
thread2.join(); // thread1과 thread2가 모두 끝날 때까지 대기

System.out.println(counter.getNum()); // 2000 (synchronized 키워드를 사용했으므로 항상 2000이 나온다.)
```

### synchronized 문제점

```java
public class SyncExample {

  public synchronized void hi()  {   // synchronized 메서드
    System.out.println("Sync Example Instance is Locked");

    try {
      Thread.sleep(1000);   // hi 메서드 실행중에 다른 스레드가 실행되도록 1초의 대기 시간을 가진다.
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("lock finish");
  }

  public void notSynchronizedMethod() {  // synchronized 메서드
    System.out.println("this is notSynchronizedMethod");
  }

  public synchronized void synchronizedMethod() { // synchronized가 아닌 일반 메서드
    System.out.println("this is synchronizedMethod");
  }

}
```

- 2개의 synchronized 메서드 `hi()`, `synchronizedMethod()` 가 있고,
- 1개의 일반 메서드 `notSynchronizedMethod()`가 있다.

synchronized 메서드 동작 중 synchronized가 아닌 일반 메서드 호출 시, 일반 메서드는 정상 동작한다.

```java
// synchronized 메서드 동작 중 synchronized가 아닌 일반 메서드 호출
public static void main(String[] args) {
  SyncExample syncExample = new SyncExample();
  Thread thread1 = new Thread(() -> syncExample.hi());
  Thread thread2 = new Thread(() -> syncExample.notSynchronizedMethod()); // 일반 메서드 호출

  thread1.start();
  thread2.start();
}

// 결과 
Sync Example Instance is Locked
this is notSynchronizedMethod    // 일반 메서드는 syncrhonized 메서드 동작 중에도 정상 동작
lock finish
```

- 일반 메서드는 synchronized 메서드 동작 중에도 정상 동작한다.

synchronized 메서드 동작(A) 중 같은 클래스의 다른 synchronized 메서드(B) 호출 시, A가 끝날 때까지 대기 후에 B가 실행된다.

```java
// syncrhonized 메서드 동작 중 또다른 synchronized 메서드 호출
public static void main(String[] args) {
  SyncExample syncExample = new SyncExample();
  Thread thread1 = new Thread(() -> syncExample.hi());
  Thread thread2 = new Thread(() -> syncExample.synchronizedMethod()); // 또다른 synchronized 메서드

  thread1.start();
  thread2.start();
}

// 결과 
Sync Example Instance is Locked
lock finish                        // 먼저 시작한 synchronized 메서드가 끝날 때까지 기다린다.
this is synchronizedMethod         // 먼저 시작한 synchronized 메서드가 끝난 후에 작업 시작
```

- synchronized 메서드는 인스턴스 단위로 lock이 걸리는데, 클래스 내부의 다른 synchronized 메서드에 대해서도 동일한 lock이 적용된다.
- 같은 synchronized 메서드인 경우, `SyncExample`에 lock을 걸기 때문에 먼저 시작한 synchronized 메서드가 끝난 후에 `SyncExample` 를 얻어서 작업을 수행할 수 있다.

오직 하나의 스레드만 접근할 수 있게 되면서, 다른 스레드는 전부 대기를 해야할 수 있다.

- synchronized 메서드를 많은 스레드들이 사용하기 원한다면 대기 시간이 길어질 수 있다.
- synchronized 키워드를 사용할 때는 성능에 대한 영향을 생각해야한다.

## Lock - ReentrantLock 객체

`ReentrantLock`은 lock을 컨트롤 할 수 있도록 도와주는 클래스이다.

synchronized에 비해 lock에 대해 더 많은 컨트롤을 할 수 있고, 부분적으로 lock을 적용할 수 있다는 차이가 있다. (synchronized의 경우, synchronized 키워드가 적용된 모든 메서드에 의도와 상관없이 무조건 lock이 걸림)

- lock() : 호출한 스레드가 해당 객체를 Lock. 만약 이미 Lock이 걸려있으면 무한정 기다린다.
- tryLock() : 호출한 스레드가 해당 객체를 Lock 시도하고 그 결과를 리턴한다. (만약 다른 Thread가 Lock했다면 호출 즉시 false를 리턴)
- trylock(long timeout, TimeUnit timeUnit) : 일정 시간동안 Lock을 얻기 위해 기다린다.
- unlock() : Lock을 반환한다.
- isHeldByCurrentThread() : 현재 스레드가 Lock을 소유하고 있는지 여부 리턴한다.
- getHoldCount() : 해당 스레드가 가지고 있는 lock 갯수 리턴한다.

[참고 자료](https://theuphill.tistory.com/16)

```java
public class LockExample {

  private int num = 0;
  private Lock lock1 = new ReentrantLock();
  private Lock lock2 = new ReentrantLock();

  public void first() {

    lock1.lock();  // lock1 - 호출한 스레드가 해당 객체를 lock 시킨다.
    System.out.println("this is first method");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("first method lock finish");
    lock1.unlock();

  }

  public void second() {
    lock2.lock();  // lock2 - 호출한 스레드가 해당 객체를 lock 시킨다. 
    System.out.println("this is second method");

    System.out.println("second method lock finish");
    lock2.unlock();
  }

  public int getNum() {
    return num;
  }

}
```

```java

public static void main(String[] args) throws InterruptedException {
  LockExample lockExample = new LockExample();
  Thread thread1 = new Thread(() -> lockExample.first());
  Thread thread2 = new Thread(() -> lockExample.second());

  thread1.start();
  Thread.sleep(50);
  thread2.start();
}

// 결과
this is first method
this is second method
second method lock finish
first method lock finish
```

- 

```java
// lock을 썼지만 thread safe 하지 않은 경우
public void add1() {
  lock1.lock();  // lock을 걸었다. - lock1 사용
  for (int i = 0; i < 100000; i++) {
    num++;
  }
  lock1.unlock(); 
}

public void add2() {
  lock2.lock();  // lock을 걸었다. - lock2 사용
  for (int i = 0; i < 100000; i++) {
    num++;
  }
  lock2.unlock();
}

-------------------------------------------------------------------------------

public static void main(String[] args) throws InterruptedException {
  LockExample lockExample = new LockExample();

  Thread thread1 = new Thread(() -> lockExample.add1());
  Thread thread2 = new Thread(() -> lockExample.add2());
 
  thread1.start();
  thread2.start();

  thread1.join();
  thread2.join();

  System.out.println(lockExample.getNum());  // 1094199 (매번 결과는 다르지만, 20000보다 낮은 결과값이 나온다.)
}
```

- 서로 다른 lock을 사용하였으므로, num에 대해 스레드 세이프하지 않았다.
- 각 lock 마다 서로 다른 자원에만 접근이 가능하도록 구현 단계에서 구성이 되어야 스레드 세이프한 로직을 만들 수 있을 것 같다. 
→ ex) lock1에는 num1만 업데이트하고 lock2에는 num2만 업데이트하도록 하면,
    - lock 간에 서로 영향을 받지 않으면서, (lock1이 걸려있는 동안, lock2 메서드는 사용 가능)
    - 자원간엔 서로 동기화 문제가 발생하지 않을 수 있다. (num1의 값이 변경되어도 num2에 영향 x)

[참고 자료](https://jgrammer.tistory.com/entry/Java-혼동되는-synchronized-동기화-정리)

## 원자성 클래스 - AtomicInteger

```java
public class AtomicIntegerExample {
  
  AtomicInteger num = new AtomicInteger();
  
  public void incrementNum() {
    num.incrementAndGet();
  }
  
  public int getNum() {
    return num.get();
  }
}
```

- 스레드 세이프한 연산을 제공하는 클래스를 사용하면 자동으로 스레드 세이프한 연산을 하기 때문에 Lock이나 synchronized 키워드를 사용할 필요가 없다.
- num++, num--과 같은 간단한 연산인 경우에 사용할 수 있는 클래스로, 복잡한 로직에서는 lock 등을 사용하여 스레드 세이프한 로직을 만들 수 있도록 해야한다.
→ 원자성 클래스는 간단한 연산 정도만을 처리해줄 수 있으므로, 복잡한 로직엔 한계가 있다.

## ConcurrentHashMap

thread safe한 연산을 위해 사용하는 컬렉션 클래스

### HashMap 사용 - NOT thread safe

```java
String str = "ABCD ABCD ABCD";
Map<Character, LongAdder> map = new HashMap<>();

for (Character c : str.toCharArray()) {
   LongAdder longAdder = map.get(c);
   if (longAdder == null) {
     longAdder = new LongAdder();
   }
   longAdder.increment();
   map.put(c, longAdder);
}

System.out.println(map);  // { =2, A=3, B=3, C=3, D=3}
```

- HashMap은 스레드 세이프 하지 않다.
- 여러개의 스레드가 동시에 작업을 수행할 때, 시점에 따라 map.get(c)를 했을 때 나오는 value가 다를 수 있다.

### **ConcurrentHashMap 사용 - Thread Safe**

```java
String str = "ABCD ABCD ABCD";
ConcurrentMap<Character, LongAdder> map = new ConcurrentHashMap<>();

for (Character c : str.toCharArray()) {
  map.computeIfAbsent(c, character -> new LongAdder()).increment();
}

System.out.println(map);  // { =2, A=3, B=3, C=3, D=3}
```

- 스레드 세이프한 연산을 제공하는 ConcurrentHashMap을 사용하여 스레드 세이프하도록 변경
- 내부 구현의 이야기이지만, ConcurrenctHashMap은 내부 자료구조를 지역(region)별로 나누고, 각 지역마다 Lock을 사용하여 스레드 세이프를 유지하면서도 모든 자원이 lock이 걸리지 않도록 구현되어 성능적 이점도 있다.

## CopyAndWrite

`CopyAndWriteArrayList`, `CopyAndWriteMap`, `CopyAndWriteSet` 등의 컬렉션이 제공된다.

일반적인 컬렌션과 동일하게 사용이 가능하지만, thread safe 하다. CopyAndWrite는 수정 시 전체 데이터를 복사하는 기능을 한다. 
→ 전체 데이터를 복사하는 작업으로, 매우 비싼 작업이다.

- 하지만, 쓰기 작업이 적고 읽기 작업이 많은 경우엔 유용하게 사용할 수 있다. (수정시에 전체 복사하기 때문)
->읽기 작업마다 synchronized 키워드나 Lock을 사용하지 않아도 편하게 구현이 가능하다.
- CopyAndWrite를 사용하지 않고, 데이터 추가(add)와 조회(get) 메서드에 synchronized를 걸어두면 여러 스레드 중 오직 하나의 스레드만이 조회가 가능하기 때문에 성능 이슈가 발생할 수 있다.
→ 추가(add) 메서드에만 동기화 작업을 하고, 조회(get) 메서드엔 동기화 작업을 걸지 않는 방법

[ 참고 자료 ]

[https://theuphill.tistory.com/16](https://theuphill.tistory.com/16)

[https://jgrammer.tistory.com/entry/Java-혼동되는-synchronized-동기화-정리](https://jgrammer.tistory.com/entry/Java-%ED%98%BC%EB%8F%99%EB%90%98%EB%8A%94-synchronized-%EB%8F%99%EA%B8%B0%ED%99%94-%EC%A0%95%EB%A6%AC)