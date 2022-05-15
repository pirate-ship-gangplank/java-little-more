package LHS11.functional_programming;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

class EvenNumberPredicate implements Predicate<Integer>{

    @Override
    public boolean test(Integer number) {
        return number%2==0;
    }
}
class SystemOutConsumer implements Consumer<Integer>{

    @Override
    public void accept(Integer number) {
        System.out.println(number);
    }
}
class NumberSquareMapper implements Function<Integer,Integer>{

    @Override
    public Integer apply(Integer number) {
        return number * number;
    }
}

public class LambdaBehindTheScreensRunner {

    public static void main(String[] args) {
        Predicate<? super Integer> evenPredicate = createEvenPredicate();
        Predicate<? super Integer> oddPredicate = n -> n % 2 == 1;


        List.of(23, 43, 34, 45,36,48).stream()
                .filter(new EvenNumberPredicate())
                .map(n->n*n)
                .forEach(e-> System.out.println(e));
    }
    //함수형 프로그래밍은 선언형 프로그램의 방법을 허용
    private static Predicate<Integer> createEvenPredicate() {
        return n -> n % 2 == 0;
    }
}
