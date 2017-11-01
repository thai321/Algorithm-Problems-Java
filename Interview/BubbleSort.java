public class BubbleSort {
  public static void main(String[] args) {

    int intArray[] = {9,8,7,5,6};
    System.out.println("Before sorting");
    for(int i : intArray) {
      System.out.println(i);
    }
    bubbleSort(intArray);

    System.out.println("After sorting");
    for(int i : intArray) {
      System.out.println(i);
    }

  }

  private static void bubbleSort(int[] intArray) {
    int temp;
    for(int i = 0; i < intArray.length ; i++) {
      for(int j = 1; j < intArray.length - i; j++) {
        if(intArray[j - 1] > intArray[j]) {
          // Swap the elements
          temp = intArray[j - 1];
          intArray[j - 1] = intArray[j];
          intArray[j] = temp;
        }
      }
    }
  }
}
/*
Before sorting
9
8
7
5
6
After sorting
5
6
7
8
9
*/
