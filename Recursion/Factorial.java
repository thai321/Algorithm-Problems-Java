public class Factorial {
  public int factorial(int n) {
    if (n == 1) return 1;
    return n * factorial(n - 1);
  }

  public int factorial(int accumulator , int n) {
    if (n == 1) return accumulator;
    return factorial(n * accumulator, n - 1);
  }

  public static void main(String[] args) {
    Factorial f = new Factorial();
    System.out.println(f.factorial(5)); // 120
    System.out.println(f.factorial(1, 5)); // 120
  }
}
