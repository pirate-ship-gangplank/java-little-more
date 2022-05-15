##스터디 2주차 정리

#함수형 프로그래밍

함수형 프로그래밍은 파라미터로 함수를 넘기는 기법을 말한다.


```
static void printWhitEP(List<String> list) {
    list.stream().forEach(
            element -> System.out.println(element)
    );
}
```
함수형 프로그래밍은 위 코드 처럼 행위를 파라미터로 넘길 수 있다.

위 파라마터의 문법은 람다이다.

함수형 프로그래밍으로 아래와 같은 활용이 가능하다.    
```
static void printBasicWithFiltering(List<String> list) {
    for (String string : list) {
        if (string.endsWith("at"))
            System.out.println(string);
    }
}
```
위와 같이 리스트에 있는 값을 원하는 값만 출력하고 싶을때
```
static void printWhitEPWithFiltering(List<String> list) {
    list.stream()
            .filter(element -> element.endsWith("at"))
            .forEach(element -> System.out.println(element)
            );
}
```
위와 같은 코드로 활용 가능하다.
때문에 많은 양의 논리를 루프 없이 구현할 수 있게되는것이다.

람다에서 파라미터가 여러개 일 경우에는 ()안에 없이 작성할 수 있다.

하지만 파라미터가 여러개라면 아래와 같이 구현해야한다. 
```
( (a,b) -> System.out.println(a+b) )
```

함수형 프로그래밍에서는 변수 변이를 피한다.
즉 값이 변하는 변수를 굳이 넣지 않는다.

함수형 프로그래밍은 어떻게 하는지 알려줄 필요 없이 뭘 할지만 명시해줄 수 있다.
아래 코드가 그 예시이다.
```
int sum = 0;
for(Integer num : numbers){
    sum += num;
}
```
위 코드처럼 로직으로 값을 구하는걸 어떻게 하는지도 보여준다

```
List<Integer> numbers = List.of(4, 6, 8, 13, 3, 15);
final Integer reduce = numbers.stream()
        .filter(num -> num%2==1)
        .reduce(0, (num1, num2) -> num1 + num2);
```

하지만 위 로직은 값을 구하는 과정의 로직은 없고 무엇을 할지만 명시해줄 수 있다.

함수형 프로그래밍은 뭘 할지만 알면 된다.

이게 함수형 프로그래밍의 특징이다.


##람다

람다 표현식은 행위를 하고 그의 맞는 리턴값을 파라미터로 넘겨줄 수 있다.

비슷한 예제로는 익명 클래스가 있다.

람다의 작성 방법으론 위 예제에서 작성했다시피

```파라미터가 한개일 경우 a - > 처럼 표현이 가능하다.```

```파라미터는 여러개 일 경우 (a, b) -> 와 같이 표햔 해주어야 한다.```

```로직이 한개일 경우 () -> abcd 처럼 {} 괄호를 써주지 않아도 된다.``` 

```
로직이 여러개일 경우 () -> {
    a
    b
    c
}
```
와 같이 {} 괄호를 써줘야 한다 

##스트림
스트림은 객체의 소스라고 할 수 있다.

일반적으로 스트림에 관해서는 중간 연산과 종단 연산이 두가지가 있다.

중간 연산이란 요소를 받고 요소 스트림을 받아 이어 나갈 수 있다.

종단 연산이란 요소를 처리하고 소모하는것이다. 
```
list.stream()
        .filter(element -> element.endsWith("at"))
        .forEach(element -> System.out.println(element)
        );
```

위 코드의 filter 메서드가 종간 연산이라고 할 수 있다.

메서드를 사용한 이후에 반환되는 값이 스트림이기 때문에 계속 스트림을 이어나갈 수 있다.


위코드에서 forEach 메서드가 종단 연산이다 스트림을 받아 요소를 처리하고 리턴 값이 스트림이 아니기 때문에
스트림을 이어나갈 수 없다.

즉, 종간 연산에서 원하는 값을 만들고 종단 연산에서 원하는 값을 추출하는 과정이 스트림의 흐름이다.


###종간연산 종류
sorted(정렬) , distinct(중복 제거) ,map(값의 재정의)



###옵셔널
옵셔널은 널을 리턴하는걸 방지하기 위해 만들어진 클래스이다. 


##함수형 인터페이스의 이면

함수형 인터페이스는 인터페이스 내에서 메서드를 한개만 정의한것이다.

이를 명시하기 위해 @FunctionInterFace 라는. 함수형 인터페이스 어노테이션을 제공한다.

해당 어노테이션은 함수형 인터페이스란 개념과 맞게 여러개의 정의할 수 없다

단, default 접근제어지시자를 사용하거나 static을 사용하여 정의하는건 예외적이다.

이런 함수형 인터페이스의 구현으로 함수를 변수에 저장하는것도 가능하다.


##메서드 래퍼런스
메서드 래퍼런스는 람다 표현식이 하나의 메소드만 호출하는 경우 해당 람다 표현식에서

불필요한 매개변수를 제거하고 사용할 수 있게 해준다.

```
static void printWhitEP(List<String> list) {
    list.stream().forEach(
            element -> System.out.println(element)
    );
}
```
위에 코드를 아래 코드와 같이 보기 쉽게 변경이 가능하다.

```
static void printWhitEP(List<String> list) {
    list.stream().forEach(
            System.out::println
    );
}
```

###메서드 래퍼런스의 종류
스태틱 메소드 참조 = 타입::스태틱메소드
특정객체의 인스턴스 메소드 참조 = 객체래퍼런스:인스턴스메소드
생성자 참조 = 타입::new
임의 객체의 인스턴스 메소드 참조 = 타입::인스턴스메소드


##마무리

함수형 프로그램은 로직을 보여주지 않고 어떤 행위를 할지만 나타낼 수 있어 코드를 직관적으로 볼 수 있다.

하지만 그만큼 굉장히 어렵고 배워야할 양이 굉장히 방대하다.
