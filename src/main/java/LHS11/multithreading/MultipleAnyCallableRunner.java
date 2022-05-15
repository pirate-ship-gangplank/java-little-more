package LHS11.multithreading;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//1. 쓰레드를 생성하는 2가지 방법
//2. 쓰레드가 가질 수 있는 다양한 상태
//3. 쓰레드에 대해 각각 다른 우선순위를 부여
//4. 쓰레드가 실행하는 동안 대기하도록 하여 쓰레드 사이에 소통이 일어남
//5. ExecutorService에서 몇 개의 쓰레드를 동시 사용하여 task를 진행
//6. 쓰레드에서 결과가 얻어지는 지점 설정 가능
//7. 다량의 task를 한번에 처리, 모든 task의 처리가 끝날 때까지 기다리는 메소드, 하나의 task의 실행이 완료된 것을 기다린 후 결과를 얻는 메소드
// 병렬 구조로써 가능한 많은 task 실행
public class MultipleAnyCallableRunner {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<CallableTask> tasks
                = List.of(new CallableTask("in28Minutes"), new CallableTask("Ranga"), new CallableTask("Adam"));


        String result = executorService.invokeAny(tasks);

        //세가지 종류의 서비스를 사용하고 있는데, 소비자에게 가장 빠른 결과를 제공하고 싶다면, 가장 먼저 나오는 결과를 반환
        System.out.println(result);

        executorService.shutdown();
    }
}
