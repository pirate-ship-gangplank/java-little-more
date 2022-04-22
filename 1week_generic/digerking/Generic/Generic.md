# Step 01. 제네릭 소개 - 제네릭은 왜 필요한가?

<br>
    
## 제네릭을 사용하지 않을 때
### MyCustomList.java

    public class MyCustomList {
        public void addElement(T element) {
            list.add(element);
        }
    }

위와 같은 메서드를 사용하기 위해서는 String 타입을 매개변수로 꼭 넣어주어야한다.

만약 String타입이 아니라, Integer, Boolean 등 다른 자료형이 접근하면 오류가 생기게 된다.

---

# Step 02. 사용자 정의 리스트를 위한 제네릭 구현

## 제네릭을 사용할 때
### MyCustomList.java

    public class MyCustomList<T> {
        public void addElement(T element) {
            list.add(element);
            }
        }


위와 같이 제네릭 타입을 매개변수로 받는 메서드를 만들면 **어떤 타입이 와도 관계없이 메서드를 사용**할 수 있다.

이때, **제네릭을 선언한 클래스**에서 모든 메서드나 변수의 타입을 <T> 처럼
**특정 타입으로 맞춰**주어야한다.

또한 **제네릭 키워드 <> 안에는 대문자**가 들어와야한다.

---
# Step 03. 제네릭 Return 메소드로 사용자 정의 리스트의 확장

## 제네릭 타입 메서드 
### MyCustomList.java



    public T get(int index) {
        return list.get(index);
    }


를 통해 타입과 관계없이 같은 기능을 수행하는 메서드 생성

리턴 타입 = 제네릭

---
# Step 04. 제네릭 퍼즐 - 제한점과 확장, 제네릭 메소드

## 제네릭 타입 메서드

<br>
Number.class ---> 정수 실수 클래스의 최상위 추상 클래스

### MyCustomList.java

    public class MyCustomList<T extends Number> {
        ...
    }

T extends Number 키워드를 넣은 것 처럼, MyCustomList에 쓰일 수 있는 클래스들을 제한 할 수 있다.

### Main.java
    static <X> doSomething(X value) {
        return value;
    }
X 라는 타입을 받아서 X라는 타입을 반환해주는 정적 메서드

### Main.java
    static <X> X doubleValue(X value) {
        return value;
    }

    static <X extends List> void duplicate(X list) {
        list.add(list);
    }
첫번째 메서드 : X라는 타입을 받아서 그 값을 반환 <br>

두번째 메서드 : X라는 모든 리스트 타입을 받아서, 그 리스트 내용을 추가(Vector, ArrayList 등 리스트의 구현체는 모두 가능하다.)

---
# Step 05. 제네릭과 와일드 카드 - 상계, 하계

### Main.java

    static Number sumOfNumberList(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }
? 키워드는 와일드카드이다.

<br>

위 예시는 상한 경계(상계) 와일드카드에 대한 것으로 

타입이 Number나 Number가 상속하는 하위 계층을 모두 받는다. 

Number의 하위 계층은 (Integer, Double, Short, Float, Long, Byte)

<br>

### Main.java

    static void addACoupleOfValues(List<? super Number> numbers) {
        numbers.add(1);
        numbers.add(1.0);
        numbers.add(1.0f);
        numbers.add(1l);
    }

<br>

위 예시는 하한 경계(하계) 와일드카드에 대한 것으로

타입이 Number나 Number가 상속하는 상위 계층 의 상위 클래스(int, double, float, long ...)를 모두 받는다.

<br>

---