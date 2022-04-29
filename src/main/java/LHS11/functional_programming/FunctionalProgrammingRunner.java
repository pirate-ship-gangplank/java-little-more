package LHS11.functional_programming;

import java.util.List;

public class FunctionalProgrammingRunner {

    public static void main(String[] args) {
        List<String> list = List.of("Apple", "Bat", "Cat", "Dog");

        printWithFPWithFiltering(list);
    }

    private static void printBasic(List<String> list) {
        for (String string : list) {
            System.out.println(string);
        }
    }
    private static void printBasicWithFiltering(List<String> list) {
        for (String string : list) {
            if(string.endsWith("at"))
                System.out.println(string);
        }
    }

    private static void printWithFP(List<String> list) {
        list.stream().forEach( // foreach에 함수코드를 보내는 것 , 흐름의 각 요소마다 실행될 함수를 보내는 것
                element -> System.out.println(element) // 람다
        );
    }

    private static void printWithFPWithFiltering(List<String> list) {
        list.stream()
                .filter( // filter 안에 조건문이 true 라면 forEach 수행
                        element -> element.endsWith("at")
                )
                .forEach( // foreach에 함수코드를 보내는 것 , 흐름의 각 요소마다 실행될 함수를 보내는 것
                        element -> System.out.println(element) // 람다
                );
    }
}
