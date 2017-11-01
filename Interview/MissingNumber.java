public class MissingNumber {

  public static void main(String[] args) {

    int[] array = {1,2,3,4,6};
    int missingNumber = getMissingNumber(array,6);
    System.out.println(missingNumber); // 5
  }

  private static int getMissingNumber(int[] array, int n) {
    int expectedSum = (n*(n+1))/2;
    int actualSum = 0;
    for(int i : array) {
      actualSum = actualSum + i;
    }
    return expectedSum - actualSum;
  }
}
