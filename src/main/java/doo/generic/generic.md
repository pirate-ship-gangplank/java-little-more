## 주차

자바 조금 더 알기 - 1주차 

## 제목

자바 프로그래밍의 제네릭

## 주제

제네릭에 대해 이해하고, 제네릭을 사용한다.

## 내용

## 제네릭 소개

1. 다양한 타입(종류 매개변수)을 받는 클래스를 만들 수 있다.

```java
public class MyCustomList<T> {

  private List<T> list = new ArrayList<>();

  public void addElement(T element) {
    list.add(element);
  }

  public void removeElement(T element) {
    list.remove(element);
  }

  public T get(int index) {  // T를 반환
    return list.get(index);
  }
}
```

- T라는 종류 매개변수를 사용하여 어떤 타입이든 받을 수 있지만, 한번 T가 결정되고 나면 다른 타입은 들어오지 못하게 된다. → 강력한 타입 체킹을 제공
- T 자체를 반환하여 사용할 수도 있다.

```java
public class GenericMain {
  public static void main(String[] args) {
    MyCustomList<String> stringList = new MyCustomList<>();
    stringList.addElement("string1");
    stringList.addElement("string2");
    stringList.get(0);

    MyCustomList<Integer> intList = new MyCustomList<>();
    intList.addElement(1);
    intList.addElement(2);
    intList.get(0);
  }
}
```

- 상황에 따라 필요한 종류 매개변수를 사용하여 유연하게 클래스를 사용할 수 있다.

### 제한점과 확장

```java
public class MyCustomList<T extends Number> { // Number 클래스를 확장한 타입만 사용 가능

  private List<T> list = new ArrayList<>(); // Number의 하위 클래스 요소로만 이루어진다.

  public void addElement(T element) {
    element.doubleValue(); // Number 클래스의 메서드를 사용할 수 있다.
    element.intValue();
    list.add(element);
  }
  ...
}
```

- Number 혹은 그 하위 클래스만 있음을 보장한다.
- Number 타입만 담을 의도를 가지고 만들 수 있다.
→ Number가 아닌 타입이 들어오는 것을 원치않는 경우이며, Number가 아닌 타입이 들어오려는 경우엔 컴파일 에러를 발생시킨다.

### 제네릭 메서드

```java
public class MyCustomList<T extends Number> {

  private List<T> list = new ArrayList<>();

  ...

  public static <A> A returnValue(A value) {
    return value;
  }

}
```

- 클래스에서 사용하는 제네릭 타입과 상관없이 사용 가능하다.
- 제네릭 메서드에 제한점(extends)을 두려면, `<A extends Number>` 로 변경하면 된다.

```java
public <A extends Number> A returnValue(Integer value) {
  return value;  // Required Type : A, but Provided : Integer. ERROR
}

public <A extends Number> A returnValue(Integer value) {
  return (A)value; // cast to type A. OK
}
```

- Number의 하위 클래스 Integer를 바로 반환하려하면, 에러가 발생한다.
→ 전달된 value를 `(A)`를 통해 타입 캐스트하면 반환된다.

### 와일드 카드

`?` 문자를 통해 표현한다.

상한 경계 와일드 카드, 하한 경계 와일드 카드가 있다.

와일드 카드는 지역 변수와 멤버 변수에 모두 사용 가능하다.