package LHS11.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GenericsRunner {

    static <X>  X doubleValue(X value){
        return value;
    }

    static <X extends List> void duplicate(X list){
        list.addAll(list);
    }

    static double sumOfNumberList(List<? extends Number> numbers){
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

    static void addACoupleOfValues(List<? super Number> numbers){
        numbers.add(1);
        numbers.add(1L);
        numbers.add(1.0f);
        numbers.add(1.0);
    }

    public static void main(String[] args) {

        // 일반적인 ArrayList보다 더 기능이 있는 것을 만들어야함 -> 데이터 종류에 묶이지 않는 ArrayList를 만들고 싶다 -> Generics을 이용하면 된다.

        // 제네릭의 종류만 정의하면 된다.

        // doubleValue 메소드 - 자료형에 상관 없이 return
        String value1 = doubleValue(new String());
        Integer number1 = doubleValue(Integer.valueOf(5));
        ArrayList list1 = doubleValue(new ArrayList());

        // duplicate 메소드
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3));
        duplicate(numbers);
        System.out.println("v = " + v);
        System.out.println("numbers = " + numbers);

        // addACoupleOfValues 메소드 - 자료형에 상관없이 list 안에 담아줌 (하한 경계)
        List emptyList = new ArrayList<Number>();
        addACoupleOfValues(emptyList);
        System.out.println("emptyList = " + emptyList);

        // sumOfNumberList 메소드 - 리스트 안의 원소들 자료형 상관 없이 더해줌 (상한 경계)
        System.out.println(sumOfNumberList(List.of(1,2,3,4,5))); // int
        System.out.println(sumOfNumberList(List.of(1.1,2.1,3.1,4.1,5.1))); // double
        System.out.println(sumOfNumberList(List.of(1l,2l,3l,4l,5l))); // long

        //
        MyCustomList<String> list = new MyCustomList<String>();
        list.addElement("Element 1");
        list.addElement("Element 2");
        String value = list.get(0);
        System.out.println(value);

        MyCustomList<Integer> list2 = new MyCustomList<Integer>();
        list2.addElement(1);
        list2.addElement(Integer.valueOf(5));
        Integer number = list2.get(0);
        System.out.println(number);

    }
}
