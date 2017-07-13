public class GCD {
  public int gcdIterative(int num1, int num2) {
    while( num2 != 0) {
      int temp = num2;
      num2 = num1 % num2;
      num1 = temp;
    }
    return num1;
  }

  public int gcdRecursive(int num1, int num2) {
    if(num2 == 0) return num1;
    return gcdRecursive(num2 ,num1 % num2);
  }
  public static void main(String[] args) {
    GCD gcd = new GCD();
    System.out.println(gcd.gcdIterative(30, 100)); // 10
    System.out.println(gcd.gcdRecursive(100, 30)); // 10
  }
}
