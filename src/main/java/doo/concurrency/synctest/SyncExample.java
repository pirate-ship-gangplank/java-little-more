package doo.concurrency.synctest;

public class SyncExample {

  public synchronized void hi()  {
    System.err.println("Sync Example Instance is Locked");

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.err.println("lock finish");
  }

  public void notSynchronizedMethod() {
    System.err.println("this is notSynchronizedMethod");
  }

  public synchronized void synchronizedMethod() {
    System.err.println("this is synchronizedMethod");
  }

  public static void main(String[] args) {
    SyncExample syncExample = new SyncExample();
    Thread thread1 = new Thread(() -> syncExample.hi());
    Thread thread2 = new Thread(() -> syncExample.synchronizedMethod());

    thread1.start();
    thread2.start();
  }

}
