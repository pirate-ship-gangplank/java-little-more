package peubel.제네릭;

import peubel.제네릭.MyCustomList;

import java.util.ArrayList;
import java.util.List;

public class GenericsRunner {


    static <X> X doubleValue(X value){
        return value;
    }


    static <X extends List> void  duplicate(X list){
        list.addAll(list);
    }


    static Double sumOfNumberList(List<? extends  Number> numbers){
        double sum = 0.0;
        for(Number number : numbers){
            sum += number.doubleValue();
        }
        return sum;

    }

    public static void main(String[] args) {

        System.out.println(sumOfNumberList(List.of(1,2,3,4,5)));
        System.out.println(sumOfNumberList(List.of(1.1,2.1,3.1,4.1,5.1)));
        System.out.println(sumOfNumberList(List.of(1L,2L,3L,4L,5L)));


        final String s = doubleValue(new String());
        final Integer integer1 = doubleValue(5);
        final ArrayList<Object> objects = doubleValue(new ArrayList<>());


        final ArrayList<Integer> arrayList = new ArrayList<>(List.of(1,2,3));
        duplicate(arrayList);
        System.out.println(arrayList);



        MyCustomList<Long> list = new MyCustomList();
        list.addElement(5L);
        list.addElement(4L);

        Long value  = list.get(0);

        System.out.println(value);
        System.out.println(list);

        MyCustomList<Integer> list2 = new MyCustomList();
        list2.addElement(5);
        list2.addElement(10);

        final Integer integer = list2.get(0);


        System.out.println(integer);
        System.out.println(list2);

    }


}
