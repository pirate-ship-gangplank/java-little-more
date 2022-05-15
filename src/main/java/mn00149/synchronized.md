## 쓰래드의 동기화

- 멀티쓰레드는 다른 쓰레드의 작업에 영향을 줄 수 있다
- 진행중인 다른 쓰레드에 간섭받지 않으려면 동기화가 필요하다
- 쓰레드의 동기화 => 한쓰레드가 다른 쓰레드에 간섭하지 못하게 하는 것
- 동기화 하려는 문장을 임계영역 으로 설정
- 임계영역에 접근 하려면 lock 이 필요
- lock은 객체당 한개씩만 존제

```
class ThreadEx22 {
	public static void main(String args[]) {
		Runnable r = new RunnableEx22();
		new Thread(r).start();
		new Thread(r).start();
	}
}

class Account2 {
	private int balance = 1000; // private으로 해야 동기화가 의미가 있다.

	public  int getBalance() {
		return balance;
	}

	public synchronized void withdraw(int money){ // synchronized로 메서드를 동기화
		if(balance >= money) {
			try { Thread.sleep(1000);} catch(InterruptedException e) {}
			balance -= money;
		}
	} // withdraw
}

class RunnableEx22 implements Runnable {
	Account2 acc = new Account2();

	public void run() {
		while(acc.getBalance() > 0) {
			// 100, 200, 300중의 한 값을 임으로 선택해서 출금(withdraw)
			int money = (int)(Math.random() * 3 + 1) * 100;
			acc.withdraw(money);
			System.out.println("balance:"+acc.getBalance());
		}
	} // run()
}
```

## AutomiType

atomic type은 멀티 스레드 환경에서 원자성을 보장하기 위한 개념이다. CAS 알고리즘을 통해 non-blocking하면서 가시성과 원자성을 보장해 동기화 문제를 해결한다.
atomic 변수는 멀티 스레드 환경에서 원자성을 보장하기 위해 나온 개념이다. synchronized와는 다르게 blocking이 아닌 non-blocking하면서 원자성을 보장하여 동기화 문제를 해결한다.

atomic의 핵심 동작 원리는 CAS(Compare And Swap) 알고리즘이다.

### CAS

```
public class AtomicExample {
    int val;

    public boolean compareAndSwap(int oldVal, int newVal) {
        if(val == oldVal) {
            val = newVal;
            return true;
        } else {
            return false;
        }
    }
}

```

아토믹 불리언

```
public class AtomicExample {
    private AtomicBoolean locked = new AtomicBoolean(false);

    public boolean lock() {
    	return locked.compareAndSet(false, true);
    }
}
```
