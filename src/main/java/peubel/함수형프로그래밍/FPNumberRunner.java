package peubel.함수형프로그래밍;

import java.util.List;
import java.util.stream.IntStream;

public class FPNumberRunner {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(4, 6, 8, 13, 3, 15);
        final Integer reduce = numbers.stream()
                .filter(num -> num%2==1)
                .reduce(0, (num1, num2) -> num1 + num2);

        System.out.println(reduce);
        System.out.println(normalSum(numbers));

        System.out.println("--------제곱 추출--------");
        IntStream.range(1, 10)
                .map( num -> num*num)
                .forEach(System.out::println);

        System.out.println("--------소문자 추출--------");
        List.of("Apple", "Ant", "Bat").stream()
                .map(String::toLowerCase)
                .forEach(System.out::println);

        System.out.println("--------글자 길이 추출--------");
        List.of("Apple", "Ant", "Bat").stream()
                .map(String::length)
                .forEach(System.out::println);
    }

    private static int normalSum(List<Integer> numbers) {
        int sum = 0;
        for(Integer num : numbers){
            sum += num;
        }
        return sum;
    }
}
