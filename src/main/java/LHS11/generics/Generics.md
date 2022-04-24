Generics
> - 컴파일시 타입을 체크해 주는 기능
- 객체의 타입 안정성을 높이고 형변환의 번거로움을 줄여줌
- 클래스 옆에 타입 변수를 선언해서 사용
```
public class MyCustomList<T>{}  // T-> 변수명 변경 가능 
```
- 메소드 반환형이나 매개변수에서도 사용 (매개변수에 사용할 때는 와일드카드 이용)
```
public class MyCustomList<T> {
    ArrayList<T> list = new ArrayList<>();
    public void addElement(T element){
        list.add(element);
}
```
여러 type 자료형을 받을 수 있다.
```
 MyCustomList<String> list = new MyCustomList<String>();
        list.addElement("Element 1");
        list.addElement("Element 2");
        String value = list.get(0);
        System.out.println(value);
 MyCustomList<Integer> list2 = new MyCustomList<Integer>();
        list2.addElement(1);
        list2.addElement(Integer.valueOf(5));
        Integer number = list2.get(0);
        System.out.println(number); 
```
- Generics에서 자료형을 제한 가능
```
// Number 클래스를 포함한 하위 클래스들만 가능
public class MyCustomList<T extends Number>{}
```
- 제네릭을 이용한 정적 메소드
```
static <X>  X doubleValue(X value){
        return value;
    }
// List 클래스를 포함한 하위 클래스만 가능 ex) Vector, ArrayList
static <X extends List> void duplicate(X list){
        list.addAll(list);
    }    
```
- 와일드 카드 <?>
    - 제네릭 클래스의 객체를 메소드의 매개변수로 받을 때, 그 객체의 타입 변수를 제한하는 것
    - 제네릭 클래스에서 매개변수로 ArrayList를 받는다고 할 때, ArrayList가 어떤 타입 변수를 가지고 있든 다 받아들일 수 있음
      ex) Integer 만 받아야 되는데 String을 받는다던가..
```
<? extends T> 와일드 카드의 상한 경계 (T(Number class)와 그 자손들을 구현한 
객체들만 매개변수로 가능)
static double sumOfNumberList(List<? extends Number> numbers){
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }
```
```
<? super T> 와일드 카드의 하한 경계 (T(Number class)와 그 상위 클래스를 구현한 
객체들만 매개변수로 가능)	
static void addACoupleOfValues(List<? super Number> numbers){
        numbers.add(1);
        numbers.add(1L);
        numbers.add(1.0f);
        numbers.add(1.0);
    }
```