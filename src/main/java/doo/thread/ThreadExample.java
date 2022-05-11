package doo.thread;

import java.util.Hashtable;

public class ThreadExample {
  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new ExtendsThread();
    thread1.start();

    Runnable implementsRunnable = new ImplementsRunnable();
    Thread thread2 = new Thread(implementsRunnable);
    thread2.start();
    thread2.setPriority(3);

    Thread thread3 = new Thread(() -> System.out.println("thread3"));
    thread3.start();

    Thread newThread = new Thread();
    newThread.setPriority(3);
    newThread.join();

    Thread.sleep(1000);
    Thread.yield();
  }
}

class ExtendsThread extends Thread {
  public void run() {
    System.out.println("thread1");
    for(int i = 1; i <= 100; i++) {
      System.out.println("i : " + i);
    }
  }
}

class ImplementsRunnable implements Runnable {
  @Override
  public void run() {
    System.out.println("thread2");
  }
}