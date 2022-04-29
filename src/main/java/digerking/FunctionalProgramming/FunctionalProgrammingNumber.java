package digerking.FunctionalProgramming;

import java.util.List;
import java.util.stream.IntStream;

public class FunctionalProgrammingNumber {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(4, 6, 8, 13, 3, 15, 8, 6, 21);
        
//        // 2.5 일반적인 리스트의 합을 구하는 방식
//        int sum = normalSum(numbers);
//        System.out.println(sum);
//
//        // 2.5 함수형 프로그램
//        int functionalSum = functionalProgrammingSum(numbers);
//        System.out.println(functionalSum);
//
//        int oddSum = functionalProgrammingOddSum(numbers);
//        System.out.println(oddSum);

        // 2.8 sort
        middleOperationSorted(numbers);

        System.out.println("-----------");

        // 2.8 distinct
        middleOperationDistinct(numbers);

        System.out.println("-----------");

        // 2.8 distinct sorted
        middleOperationDistinctSorted(numbers);

        System.out.println("-----------");

        // 2.8 distinct Map
        middleOperationDistinctMap(numbers);

        System.out.println("-----------");

        Ex1();

        System.out.println("-----------");

        Ex2();

        System.out.println("-----------");

        Ex3();
    }

    // 2.5 일반적인 리스트의 합을 구하는 방식
    private static int normalSum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    // 2.6, 2.7 함수형 프로그래밍을 이용한 합을 구하는 방식 + 람다
    private static int functionalProgrammingSum(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, (number1, number2) -> {
                            System.out.println(number1 + " " + number2);
                            return number1 + number2;
                        });
    }

    // 2.5 함수형 프로그래밍을 이용한 홀수의 합을 구하는 메서드
    private static int functionalProgrammingOddSum(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number%2 == 1)
                .reduce(0, (number1, number2) -> number1 + number2);
    }


    // 2.8 스트림 중간 연산 (sort)
    private static void middleOperationSorted(List<Integer> numbers) {
        numbers.stream().sorted().forEach(e -> System.out.println(e));
    }

    // 2.8 스트림 중간 연산 (distinct)
    // 정렬은 하지 않고 중복된 요소를 차례로 제거
    private static void middleOperationDistinct(List<Integer> numbers) {
        numbers.stream().distinct().forEach(e -> System.out.println(e));
    }

    // 2.8 스트림 중간 연산 (distinct)
    // 중복제거 및 정렬
    private static void middleOperationDistinctSorted(List<Integer> numbers) {
        numbers.stream().distinct().sorted().forEach(e -> System.out.println(e));
    }

    // 2.8 스트림 중간 연산 (distinct)
    // 중복제거 및 정렬
    private static void middleOperationDistinctMap(List<Integer> numbers) {
        numbers.stream().distinct().map(e -> e * e).forEach(e -> System.out.println(e));
    }

    // 2.9 연습문제
    // 1 ~ 10 까지 제곱한 수들을 출력
    private static void Ex1() {
        IntStream.range(1, 11).map(e -> e * e).forEach(e-> System.out.println(e));
    }

    // 2.9 연습문제
    // 문자열 소문자로 출력하기
    private static void Ex2() {
        List.of("Apple", "Ant", "Bat")
                .stream()
                .map(s -> s.toLowerCase())
                .forEach(s -> System.out.println(s));
    }

    // 2.9 연습문제
    // 문자열 길이 출력하기
    private static void Ex3() {
        List.of("Apple", "Ant", "Bat")
                .stream()
                .map(s -> s.length())
                .forEach(s -> System.out.println(s));
    }
}
