함수형 프로그래밍 : 함수에 일급 시민을 적용시키는 것

## 일급 시민 (First Class Citizen) 특징

1. 변수에 할당할 수 있다.
2. 인자로 전달할 수 있다.
3. 반환 값으로 전달할 수 있다.

## stream

데이터의 흐름

```java
List<Integer> numbers = List.of(1,3,4,6,8,11);
```

### 중간 연산

- sort : 전달한 Comparator에 따라 데이터를 정렬한다.

```java
numbers.stream()
    .sorted((n1, n2) -> Integer.compare(n1, n2))
```

- distinct : 중복 데이터를 걸러낸다

```java
numbers.stream()
    .distinct()  // 데이터 중에 중복되는 값이 있으면 걸러낸다.
```

- filter : stream 구성 데이터 중 조건에 따라 일부를 걸러낸다.

```java
numbers.stream()
   .filter(number -> number % 2 == 0) // 짝수만 통과한다.
```

- map : 스트림 데이터를 원하는 형태로 변환한다.

```java
numbers.stream()
    .map(n -> n * n) // 데이터를 제곱수로 변환
```

### 최종 연산

- reduce : 스트림 요소를 줄여나가면서 연산을 수행하고 최종 결과를 반환한다.

```java
numbers.stream()
    .reduce(0, (n1, n2) -> n1 + n2);  // 초기값 0, 수행할 연산을 전달한다.
```

- max : 최대값을 구한다.

```java
numbers.stream()
    .max((n1, n2) -> Integer.compare(n1, n2))
```

- min : 최소값을 구한다.

```java
numbers.stream()
    .min((n1, n2) -> Integer.compare(n1, n2))
```

- collect : 구현된 방법에 따라 스트림 요소를 수집한다.

```java
numbers.stream()
     .sorted((n1, n2) -> Integer.compare(n1, n2))
     .collect(Collectors.toList());
```

### 함수형 프로그래밍 vs 구조적 프로그래밍

- 루프 문 - mutation 가변

```jsx
public Integer listSum(List<Integer> numbers) {
  int sum = 0;
  for (Integer num : numbers) {
    sum += num;  // sum에 대해 값의 변경이 존재한다.
  }
  return sum;
}
```

- stream

```jsx
public Integer listSum(List<Intger> numbers) {
  return numbers.stream()
          .reduce(0, (num1, num2) -> num1 + num2);  // 지역변수에 대해 신경쓰지 않고, 그저 더하는 '로직'에만 집중
}
```

1. 함수형 프로그래밍에서는 변수 변이를 피한다. 
→ 굳이 값이 변하는 변수를 넣지 않는다.
2. 무엇을 할지에 집중한다.
→ 어떻게 하는지가 아닌 무엇을 할지에 집중한다. 결과가 나오는 과정(로직)에 대해선 관심을 갖지 않는다.

### 람다 함수

익명함수를 지칭하는 용어.

- 함수 생성의 단축형
- 여러 줄의 코드를 가질 수 있다.

```java
// 매개변수가 여러개인 경우
(매개변수1, 매개변수2) -> {
  //로직
  return ..;
}

----------------------------------------------------------------
// 매개변수가 1개 & return 값만 있는 경우
매개변수 -> ""
```

### 함수형 인터페이스

- Predicate

```java
@FunctionalInterface
public interface Predicate<T> {
  boolean test(T t);
}
```

- Consumer

```java
@FunctionalInterface
public interface Consumer<T> {
  void accept(T t);
}
```

- Function

```java
@FunctionalInterface
public interface Function<T, R> {
  R apply(T t);
}
```

### 메서드 참조

람다 표현식에서 불필요한 매개변수를 제거하고 사용할 수 있도록 해주는 기능

```java
numbers.stream()
        .sorted((n1, n2) -> Integer.compare(n1, n2))

----------------------------------------------------------------
numbers.stream()
        .sorted(Integer::compare)
```

- 정적 메서드 뿐만 아니라 인스턴스 메서드도 메서드 참조로 작성이 가능하다.