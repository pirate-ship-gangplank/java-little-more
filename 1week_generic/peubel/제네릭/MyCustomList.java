package peubel.제네릭;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyCustomList<T extends Number> {
    ArrayList<T> list = new ArrayList<>();


    public void addElement(T element){
        list.add(element);
    }

    public void removeElement(T element){
        list.remove(element);
    }

    public T get(int index){
        return list.get(index);
    }


    @Override
    public String toString() {
        return "MyCustomList{" +
                "list=" + list +
                '}';
    }
}
