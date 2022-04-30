# Step 01. 함수형 프로그래밍 소개


> 함수형 프로그래밍 vs 객체지향 프로그래밍

- **객체지향 프로그래밍**

  - 매개변수에 객체를 넣고 메서드를 사용할 수 있다.
  

- **함수형 프로그래밍**
  - 매개변수에 함수를 보내고, 변수에 함수를 지정하고, 함수를 리턴 받을 수 있다.
  
  - 유명한 책인 클린 코드(Clean Code)의 저자 Robert C.Martin은 함수형 프로그래밍을 대입문이 없는 프로그래밍이라고 정의하였다.

    출처: https://mangkyu.tistory.com/111 [MangKyu's Diary]

---

# Step 06 함수형 프로그래밍 vs 구조적 프로그래밍 - 간단한 비교

    일반적인 리스트의 합을 구하는 방식

    private static int normalSum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

<br>

    함수형 프로그래밍을 이용한 합을 구하는 방식

    private static int functionalProgrammingSum(List<Integer> numbers) {

        int sum = numbers.stream()
                .reduce(0, (number1, number2) -> number1 + number2);

        return sum;
    }

.reduce(초기값, 연산) 의 형태로 사용된다.

초기값부터 시작해서, 각 원소를 차례로 순회하며 연산을 수행하고, 이전 연산의 결과를 다음 초기값으로 넘기면서 결과를 누적한다.

즉, 위의 예시는 0부터 시작하여, number1 + number2 라는 연산을 함수로써 매개변수로 사용하여 순차적으로 더해가는 것이다.

---

# Step 07. 함수형 프로그래밍 표기법 - 람다 표현식

> 함수형 프로그래밍은 함수에 일등 지위를 주는 것

- 함수가 다른 메서드의 매개변수로 전달될 수 있도록 허용하는 것

- 상태 변이가 없음

- 어떻게 할지가 아닌 무엇을 할지에 초점을 두어야 함

<br>

    private static int functionalProgrammingSum(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, (number1, number2) -> number1 + number2);
    }

위 예시에서, (number1, number2) -> number1 + number2
를 람다 표현식이라고 한다.

즉, 괄호 안에 있는 매개변수를 화살표 방향으로 넘겨 연산을 진행하고 결과를 돌려받는 함수 표현 축약형이라고 볼 수 있다.

### .stream()

> // List로부터 스트림을 생성 <br><br> 
> List<String> list = Arrays.asList("a", "b", "c"); Stream<String> listStream = list.stream();

> // 배열로부터 스트림을 생성 Stream<String> <br><br>
> stream = Stream.of("a", "b", "c"); //가변인자  <br><br>
> Stream<String> stream = Stream.of(new String[] {"a", "b", "c"}); <br><br>
> Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"}); <br><br>
> Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"}, 0, 3); //end범위 포함 x


> IntStream stream = IntStream.range(4, 10);

출처: https://mangkyu.tistory.com/114 [MangKyu's Diary]

위와 같이 List나 Array 뿐만 아니라 int long double 등 원시 자료형에 대한 특수한 Stream도 사용 할 수있다.

---

# Step 08. Stream 의 중간 연산 - Sort, Distinct, Filter, Map

    // 2.8 스트림 중간 연산 (sort)
    private static void middleOperationSorted(List<Integer> numbers) {
        numbers.stream().sorted().forEach(e -> System.out.println(e));
    }

    // 2.8 스트림 중간 연산 (distinct)
    // 정렬은 하지 않고 중복된 요소를 차례로 제거
    private static void middleOperationDistinct(List<Integer> numbers) {
        numbers.stream().distinct().forEach(e -> System.out.println(e));
    }

    // 2.8 스트림 중간 연산 (distinct)
    // 중복제거 및 정렬
    private static void middleOperationDistinctSorted(List<Integer> numbers) {
        numbers.stream().distinct().sorted().forEach(e -> System.out.println(e));
    }

> 중간연산(Sort, Distinct, Filter, Map) 은 갯수 상관없이 붙여 쓸 수 있음

하지만 종단연산은 하나임 -> 하나의 값으로 축소하기 때문


---

# Step 09. 연습문제

    // 2.9 연습문제
    // 1 ~ 10 까지 제곱한 수들을 출력
    private static void Ex1() {
        IntStream.range(1, 11).map(e -> e * e).forEach(e-> System.out.println(e));
    }

    // 2.9 연습문제
    // 문자열 소문자로 출력하기
    private static void Ex2() {
        List.of("Apple", "Ant", "Bat")
                .stream()
                .map(s -> s.toLowerCase())
                .forEach(s -> System.out.println(s));
    }

    // 2.9 연습문제
    // 문자열 길이 출력하기
    private static void Ex3() {
        List.of("Apple", "Ant", "Bat")
                .stream()
                .map(s -> s.length())
                .forEach(s -> System.out.println(s));
    } 

---
# Additional For-loop vs Stream

### For-loop(반복문)을 이용한 배열 접근 후 최댓값 도출
  
    int[] a = ints;
    
    int e = ints.length;
    
    int m = Integer.MIN_VALUE;
    
    for(int i=0; i < e; i++)
    if(a[i] > m) m = a[i];



### Stream을 이용한 배열 접근 후 최댓값 도출

    int m = Arrays.stream(ints)
    .reduce(Integer.MIN_VALUE, Math::max);

<br>

> 두 코드를 동작시킨 환경에서의 성능차이는 다음과 같다.

int-array, for-loop : 0.36 ms

int-array, seq. stream: 5.35 ms


<br>
단편적인 예시이긴 하지만 일반 배열이아닌 ArrayList에서도 반복문이 더 우세한 결과를 가지고 있었다.

<br>

https://pamyferret.tistory.com/49

또한 위 블로그에 따르면

For-loop이 더 빠른 이유를 설명해놓았는데


## - for문은 단순 인덱스 기반이다. 
####  - for문은 단순 인덱스 기반으로 도는 반복문으로 메모리 접근이기 때문에 Stream에 비해 빠르고 오버헤드도 없다. 
####  - stream의 경우는 JVM이 이것저것 처리해줘야하는 것들이 많아 실행 시 느릴 수 밖에 없다.


## - for문은 컴파일러가 최적화를 시킨다. 
####  - stream은 java 8부터 지원한 것이고 for문은 그보다 훨씬 오래전부터 계속 사용해왔다. 
####  - 그만큼 사용되는 컴파일러는 오래 사용된 for문에 대한 처리가 되어 있어 for문의 경우 미리 최적화를 시킬 수 있지만, 
####  - stream의 경우 신생(?)인 만큼 정보가 없어 for문과 같은 정교한 최적화를 수행할 수 없다.


<br>
이 두가지 근거(?)가 설득력 있게 다가왔다.

---

# Step 12. Optional



  

---