package digerking.FunctionalProgramming;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

        System.out.println("-----------");

        endMax();

        System.out.println("-----------");

        endMin();

        System.out.println("-----------");

        Ex4();

        System.out.println("-----------");

        Ex5();

        System.out.println("-----------");

        OptionalPractice();
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


    // 2.10 종단연산 - Max
    private static void endMax() {
        //optional 객체로 반환
        System.out.println(List.of(23, 12, 34, 53)
                .stream()
                .max((n1, n2) -> Integer.compare(n1, n2)));
        
        //optional 객체에서 꺼내기
        System.out.println(List.of(23, 12, 34, 53)
                .stream()
                .max((n1, n2) -> Integer.compare(n1, n2)).get());
    }

    // 2.11 종단연산 - Min
    private static void endMin() {
        //홀수 중에서 최솟값
        System.out.println(List.of(23, 12, 34, 53).stream()
                .filter(element -> element %2 != 0)
                .min((n1, n2) -> Integer.compare(n1, n2)));

        //홀수 값들을 리스트 형식으로
        System.out.println(List.of(23, 12, 34, 53).stream()
                .filter(element -> element %2 != 0)
                .collect(Collectors.toList()));
    }

    // 2.11 연습문제 - 짝수를 뽑아서 리스트로 저장
    private static void Ex4() {
        System.out.println(List.of(23, 12, 34, 53).stream()
                .filter(e -> e %2 == 0)
                .collect(Collectors.toList())
        );
    }

    // 2.11 연습문제 - 10개의 정수를 제곱한 값을 원소로 갖는 리스트를 만들기
    private static void Ex5() {
        //.boxed() -> Stream 객체로 변환해주는 중간 연산
        
        System.out.println(
                IntStream.range(0, 10)
                        .map(e -> e * e)
                        .boxed()
                        .collect(Collectors.toList())
        );
    }

    // 2.12 Optional -> NPE 를 피하고자 사용
    private static void OptionalPractice() {
        Optional<Integer> test = List.of(23, 45, 67, 12)
                .stream()
                .filter(n -> n%2 == 0)
                .max((n1, n2) -> Integer.compare(n1, n2));

        if (test.isPresent()) {
            System.out.println(test.get());
        }
        else System.out.println("Empty" + test);
    }




}
