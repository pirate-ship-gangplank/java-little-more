# Generics

## Generics 사용 이유
- 컴파일시 강한 타입체크를 할 수 있다
- 타입변환(casting)을 제거한다
- 객체를 저장하는 기술인 Collection Framework의 단점을 개선한 기능
- 클래스나 메소드에 자료형을 매개변수 형식으로 사용할 수 있는 기능. 
- Class ArrayList<E>에서 'E'가 있는 곳에는 ArrayList를 선언하고 생성할 때 사용할 실제타입
- E는 Element(원소)를 말하며, new ArrayList<String>() 이면 'E'는  'String' 클래스를 가르키며,
    < > 안의 String은 유형 매개 변수 (type parameter)이다. 
        따라서 boolean add(Object o) 메소드는 boolean add(String o) 가 된다. 
- 'E'는 ArrayList의 인스턴스를 만들때 < > 안에 넣는 타입을 말합니다.

### Ex Code
package day10;

import java.util.ArrayList;
import java.util.Iterator;

public class Generic {

	public static void main(String[] args) {
		ArrayList list = new ArrayList(10);
		list.add(10);
		list.add("List Test");
		
		Iterator i = list.iterator();
		Integer num;
		for(int j=0; j<list.size(); j++) {
			num = (Integer) i.next(); //실행시 String type 부분에서 에러
			System.out.println("num = "+num);
		}

	}

}
```
package day10;

import java.util.ArrayList;
import java.util.Iterator;

public class Generic2 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>(10);
		list.add(10);
		list.add("List Test");
		
		Iterator i = list.iterator();
		Integer num;
		for(int j=0; j<list.size(); j++) {
			num = (Integer) i.next();//컴파일 단계에서 에러
			System.out.println("num = "+num);
		}

	}

}
```
## Generic 클래스  
   - 제네릭 클래스는 형 매개변수(type parameter)를 가지는 클래스입니다. 
   - 형매개변수는 객체가 생성시 전달받으며 속성이나 메소드의 자료형으로 
     사용됩니다. 

  [정의] 
   class Classname<type parameter>{ 
           // .... class body 
   } 

### Ex Code
- 아래의 코드 처럼 Object 타입을 사용하면 모든 객체를 받을수 있지만 잦은 형변환이 필요한다는 단점이 있다
package day10;
```
class Box{
	private Object object;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
}

class Apple{
	
}

public class NoGeneric {

	public static void main(String[] args) {
		Box box = new Box();
		box.setObject("오인웅");//자동 타입 변환 String -> Object
		String name =(String) box.getObject();// 강제로 형변환이 필요 Object -> String
		box.setObject(new Apple());
		Apple apple = (Apple)box.getObject();
	}

}
```
```
 GenericClassExample.java 

class SimpleGeneric<T> { 
private T[] values; 
private int index; 
SimpleGeneric(int len) { // Constructor 
values = (T[])new Object[len]; 
index = 0; 
} 
public void add(T ... args) { 
for (T e : args) 
values[index++] = e; 
} 
public void print() { 
for (T e : values) 
System.out.print(e + " "); 
System.out.println(); 
} 
} 
 
public class GenericClassExample { 
public static void main(String[] args) { 
SimpleGeneric<Integer> gInteger = new SimpleGeneric<Integer>(10); 
SimpleGeneric<Double> gDouble = new SimpleGeneric<Double>(10); 
gInteger.add(1, 2); 
gInteger.add(1, 2, 3, 4, 5, 6, 7); 
gInteger.add(0); 
gInteger.print(); 
gDouble.add(10.0, 20.0, 30.0); 
gDouble.print(); 
} 
} 

```
- Generic 을 이용하면 객체를 생성시 구체적인 타입으로 변형 되므로 형변환이 필요 없다
 ```
  MultipleTypeParam.java 


class Price<N, V> { 
private N[] names; 
private V[] values; 
private int index; 
Price(int size) { 
names = (N[])new Object[size]; 
values = (V[])new Object[size]; 
index = 0; 
} 
public void insert(N n, V v) { 
names[index] = n; 
values[index] = v; 
++index; 
} 
public void print() { 
for (int i = 0; i < index; i++) 
System.out.println(names[i] + " : " + values[i]); 
} 
} 
 
public class MultipleTypeParam { 
public static void main(String[] args) { 
Price<String, Integer> p1 = new Price<String, Integer>(10); 
Price<String, Double> p2 = new Price<String, Double>(10); 
p1.insert("Apple", 1200); 
p1.insert("Banana", 2000); 
p1.insert("Grape", 4500); 
p2.insert("USD", 943.0); 
p2.insert("JPY", 822.86); 
p2.insert("EUR", 1273.05); 
System.out.println("*** Fruit Price ***"); 
p1.print(); 
System.out.println("*** Exchange Rate ***"); 
p2.print(); 
} 
} 

```

## Generic 인터페이스  
   - 클래스와 마찬가지로 형 매개변수를 가지는 제네릭 인터페이스를 선언합니다. 


  [정의] 
   interface InterfaceName<type parameter>{ 
           // .... interface body 
   } 

### Ex Code
``` 
GenericInterfaceExample.java 


interface GenericInterface<T> { 
public void setValue(T x); 
public String getValueType(); 
} 

class GenericClass<T> implements GenericInterface<T> { 
private T value; 
public void setValue(T x) { 
value = x; 
} 
public String getValueType(){ 
return value.getClass().toString(); 
} 
} 
 
public class GenericInterfaceExample { 
public static void main(String[] args) { 
GenericClass<Integer> gInteger = new GenericClass<Integer>(); 
GenericClass<String> gString = new GenericClass<String>(); 
gInteger.setValue(10); 
gString.setValue("Text"); 
System.out.println(gInteger.getValueType()); 
System.out.println(gString.getValueType()); 
} 
} 
```




## Generic 메소드 
  -  자바에서 제네릭 프로그램의 단위에는 클래스와 인터페이스, 그리고 
     메소드가 있습니다.  

  [정의] 
   <type parametr>return_type MethodName(parameter){ 
           // .... method body 
   } 


### Ex Code
```
GenericMethodExample.java 

public class GenericMethodExample { 
public static <T> void printArgInfo(T arg) { 
System.out.print("Argument Type is " + arg.getClass()); 
System.out.println(" / Value is " + arg.toString()); 
} 
public static void main(String[] args) { 
Integer i = new Integer(10); 
char c = 'A'; 
float f = 3.14f; 
printArgInfo(i); // <Integer> void printArgInfo(Integer) 
printArgInfo(c); // <Character> void printArgInfo(Character) 
printArgInfo(f); // <Float> void printArgInfo(Float) 
} 
} 
```

## 제네릭 클래스와 제네릭 메소드를 모두 가지고 있는 경우 

### Ex Code
```NestedGenericMethodExample.java 

class GenericClass <T> { 
public void printMethodArgInfo(T arg) { 
System.out.print("Argument Type is " + arg.getClass()); 
System.out.println(" / Value is " + arg.toString()); 
} 
public <T> void printGenericMethodArgInfo(T arg) { 
System.out.print("Argument Type is " + arg.getClass()); 
System.out.println(" / Value is " + arg.toString()); 
} 
} 
public class NestedGenericMethodExample { 
public static void main(String[] args) { 
GenericClass<String> gc = new GenericClass<String>(); 
gc.printGenericMethodArgInfo(10L); 
gc.printGenericMethodArgInfo('A'); 
//gc.printMethodArgInfo(10L); // error 
} 
} ```