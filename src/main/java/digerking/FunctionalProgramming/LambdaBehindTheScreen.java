package digerking.FunctionalProgramming;

import java.util.List;

public class LambdaBehindTheScreen {
    public static void main(String[] args) {
        List.of(23, 45, 34, 45, 36, 48)
                .stream()
                .filter(n -> n%2 == 0)
                .forEach(e -> System.out.println(e));
    }
}
