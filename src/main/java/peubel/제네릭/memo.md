## 스터디 1주차 정리

#주제
제네릭의 대해 이해하고, 사용시 이점과 활용 방법에 대해 정리






##제네릭이란?

제네릭은 클래스와 인터페이스, 그리고 메소드를 정의할 때 타입을 파라미터로 사용할 수 있도록 하는 기능을 말한다.

###MyCustomList.java
```
public class MyCustomList<T>{ 

    public T get(int index){
        return list.get(index);
    }
}
```
위와 같이 클래스나 메소드를 정의할때 <> 안에 <strong>대문자 알파벳</strong>을 명시해주면 사용 가능하다.




##제네릭의 장점
제네릭은 어떠한 데이터 타입이 들어올지 모를때 사용하기 좋다

###Ex)
```
MyCustomList list = new MyCustomList();
list.addElement("문자");
System.out.println(list.get);

MyCustomList list2 = new MyCustomList();
list2.addElement(5); 
System.out.println(list2.get(0)); 

```
위 코드 처럼 제네릭을 사용하지 않고 문자열과 숫자 타입을 하나의 메서드에서 받을 수 있을까?
또 원하는 리턴 타입을 내가 정할 수 있을까?

불가능하거나 굉장히 번거로울것이다(Object 클래스를 사용하여 형변환하거나 기타 등등 문제가 많다). 
따라서 제네릭을 사용하면된다.


###Ex)
```
public class MyCustomList<T> {
    ArrayList<T> list = new ArrayList<>();


    public void addElement(T element){
        list.add(element);
    }

    public T get(int index){
        return list.get(index); 
    }
}
```
```
MyCustomList<String> list = new MyCustomList();
list.addElement("문자");
System.out.println(list.get); //결과 : 문자

MyCustomList<Integer> list2 = new MyCustomList();
list2.addElement(5); 
System.out.println(list2.get(0)); //결과 : 5

```

위 코드 처럼 클래스에서 제네릭을 명시해주고 메서드 파라미터와 리턴 타입 모두 명시해준다면
앞서 말한 코드의 문제점을 해결 할 수 있다.

이렇게 예시로써 알 수 있는점은 다양한 데이터를 받아올때의 문제점을 제네릭으로 해결 할 수 있다.
컴파일 타임에 자료형의 오류에 대한 검증을 수행하여 런타임에 자료형에 안전한 코드를 실행한다.
반환값에 대한 타입 변환 및 타임 검사에 들어가는 노력을 줄일수 있고, 형변환이 없어지므로 가독성이 좋아진다.


##상한 경계와 하한 경계

T extends A (상한 경계)

타입이 넣을 수 있는 타입 상속관계에서 제일 높은 클래스를 지정해준다.
타입 매개변수의 클래스는 A 클래스이거나 하위 클래스이어야 한다.

T super B

타입에 넣을 수 있는 타입 상속 관계에서 제일 낮은 클래스를 지정해준다.
타입 매개 변수의 클래스는 B 클래스이거나 상위 클래스이어야 한다.


##제네릭의 상속

제네릭은 상속을 걸어 개발자가 원하는 클래스만 들어올 수 있게끔 할 수 있다.

```
public class MyCustomList<T extends Number> // 상한경계이다.
```
위 코드 처럼 상속 조건을 걸어두면  Number 클래스와 상속 받는 클래스들만 제네릭 타입으로 들어 올 수 있다.


##비경계 와일드 카드

```
void test(List<?>
```
위 코드 처럼 <?> 의 형태로 사용하며 ?가 모든 타입이 인자가 될 수 있다.

비경계 와일드 카드의 특징은 ?에서 얻은 타입은 어떤 타입이 와도 읽을 수 있도록 object이다


###상한경계 와일드카드
```<? extends A>```
?는 A클래스이거나 상속 받는 클래스만 올 수 있다.<br/>
따라서 받아지는 타입은 A이다.

###하한경계 와일드카드
```<? extends B>```
?는 B클래스거나 B클래스의 모든 상위 클래스이다.<br/>
따라서 받아지는 타입은 Object이다.