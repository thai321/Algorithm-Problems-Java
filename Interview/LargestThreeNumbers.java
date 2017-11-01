public class LargestThreeNumbers {

  public static void main(String[] args) {
    largestThreeNumbers(new int[] {3,1,9,12,4,6,16,2,5});
    // 16 12 9
  }

  private static void largestThreeNumbers(int [] array) {

    int first, second, third;
    first = second = third = Integer.MIN_VALUE;

    for(int el : array) {
      if(el > first) {
        third = second;
        second = first;
        first = el;
      } else if (el > second) {
        third = second;
        second = el;
      } else if (el > third){
        third = el;
      }
    }

    System.out.println(first + " " + second + " " + third);
  }
}
