import java.util.Arrays;

public class SecondLargest {

  public static void main(String[] args) {
    int array[] = {1,20, 5, 16, 9, 10};

    int largest = array[0];
    for(int el : array) {
      if (el > largest) {
        largest = el;
      }
    }

    int secondLargest = array[0];
    for(int el : array) {
      if(el > secondLargest  && el < largest && secondLargest < largest) {
        secondLargest = el;
      }
    }

    System.out.println(largest); // 20
    System.out.println(secondLargest); // 16
  }
}
