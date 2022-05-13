package digerking.ExceptionHandling;

public class CheckedException {
    public static void main(String[] args) {

        // sleep 이라는 메서드 자체가 InterruptException 예외를 터뜨릴 수 있다.
        // Thread.sleep(2000);

        // 해결방법 1
        try {
            someOtherMethod();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    // 해당 메서드를 호출하는 클래스에서, 이미 예외를 처리했어도
    // 해당 메서드에서 예외처리가 되어있지 않기 때문에 컴파일 오류가 발생한다.

    // throws InterruptedException 으로 해결
    private static void someOtherMethod() throws InterruptedException {
        Thread.sleep(2000);
    }
}
