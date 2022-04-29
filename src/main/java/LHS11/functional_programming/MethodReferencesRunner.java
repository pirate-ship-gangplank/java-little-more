package LHS11.functional_programming;

import java.util.List;

public class MethodReferencesRunner { //메소드 레퍼런스를 통한 함수형 프로그래밍 코딩

    public static void print(Integer number){
        System.out.println(number);
    }

    public static void main(String[] args) {

        List.of("Ant", "Bat","Cat","Dog","Elephant").stream()
                .map(e->e.length())
                .forEach(e->MethodReferencesRunner.print(e));

        List.of("Ant", "Bat","Cat","Dog","Elephant").stream()
                .map(String::length)
                .forEach(MethodReferencesRunner::print); // class::method

//        메소드 참조를 사용하는 이유는 코드의 가독성을 높이기 위해서

        Integer max = List.of(23, 45, 67, 34).stream()
                .filter(MethodReferencesRunner::isEven)
//                .max((n1, n2) -> Integer.compare(n1, n2))
                .max(Integer::compare)
                .orElse(0);
        System.out.println(max);

        Integer max1 = List.of(23, 45, 67, 34).stream()
                .max((n1, n2) -> Integer.compare(n1, n2))
                .get();




    }

    public static boolean isEven(Integer number){
        return number % 2 == 0;
    }
}
