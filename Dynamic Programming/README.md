# Dynamic Programming
- Dynamic programming is a method for solving a complex problem by breaking it down into a collection of simpler sub-problem
- It is applicable to problems exhibiting the properties of overlapping subproblems
- The method takes far less time than other methods that don't take advantage of the subproblem overlap
- We need to solve different parts of the problem (subproblems) + combine the solutions of the subproblems to reach an overall solution
- We solve each subproblems only once -> we reduce the number of computations
- Subproblems can be stored <b>("memoization")!!!</b>

## Dynamic Programming vs Divide and Conquer method
- Several problems can be solved by combining optimal solutions tonon-overlapping sub-problems
- This strategy is called "divide and conquer" method
- This is why merge sort / quick sort are not classified as dynamic programming problems
- Overlapping subproblems -> dynamic programming
- Non-overlapping subproblems -> divide and conquer method

## Fibonacci Numbers
- <u>Solutions:</u> use dynamic programming and memoization in order to avoid recalculating a subproblem over and over again
- We should use an associative array abstract data type (hastable) to store the solution for the subproblems // <b>O(1)</b> time complexity
- On every <b>f()</b> method call -> we insert the calculated value if necessary
- Why is it good? Instead of the exponential time complexity we will have <b>O(N)</b> time complexity + requires <b>O(N)</b> space
  - Recursive approach: <b>O(2<sup>n</sup>)
  - Dynamic programming: <b>O(N)</b>

```java
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
```
