package peubel.함수형프로그래밍;

import java.util.List;

public class FunctionalProgramming {

    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "cat", "bat");
        printBasic(list);
        System.out.println();
        printWhitEP(list);
        System.out.println();
        printBasicWithFiltering(list);
        System.out.println();
        printWhitEPWithFiltering(list);

    }


    static void printBasic(List<String> list) {
        for (String string : list) {
            System.out.println(string);
        }
    }

    static void printWhitEP(List<String> list) {
        list.stream().forEach(
                System.out::println
        );
    }

    static void printBasicWithFiltering(List<String> list) {
        for (String string : list) {
            if (string.endsWith("at"))
                System.out.println(string);
        }
    }

    static void printWhitEPWithFiltering(List<String> list) {
        list.stream()
                .filter(element -> element.endsWith("at"))
                .forEach(element -> System.out.println(element)
                );
    }
}
