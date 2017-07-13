public class Algorithm {
  public int sumIterative(int n) {
    int result = 0;
    for(int i = 1; i <= n; i++)
      result += i;
    return result;
  }

  public int sumRecursive(int n) {
    if (n == 1) return 1;
    return n + sumRecursive(n - 1);
  }

  public static void main(String[] args) {
    Algorithm algorithm = new Algorithm();
    System.out.println(algorithm.sumIterative(5));
    System.out.println(algorithm.sumRecursive(5));
  }
}
