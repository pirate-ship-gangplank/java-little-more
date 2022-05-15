# Thread

- 하나의 프로그램에서 여러 개를 동시에 처리하는 기능
- 우리가 일반적을으로 사용하는 프로그램은 한 프로그램에서 하나씩 순차적으로
  처리되는 방식이다. 즉 작업1이 완료된 뒤 작업 2가 처리된다.
- 스레드는 작업1과 작업2가 동시에 처리될 수 있다.

- Process
  . thread의 집합으로 하나의 exe, com, dll 프로그램을 말한다.
  . 현재 실행되고 있는 프로그램이다.
  . Process간 자원(memory)을 공유할 수 없다. 따라서 Process를 많이 발생 시키면
  자원이 바닥나게 된다.
  . 일종에 프로그램이 돌아가도록 자원이 갖춰진 공장으로 비유 할 수 있다

- Thread
  . 독립된 작업처리 단위로 프로세스를 구성한다.
  . 메소드(함수)단위의 처리 모듈 이며 process의 구성 요소이다.
  . Thread는 많이 발생해도 자원을 공유함으로 Process에 비해 시스템에 적은
  부담이 된다.
  . 스레드 스케줄러에 의해서 스레드의 여러상태중 실행상태로 변경할 수 있다.
  . 스레드의 상태는 준비상태, 실행상태, 대기상태, 정지상태가 있다.
  . 프로세스가 공장이라면 쓰레드는 그 공장의 자원을 이용해 일하는 일꾼으로 비유 할 수 있다

### 스레드의 생성 방법

- Thread 클래스를 상속받는 방법
- Runnable 인터페이스를 구현하는 방법
  . 자바는 다중 상속이 안됨으로 클래스가 특정 클래스를 상속할 필요가 있는 경우는 반드시
  Runnable인터페이스를 구현해야 한다.

  #### 출처

  Lectureblue.pe.kr

### 스레드 실습

> > > > > ThreadTest1.java
> > > > > class MyThread extends Thread{

    private int num;
    private String name;

    public MyThread(String a, int b) {
        name = a;
        num = b;
    }

    public void run() {  //Callback 메소드
        for(int i=0; i<num ; i++){
            System.out.println(name + " : " + i);
        }
    }

}
public class ThreadTest1{
public static void main(String args[]) {
MyThread t1 = new MyThread("first", 1000);
MyThread t2 = new MyThread("second", 1000);
MyThread t3 = new MyThread("third", 1000);

        t1.start();
        t2.start();
        t3.start();
    }

}

> > > > > ThreadTest2.java
> > > > > class ThreadOne implements Runnable {

    private int num;
    private String name;
    public ThreadOne(String a, int b) {
        name = a;
        num = b;
    }
         public void run(){
        for(int i=0; i<num;i++)
            System.out.println(name + " : " + i);
    }

}
public class ThreadTest2{
public static void main(String args[]) {
//Runnable Interface를 구현한 클래스 객체를  
 //Thread 클래스의 생성자로 할당합니다.
Thread t1 = new Thread(new ThreadOne("first", 1000));
Thread t2 = new Thread(new ThreadOne("second", 1000));
Thread t3 = new Thread(new ThreadOne("third", 1000));
t1.start();  
 t2.start();  
 t3.start();  
 }
}
