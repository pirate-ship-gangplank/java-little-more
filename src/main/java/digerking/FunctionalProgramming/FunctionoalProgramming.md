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

