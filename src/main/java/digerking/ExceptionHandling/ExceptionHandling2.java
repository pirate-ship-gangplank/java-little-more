package digerking.ExceptionHandling;

public class ExceptionHandling2 {
    public static void main(String[] args) {
        method1();

        System.out.println("Main Ended");
    }

    private static void method1() {
        method2();

        System.out.println("Method1 Ended");
    }

    private static void method2() {
        // 실행
        try {
//            String str = null;
//            str.length();

            int[] i = {1, 2};
            int number = i[3];

            System.out.println("Method2 Ended");
        }

        // 예외 발생 시의 동작
        catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("ArrayIndexOutOfBoundsException");
            exception.printStackTrace();
        }

        // 예외 발생 시의 동작
        catch (NullPointerException exception) {
            System.out.println("NullPointerException");
            exception.printStackTrace();
        }

        // 예외 발생 시의 동작
        catch (Exception exception) {
            //전체 Stack Trace 를 출력한다.
            exception.printStackTrace();
        }
    }
}
