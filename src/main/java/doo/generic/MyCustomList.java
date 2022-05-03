package doo.generic;

import java.util.ArrayList;
import java.util.List;

public class MyCustomList<T extends Number> {

  private List<T> list = new ArrayList<>();

  public void addElement(T element) {
    element.doubleValue(); // Number 클래스의 메서드를 사용할 수 있다.
    element.intValue();
    list.add(element);
  }

  public void removeElement(T element) {
    list.remove(element);
  }

  public T get(T index) {
    return index;
  }

  public <A extends Number> A returnValue(A value) {
    return value;
  }

}
