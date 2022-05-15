package doo.concurrency;

public class Counter {

  private int num = 0;

  public synchronized void increase() {
    num++;
  }

  public int getNum() {
    return num;
  }
}
