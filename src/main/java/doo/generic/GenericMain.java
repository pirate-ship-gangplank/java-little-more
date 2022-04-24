package doo.generic;

public class GenericMain {
  public static void main(String[] args) {
    MyCustomList<Integer> intList = new MyCustomList<>();
    intList.addElement(1);
    intList.addElement(2);
    intList.get(0);

  }
}
