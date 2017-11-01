import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwapArrayList {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    System.out.println("Before swapping elements = " + list);
    Collections.swap(list, 1, 2);
    System.out.println("After swapping elements = " + list);
  }
}

/*
Before swapping elements = [1, 2, 3, 4]
After swapping elements = [1, 3, 2, 4]
*/
