package LHS11.functional_programming;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FBNumberRunner {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(4, 6, 8, 13, 3, 15);

        Integer sum = fpSum(numbers);

//        IntStream.range(1,11).map(e->e*e).forEach(e-> System.out.println(e));

//        List.of("Apple","Banana","Cat").stream().map(s->s.toLowerCase()).forEach(c-> System.out.println(c));

        List.of("Apple","Banana","Cat").stream().map(s->s.length()).forEach(c-> System.out.println(c));

        Integer integer = List.of(1, 2).stream().max((n1, n2) -> Integer.compare(n1, n2)).get();

        IntStream.range(1,11).boxed().map(e -> e * e).collect(Collectors.toList());

        System.out.println("integer = " + integer);

        System.out.println("sum = " + sum);
    }

    private static Integer fpSum(List<Integer> numbers) {
        return numbers.stream() // 함수형 프로그래밍 -> 지역변수나 반복문 사용법을 신경쓰지 않아도 된다.
                                // 1.변수 변이를 피함( 값이 변하는 변수를 넣지 않는다.), 2. 뭘할지에 집중한다.
                .filter(element -> element % 2 == 1)
                .reduce(
                        0, //첫 번째는 매개변수,
                        (num1, num2) -> num1 + num2 //  두 번째는 함수 정의 , 매개변수가 두 개일때는 괄호가 필요
                );
    }



    private static Integer normalSum(List<Integer> numbers){
        int sum=0;
        for (Integer number : numbers) {
            sum+=number;
        }
        return sum;
    }
    //중간 연산  -> filter, sorted,distinct ,map 중간 연산의 갯수는 제한이 없읍
    //종단 연산 -> reduce, max

    //함수 생성의 단축형이고 여러 줄의 코드를 가질 수 있음

    //구조적 프로그래밍 -> 값이 계속 바뀜, 변이가 있음

    //collect -> 새로운 리스트를 그룹해줌
}
