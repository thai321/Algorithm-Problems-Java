import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

  private Map<Integer,Integer> memoizeTable;

  public Fibonacci() {
    this.memoizeTable = new HashMap<>();
    this.memoizeTable.put(0,0);
    this.memoizeTable.put(1,1);
  }

  // O(N)
  public int fibonacciDP(int n) {

    // O(1) !!!
    if(memoizeTable.containsKey(n)) return memoizeTable.get(n);

    memoizeTable.put(n-1, fibonacciDP(n-1));
    memoizeTable.put(n-2, fibonacciDP(n-2));

    int calculatedNumber = memoizeTable.get(n-1) + memoizeTable.get(n-2);

    memoizeTable.put(n, calculatedNumber);

    return calculatedNumber;
  }

  // it has exponential running time O(2^n)
  public int naiveFibonacci(int n) {
    if(n == 0) return 0;
    if(n == 1) return 1;
    return naiveFibonacci(n-1) + naiveFibonacci(n-2);
  }

  public static void main(String[] args) {
    Fibonacci f = new Fibonacci();
    System.out.println(f.naiveFibonacci(6)); // 8
    System.out.println(f.fibonacciDP(6)); // 8
    // System.out.println(f.naiveFibonacci(1000)); // so slow
    System.out.println(f.fibonacciDP(1000)); // 1556111435, much faster
  }
}
