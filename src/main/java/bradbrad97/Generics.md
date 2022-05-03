- generics란?

컴파일 타임에 타입을 체크해 주는 기능.

- generics의 장점
1. 객체의 타입 안정성을 제공함
2. 형변환의 번거로움을 줄여준다.

---

예제코드1)

ArrayList list = new ArrayList();

list.add(10);

list.add(20);

list.add(”30”);

Integer i = (Integer)list.get(2);

---

위의 예제에서 마지막줄 코드는 컴파일에러를 야기하지 않습니다. get()함수는 object를 반환하고 object를 Integer로 형변환 하는 것은 컴파일러 입장에서 문제가 되지 않기 때문입니다.

다만 런타임에서 ClassCastException error(형변환에러)가 발생합니다. 런타임에서 에러가 나는 것보다 컴파일시 에러가 나는게 낫기때문에

---

예제코드2)

ArrayList<Integer> list = new ArrayList<Integer>();

list.add(10);

list.add(20);

list.add(”30”); //에러발생

Integer i = (Integer)list.get(2);

---

코드를 위와 같이 수정한다면 list.add(30); 에서 컴파일 에러가 발생하게 됩니다. 따라서 generics를 통해 타입체크가 강화될 수 있다는 점을 확인할 수 있습니다.

- JDK 1.5 버젼(제네릭스 도입) 이후..

예제 1번과 같이 ArrayList에 여러가지 자료형을 넣고 싶을 때에는

ArrayList<object> list = new ArrayList<object>();

로 선언하는것이 크게 권장됩니다.

JDK 1.5버젼 이후에 ArrayList 클래스 원형은 일반클래스에서 ArrayList<E>형태의 지네릭클래스로 변형되었습니다. ArrayList 뿐만 아니라 object를 담고 있는 클래스는 모두 지네릭 클래스로 변경되었습니다.

- 왜 런타임에러보다 컴파일에러가 좋을까?

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7ca15340-f7e2-49bb-8b2a-6b4521a1b58f/Untitled.png)

## 제네릭스 사용법

## 클래스 단위 (제네릭 클래스) - 가장 광범위하게, 자주 쓰임

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/66bfa491-3790-415c-9204-3c88f5181f75/Untitled.png)

[]()

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/eb780b6b-daba-4c2c-9a0b-ad643b845f35/Untitled.png)

위와 같이 클래스명<타입> 형식으로 제네릭 클래스를 만들 수 있는데 메인 함수 내에서

MyCustomList 객체를 임의의 타입으로 선언해주면 컴파일타임에 각각 위와 같이 String, Integer로 타입캐스팅이 이뤄집니다. 따라서 개발자가 일일히 타입캐스팅을 해줘야되는 번거로움이 줄어들고 코드가 간결해집니다.

- 타입의 종류를 제한하고 싶을 때 (제한점? 상한점?)

String type의 MyCustomList 객체를 사용하는 것을 제한하고 싶다고 가정합니다.

그렇다면 클래스 선언부를

public class MyCustomList<T extends Number> 와 같이 선언해준다면 Number의 하위클래스들인 Integer, Double, Long과 같은 타입으로 객체 생성 범위를 제한할 수 있습니다.

### 메서드 단위

- 사용법

static <X> X doubleValue(X value){

return value;

}

main)

String value = doubleValue(Integer.valueOf(5)); //!!

String value = doubleValue(new String());

ArrayList value = doubleValue(new ArrayList());

- 사용되는 타입의 종류를 제한하고 싶을 때

제네릭 클래스의 방식과 유사합니다.

static <X extends List> X doubleValue(X value)

위와 같이 선언하면 List의 하위 클래스인 ArrayList, LinkedList와 같은 타입으로만 사용을 제한할 수 있습니다.

### 와일드카드란?

- 의미와 사용법

static Number sumOfNumberList(List<? extends Number> numbers){

//모든 원소 더함

}

여기서 와일드카드란 ?를 의미하는 것인데 이 함수는 Number의 하위클래스를 담는 리스트를 매개변수로 받아

Number타입의 값을 리턴합니다.

따라서 sumOfNumberList(new ArrayList({1,2,3,4,5})) → 15

sumOfNumberList(new ArrayList({1.1,2.2,3.3,4.4,5.5}) → 16.5

sumOfNumberList(new LinkedList({1,2,3,4,5}) → 15

와 같이 함수호출을 할 수 있습니다.

- 상한 경계 와일드 카드

위 예시와 같이 타입 범위를 제한하는 것

- 하한 경계 와일드 카드

static Number addACoupleOfValues(List<? super Number> numbers){

numbers.add(1);

numbers.add(1.0);

numbers.add(1l);

}ZZ

Number의 하위 클래스인 Integer, Double, Long 등을 numbers에 더해줄 수 있습니다.

*여기서 의문점 발생!