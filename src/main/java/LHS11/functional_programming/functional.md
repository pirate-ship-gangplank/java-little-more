매개변수로서 함수
>
```
private static void printBasic(List<String> list) {
        for (String string : list) {
            System.out.println(string);
        }
    }
```
```
private static void printWithFP(List<String> list) {
        list.stream().forEach( 
                element -> System.out.println(element) // 람다식
        );
    }
```
위 두 메소드는 같은 결과를 도출한다.
- stream은 값의 흐름(list 안의 원소들)
- foreach는 stream에서 데이터가 아닌 흐름의 각 요소마다 실행될 함수를 보내는 것
- () -> => 람다식 (불필요한 코드를 줄이고, 가독성을 높임)
- element에 list안의 원소들이 들어가고 System.out.println(element)에서 시행

함수형 프로그래밍 - 필터 (filter)
>
```
private static void printBasicWithFiltering(List<String> list) {
        for (String string : list) {
            if(string.endsWith("at"))
                System.out.println(string);
        }
    }
```
```
private static void printWithFPWithFiltering(List<String> list) {
        list.stream()
                .filter( 
                        element -> element.endsWith("at")
                )
                .forEach( 
                        element -> System.out.println(element) 
                );
    }
```
위 두 메소드는 문자열의 끝이 at으로 끝나는 문자열을 구하는 메소드
- filter 안에 조건문이 true 일 때만 forEach 수행
  (filter 안의 조건문이 만족시키는 것들만 골라내는 것)
- 함수형 프로그래밍을 함으로써 많은 양의 logic을 반복문 없이 구현 가능

함수형 프로그래밍 - 모음 (reduce)
>
```
private static Integer normalSum(List<Integer> numbers){
        int sum=0;
        for (Integer number : numbers) {
            sum+=number;
        }
        return sum;
    }
```
```
private static Integer fpSum(List<Integer> numbers) {
        return numbers.stream() 
                .reduce(
                        0, 
                        (num1, num2) -> num1 + num2 
                );
    }
ex) 4, 6, 8, 13
  num1=0, num2=4 -> num1=4
  num1=4, num2=6 -> num1=10
  num1=10, num2=8 -> num1=18
  num1=18, num2=13 -> num1=31
```
위 두 메소드는 리스트들의 합을 구하는 메소드
- reduce(n1,람다식) => 첫 번째 매개변수는 시작값, 두 번째 매개변수는 함수 정의
- 두 개의 매개변수가 있을 때는 괄호가 꼭 필요
- 중간에 filter를 이용해서 홀수들의 합도 구할 수 있음
- 함수형 프로그래밍을 이용하면 지역변수나 반복문을 사용할 필요X
  (변수 변이를 피함)
```
numbers.stream()
                .reduce(
                        0,
                        (num1, num2) -> {
                            System.out.println(num1 + " " + num2);
                            return num1 + num2;
                        } // <- 람다 표현식 (단축 메소드) 
  						 );
```

중간 연산과 종산 연산
> - 중간 연산은 요소를 받고, 요소 스트림을 받아 스트림 안의 숫자를 reduce하거나 다른 종류의 요소로 map 가능
- 중간 연산의 결과는 또 다른 스트림이고 종단 연산에서는 요소를 처리하고 소모
- 중간 연산의 예
    - fillter, map, sort, distinct 등

  중간 연산(Sort, Distinct, Filter, Map)
>
```
numbers=[3,5,7,8,3,7,11]
numbers.streaminct().sorted().map(e->e*e)
	.forEach(e->System.out.println(e));
=> 9 25 49 64 121
```
- distinct() : 중복값 제거
- sorted() : 정렬
- map() : 새로운 값으로 mapping

최대값 연산
> - 스트림 안의 max 메소드가 적용되지 않기 때문에 비교자 인터페이스를 구현해야함
``````
코드를 입력하세요
```
	Integer max = List.of(23, 45, 67, 34).stream()
                .max((n1, n2) -> Integer.compare(n1, n2))
                .get();
```

 	
    
    
collect
> ```
List.of(23,12,34,53).stream().filter(e->e%2==1)
	.collect(Collectors.toList())
 ```
 ```
IntStream.range(1,11).map(e->e*e)
	.boxed()
	.collect(Collectors.toList())
```

자바의 Optional 클래스
> 
```
List.of(23,45,57).stream.filter(n -> n%2==0)
	.max( (n1,n2) -> Integer.compare(n1,n2))
=> Optional[12]
```
- Optional은 null값을 반환하지 않기 위해 사용

함수형 인터페이스
> 
```
 List.of(23, 43, 34, 45,36,48).stream()
                .filter(n->n%2==0)
                .map(n->n*n)
                .forEach(e-> System.out.println(e));
```
#### 여기서 filter가 어떻게 적용이 되는가? => 함수형 인터페이스(Prediacate 인터페이스) 덕분에 가능
```
- Stream<T> filter(Predicate<? super T> predicate);
- boolean test(T t)
```
```
class EvenNumberPredicate implements Predicate<Integer>{
    @Override
    public boolean test(Integer number) {
        return number%2==0;
    }
}
```
위의 메소드 number에 stream 요소들이 들어가게 된다.
- 필터링 후에 메소드에 값을 집어넣음
- Predicate => 입력값을 보고 참인지 거짓인지 판단하여 그 값을 반환
#### forEach는 어떻게 적용이 되는가?
```
- Consumer<? super T> action
- void accept(T t)
- Consumer는 허용 메소드
```
```
class SystemOutConsumer implements Consumer<Integer>{
    @Override
    public void accept(Integer number) {
        System.out.println(number);
    }
}
```
- forEach() 안의 정의가 Consumer 인터페이스 안의 accept 메소드가 정의가 된다
- Consumer => 사용자가 어떤 값을 입력해 주고 그에 대한 소비를 진행(어떤 값도 반환 X)
#### Map은 어떻게 적용이 되는가?
```
- <R> Stream<R> map(Function<? super T, ? extends R> mapper;
- R apply (T t)
```
```
class NumberSquareMapper implements Function<Integer,Integer>{
    @Override
    public Integer apply(Integer number) {
        return number * number;
    }
}
```
- Function=> 함수형 인터페이스로 입력값을 얻고 어떤 값을 출력해내는 인터페이스를 뜻함
- 입력하면 출력값을 도출

메소드 레퍼런스를 통한 함수형 프로그래밍 코드 축약
> ```
 List.of("Ant", "Bat","Cat","Dog","Elephant").stream()
                .map(String::length)
                .forEach(MethodReferencesRunner::print);
                // .forEach(System.out::println) 와 결과 동일
                // class::method
 MethodReferencesRunner 클래스 안에 print 메소드를 생성
```
```
 public static void print(Integer number){
        System.out.println(number);
    }
```
- class :: method 형식으로 축약 가능 
메소드 참조 사용하는 이유=> 코드의 가독성을 높이기 위함