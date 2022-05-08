package doo.thread;

public class MyTask extends Thread {
  private int number;

  public void run() {
    System.out.println("thread number : " + number);
    for (int i = number * 100; i <= number * 100 +99; i++) {
      System.out.println(i);
    }
  }
}
