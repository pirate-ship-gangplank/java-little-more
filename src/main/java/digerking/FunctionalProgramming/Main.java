package digerking.FunctionalProgramming;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<String> list = List.of("Apple", "Banana", "Cat", "Dog");

        List<String> list = List.of("Apple", "Bat", "Cat", "Dog");

        List<Integer> exList = List.of(1, 4, 7, 9);

        printBasic(list);
        printFunctionalProgramming(list);

        printBasicWithFiltering(list);
        printFunctionalProgrammingWithFiltering(list);

        printOddEvenNumberFiltering(exList);


    }

    //2.2 리스트를 반복문으로 사용하는 일반적인 방법
    private static void printBasic(List<String> list ){
        for (String string : list) {
            System.out.println(string);
        }
    }

    // 2.2 리스트를 함수형 프로그래밍으로 사용하는 방법
    // 함수형 프로그래밍 첫번 째 개념 --> 매개변수를 함수로
    private static void printFunctionalProgramming(List<String> list ){
        //리스트를 stream 으로
        //리스트의 각 요소에 대해서 forEach(개별로, 각각) element 라고 지정하고, element 를 println

        // forEach 에 함수 코드를 보내는 것임
        list.stream().forEach(
                element -> System.out.println("element - " + element)
        );
    }

    // 2.4 반복문을 이용한 리스트 필터링 방법
    private static void printBasicWithFiltering(List<String> list){
        for (String string : list) {
            if (string.endsWith("at")) {
                System.out.println(string);
            }
        }
    }

    // 2.4 함수를 이용한 리스트 필터링 방법
    // 함수형 프로그래밍 -> 단순 반복이 아닌 내부 로직을 생각하는 법을 적용
    private static void printFunctionalProgrammingWithFiltering(List<String> list){
        list.stream()
                .filter(element -> element.endsWith("at"))
                .forEach(element -> System.out.println("element - " + element));
    }

    
    // 2.4 함수형 프로그래밍 방식으로 홀수만 출력하기
    private static void printOddEvenNumberFiltering(List<Integer> list) {
        list.stream()
                .filter(element -> element % 2 == 1)
                .forEach(element -> System.out.println("Odd Number - " + element));
    }

}
