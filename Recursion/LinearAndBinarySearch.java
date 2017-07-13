public class LinearAndBinarySearch {
  private int[] array;

  public LinearAndBinarySearch(int[] array) {
    this.array = array;
  }

  public boolean linearSearch(int item) {
    for(int i = 0; i < array.length; i++){
      if(array[i] == item) return true;
    }
    return false;
  }

  public boolean binarySearch(int item) {
    return binarySearch(0, array.length - 1, item);
  }

  private boolean binarySearch(int startIndex, int endIndex, int item) {
    if( endIndex < startIndex ) {
      System.out.println("The item is not present in the array ... ");
      return false;
    }

    int middleIndex = (startIndex + endIndex)/2;
    if( array[middleIndex] == item) {
      return true;
    } else if (item < array[middleIndex]) {
      return binarySearch(startIndex, middleIndex - 1, item);
    } else {
      return binarySearch(middleIndex + 1, endIndex, item);
    }
  }

  public static void main(String[] args) {
    int[] array = { 1, 5, 3, 8, 9, 10 };
    LinearAndBinarySearch f = new LinearAndBinarySearch(array);
    System.out.println(f.binarySearch(100)); // false
    System.out.println(f.binarySearch(4)); // false
    System.out.println(f.binarySearch(5)); // false
    System.out.println(f.binarySearch(9)); // true
    System.out.println(f.binarySearch(3)); // true
    System.out.println(f.linearSearch(40)); // false
    System.out.println(f.linearSearch(3)); // true
  }
}
