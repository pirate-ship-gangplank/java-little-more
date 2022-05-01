# 함수형 프로그래밍 과 람다식

1. 함수형 프로그래밍과 람다식

- 자바는 객체지향 언어로 클래스를 만들고 클래스안의 메소드에
  기능을 구현하고 메소드를 호출한다.
- 프로그래밍 중 클래스 없이 함수를 구현하고 호출하는 방법이다.
- 자바는 자바8 부터 함수형 프로그래밍을 지원한다.
- 자바에서 제공하는 함수형 프로그래밍 방식을 람다식(Lambda expression)이라 한다.

2. 람다식 구현하기

- 람다식은 함수 이름이 없는 익명함수를 만드는 것이다.
- (매개변수) -> {실행문;} 으로 표현 한다.

int add(int x, int y) {
return x + y => (int x, int y) -> {return x+y;}
}

3. 람다식 문법
   (1) 매개변수 자료형과 괄호 생략

- 람다식에서는 매개변수 자료형을 생략할 수 있다.
- 매개변수가 하나인경우는 괄호도 생략할 수 있다.
  str -> {System.out.println(str);}
- 매개변수가 두개인 경우는 괄호를 생략할 수 없다.

(2) 중괄호 생략

- 중괄호 안의 구현 부분이 한 문장인 경우 중괄호를 생략할 수 있다.
  str -> System.out.println(str);
- 중괄호 안의 구현부분이 한 문장이라도 return문이 있으면 중괄호를 생략할 없다.

(3) return 생략

- 중괄호 안의 구현 부분이 return문만 있다면 중괄호와 return을 모두 생략하고
  식만 쓴다.
  (x, y) -> x + y; -> {return x + y;}
  str -> str.length(); ->{return str.length();}

4. 람다식 사용
   (1) 함수형 인터페이스

- 자바는 메서드를 단독으로 생성 할 수 없다
- 람다식을 구현할 추상 메소드 선언
- MyNumber 인터페이스에 getMax() 추상메소드 선언

> > > MyNumber.java

package lambda;

@FunctionalInterface
public interface MyNumber {

    int getMax(int num1, int num2);

}

(2) 람다식 구현과 호출

- MyNumber 인터페이스형 변수(max)에 람다식을 대입한다.
- MyNumber 타입인 max의 getMax()를 호출 한다.

> > > TestMyNumber.java

package lambda;

public class TestMyNumber {

    public static void main(String[] args) {
        MyNumber max = (x, y)->(x>= y)? x:y; // 람다식을 인터페이스 자료형 max 변수에 대입

        System.out.println(max.getMax(10, 20));// 인터페이스 자료형 변수로 함수 호출

    }

}

5. 함수형 인터페이스

- 람다식 구현을 위해 함수형 인터페이스를 만들고, 인터페이스에 람다식 메서드를 대입한다.
- 람다식은 하나의 메서드를 구현하여 인터페이스형 변수에 대입된다.
- 함수형 인터페이스는 두 개 이상의 메서드를 가질 수 없다.

(1) @FunctionalInterface 애노테이션

- 실수로 메서드를 하나 이상 선언하면 오류가 나도록 한다.
- 함수형 인터페이스를 명시적으로 표현한다.
- 애노테이션을 생략해도 된다.

package lambda;

@FunctionalInterface
public interface MyNumber {

    int getMax(int num1, int num2);

    int getAdd(int num1, int num2);

}

6. 객체지향 프로그래밍 방식과 람다식 비교

- 람다식을 사용하면 기존 방식보다 간결한 코드를 구현할 수 있다.
- 메소드의 구현부를 클래스에 만들고, 이를 다시 인스턴스로 생성하고 호출하는
  코드가 줄어들기 때문이다.

- 공통 인터페이스 생성

> > > StringConcat .java
> > > package lambda;

public interface StringConcat {

     void makeString(String s1, String s2);

}

(1) 클래스에서 인터페이스 구현

- 인터페이스를 구현한 클래스 생성후 추상메소드 재정의한다.
- 클래스를 테스트하는 프로그램을 작성한다.

> > > StringConCatImpl.java
> > > package lambda;

public class StringConCatImpl implements StringConcat{

@Override
public void makeString(String s1, String s2) {
System.out.println( s1 + "," + s2 );
}
}

> > > TestStringConcat.java
> > > package lambda;

public class TestStringConcat {

    public static void main(String[] args) {

        String s1 = "Hello";
        String s2 = "World";
        StringConCatImpl concat1 = new StringConCatImpl();
        concat1.makeString(s1, s2);

}
}

(2) 람다식으로 인터페이스 구현

- 람다식으로 인터페이스 구현시 클래스를 따로 생성할 필요없이 바로 메서드를
  구현한다.

> > > TestStringConcat.java

package lambda;

public class TestStringConcat {

    public static void main(String[] args) {

        String s1 = "Hello";
        String s2 = "World";

        StringConcat concat2 = (s, v)->System.out.println(s + "," + v );

        concat2.makeString(s1, s2);

}

}

7. 익명 객체를 생성하는 람다식

- 람다식은 객체 없이 인터페이스의 구현만으로 메서드를 호출한다.
- 람다식으로 메서드를 구현해서 호출하면 컴퓨터 내부적으로 아래처럼 익명 클래스가
  생성되고 이를 통해 익명 객체가 생성되는 것이다.

StringConcat concat3 = new StringConcat() {

@Override
public void makeString(String s1, String s2) {

     System.out.println( s1 + "," + s2 );

}
};

8. 함수를 변수처럼 사용하는 람다식

- 람다식을 이용하면 구현된 함수를 변수처럼 사용할 수 있다.
- 람다식으로 구현된 메서드도 변수에 대입하여 사용할 수 있고,
  매개변수로 전달하고 반환할 수 있다.

(1) 인터페이스형 변수에 람다식 대입하기

package lambda;

interface PrintString{

    void showString(String str);

}

public class TestLambda {

    public static void main(String[] args) {

        PrintString lambdaStr = s->System.out.println(s);  //람다식을 변수에 대입
        lambdaStr.showString("hello lambda_1");


}
}

(2) 매개변수로 전달하는 람다식

- 람다식을 변수에 대입하면 이를 매개변수로 전달할 수 있다.
- 이 때 전달되는 매개변수의 자료형은 인터페이스형이다.

package lambda;

interface PrintString{

    void showString(String str);

}

public class TestLambda {

    public static void main(String[] args) {

        PrintString lambdaStr = s->System.out.println(s);  //람다식을 변수에 대입
        lambdaStr.showString("hello lambda_1");

        showMyString(lambdaStr);                          //메서드 매개변수로 전달

    }

    public static void showMyString(PrintString p) {
        p.showString("hello lambda_2");
    }

}

(3) 반환 값으로 쓰이는 람다식

- 메서드의 반환형을 람다식의 인터페이스형으로 선언하면 구현한 람다식을 반환할
  수 있다.

package lambda;

interface PrintString{

    void showString(String str);

}

public class TestLambda {

    public static void main(String[] args) {

        PrintString lambdaStr = s->System.out.println(s);  //람다식을 변수에 대입
        lambdaStr.showString("hello lambda_1");

        showMyString(lambdaStr);                          //메서드 매개변수로 전달

        PrintString reStr = returnString();
        reStr.showString("hello ");

    }

    public static void showMyString(PrintString p) {
        p.showString("hello lambda_2");
    }

    public static PrintString returnString() {         //반환 값으로 사용
        return s->System.out.println(s + "world");
    }

}

[03] 스트림 클래스

- 배열의 요소를 특정 기준에 따라 정렬(sorting)하거나, 요소 중 특정 값을 제외하고 출력
  하는(filter)기능처럼 여러 자료의 처리에 대한 기능을 구현해 놓은 클래스가 스트림이다.
- 자료가 모여 있는 배열이나 컬렉션 또는 특정 범위 안에 있는 일련의 숫자를 처리하는
  기능이 미리 구현되어 있어서 프로그램 코드가 훨씬 간결해지고 일관성 있게 다룰 수
  있다.

int[] arr = {1,2,3,4,5};

for(int i=0;i<arr.length; i++){  
 System.out.println(arr[i]); => Array.stream(arr).forEach(n->System.out.println(n));
}

1. 스트림 연산

- 스트림의 종류는 중간 연산, 최종 연산이 있다.
- 중간 연산은 자료를 거르거나 변경하여 또 다른 자료를 내부적으로 생성한다.
- 최종 연산은 생성된 내부 자료를 소모해 가면서 연산을 수행한다.
- 최종 연산은 마지막에 한 번만 호출된다.

(1) 중간 연산 - filter(), map()

- filter()는 조건을 넣고 그 조건에 맞는 참인 경우만 추출하는 경우에 사용한다.
- 문자열 배열에서 문자열의 길이가 5이상인 경우만 출력하는 코드이다.

  sList.stream().filter(s -> s.length() >5).forEach(s -> System.out.println(s));

  스트림생성 중간 연산 최종 연산

- map()은 클래스가 가진 자료 중 이름만 출력하는 경우에 사용한다.
- 고객 클래스에서 고객이름만 가져와서 출력한다.
- map()은 요소들을 순회하면서 다른 형식으로 변환하기도 한다.

customerList.stream().map(c -> c.getName()).forEach(s -> System.out.println(s));

        스트림생성           중간 연산               최종 연산

(2) 최종 연산 - forEach(), count(), sum(), reduce()

- 최종 연산은 스트림의 자료를 소모하면서 연산을 수행하기 때문에 최종 연산이 수행
  되고 나면 해당 스트림은 더 이상 사용할 수 없다.
- 최종 연산은 결과를 만드는 데 주로 사용한다.

2. 스트림 생성하고 사용
   (1) 정수 배열에 스트림 생성하고 사용

- 스트림을 활용해 정수 배열을 개수와 합을 출력한다.

> > > IntArrayTest .java
> > > package stream;

import java.util.Arrays;

public class IntArrayTest {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};

        int sumVal = Arrays.stream(arr).sum();
        int count = (int) Arrays.stream(arr).count();

        System.out.println(sumVal);
        System.out.println(count);

    }

}

(2) Collection에서 스트림 생성하고 사용

- ArrayList에 스트림 생성하고 활용한다.
- Collection인터페이스의 메소드중 stream()를 사용하면 제네릭형으로 사용해
  자료형을 명시할 수 있다.

  Stream<String> stream = sList.stream();

- 이렇게 생성된 스트림은 내부적으로 ArrayList의 모든요소를 가지고 있다.
- ArrayList에 저장된 이름을 정렬하여 그 결과를 출력해 본다.

> > > ArrayListStreamTest.java
> > > package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListStreamTest {

    public static void main(String[] args) {

        List<String> sList = new ArrayList<String>();
        sList.add("Tomas");
        sList.add("Edward");
        sList.add("Jack");

        Stream<String> stream = sList.stream();
        stream.forEach(s->System.out.print(s + " "));
        System.out.println();

        sList.stream().sorted().forEach(s->System.out.print(s+ " ")); //스트림 새로 생성하여 정렬하여 출력한다.

    }

}

3. 스트림의 특징
   (1) 자료의 대상과 관계없이 동일한 연산을 수행한다.
   (2) 한 번 생성하고 사용한 스트림은 재사용할 수 없다.
   (3) 스트림의 연산은 기존 자료를 변경하지 않는다.
   (4) 스트림의 연산은 중간 연산과 최종 연산이 있다.

- 스트림 중간 연산은 여러 개가 적용될 수 있고, 최종 연산은 맨 마지막에 한 번 적용된다.
- 자료를 정렬하거나 검색하는 중간 연산이 호출되어도 최종 연산이 호출되지 않으면
  정렬이나 검색한 결과를 가져올 수 없다. 이를 지연 연산(lazy evaluation) 이라 한다.
