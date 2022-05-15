package digerking.ExceptionHandling;

import java.util.Scanner;

public class Finally {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            int[] numbers = {1, 2, 3, 4, 5};
            int number = numbers[2];

            System.out.println("Before Scanner Close");

            scanner.close();
        }

        catch (Exception exception) {
            exception.printStackTrace();
        }

        // 예외 처리를 한 후 수행할 코드들
        finally {
            System.out.println("Before Scanner Close");
            scanner.close();
        }

        System.out.println("Just Before closing out main");
    }
}