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
## Knapsack problem
- It is a problem in combinatorial optimization
- Given a set of items, each  iwth a mass <b>w</b> and a value <b>v</b>, determine the number of each item to include in a colleciton so that the total wight <b>M</b> is less than or equal to a given limit and the total value is as large as possible
- The problem often arises in resource allocation where there are financial constraints

#### <u>Application:</u>
- Has lots of applications of course
- Finding the least wasteful way to cut raw materials
- Selection of investments and portfolios
- Selection of assets for asset-backed securitization
- Construction and scoring of tests in which the test-takers have a choice as to which questions they answer

#### <u>Divisible problem</u>
- If we can take fractions of the given items, then the greedy approach can be used
- Sort the items according to their values, it can be done in <b>O(N logN)</b>
- Start with teh item that is the most valuable and take as much as possible
- Than try with the next item from our sorted list
- This linear search has <b>O(N)</b> time complexity
- Overall complexity: <b> O(N logN) + O(N) = O(N logN) !!!</b>
- So we can solve the divisible knapsack problem quite fast

- <u>0-1 knapsack problem</u>:
  - In this case we are not able to take fractions: we have to decide whether to take an item or not
  - Greedy algorithm will not provide the optimal result !!!
  - Another approach would be to sort by cost per unit weight and include form highest on down until knapsack is full .. not a good solution too
  - <b>Dynamic programming</b> is the right way!!!

### <u>Dynamic programming</u>
- Solves larger problem by realating it to overlapping subproblems and then solves the subproblems
- It works through the exponential set of solutions, but does not examine them all explicitly
- Stores intermediate results so that ehy are not recomputed
  - <b>"memoization"</b>
- Solution to original problem is easily computed from the solutions ot the subproblems

### <u>Knapsack with dynamic programming</u>
- We have to define subproblems: we have N items so we have to make <b>N</b> decisions whether to take the item with given index or not
- The <u>subporblems:</u> the solution considering every possible
- <b>S[i][w]</b> the solution to the subproblem coreesponding to the first <b>i</b> items and avaiblable weight <b>w</b>
- or in other words ...
- <b>S[i][w] = </b> the maximum cost of items that fit inside a knapsack of size(weight) w, choosing from the first <b>i</b> items !!!
- We have to decide whether to take the item or not
> <b>S[i][w] = Math.max(S[i - 1][w]; ########## V<sub>i</sub>+ S[i - 1][w - w<sub>i</sub>]) </b>

>                    Do not take i-th item      we take i-th item

> But !!! We are only considering <b>S[i - 1][w - w<sub>i</sub>]</b> if it can fit <b>w >= w<sub>i</sub>


### <u>Time complexity</u>
- Running time of Knapsack: <b>O(n*W)</b> ;
-  n is items we would like to include in the knapsack, W is the maximum capicity of the knapsack
- <b>But</b> it is not polynomial, it is pseudo-polynomial
- Numeric algorithm runs in <b>pserudo-polynomial time</b> if its running time is polynomial in the numeric value of the input, but is exponential in the length of the input (the number of bits required to represent it)

```java
public class Knapsack {
  private int numOfitems;
  private int capacityOfKnapsack; // max weight
  private int[][] knapsackTable;
  private int totalBenefit;
  private int[] weights; // list of weights of items
  private int[] values; // list of values of items

  public Knapsack(int numOfitems, int capacityOfKnapsack, int[] weights, int[] values) {
    this.numOfitems = numOfitems;
    this.capacityOfKnapsack = capacityOfKnapsack;
    this.weights = weights;
    this.values = values;
    this.knapsackTable = new int[numOfitems + 1][capacityOfKnapsack + 1];
    // add 1 because we need an extra row for 0 item
    // and extra column for 0 weight
  }

  public void solve() {
    // time complexity: O(N*W), N is number of items
    // ignore the first row and first column because it's all 0
    for(int i = 1; i < numOfitems + 1; i++) {
      for(int w = 1; w< capacityOfKnapsack + 1; w++) {
        int notTakingItem = knapsackTable[i-1][w]; // not taking item i
        int takingItem = 0; // default is 0

        // make sure that w-weight[i] >= 0
        if (weights[i] <= w) {
          takingItem = values[i] + knapsackTable[i-1][w-weights[i]];
        }
        knapsackTable[i][w] = Math.max(notTakingItem, takingItem);
      }
    }
    totalBenefit = knapsackTable[numOfitems][capacityOfKnapsack];
  }

  public void showResult() {
    System.out.println("Total benefit: " + totalBenefit);

    for(int n = numOfitems, w = capacityOfKnapsack; n > 0; n--) {
      int currentValue = knapsackTable[n][w];
      int previousValue = knapsackTable[n-1][w]; // 1 row above
      // if the currentValue is equal to the previousValue
      // means we don't take this n-th item. --> next
      if (currentValue != 0 && currentValue != previousValue) {
        System.out.println("We take item: # " + n);
        w = w - weights[n];
      }
    }
  }

  public static void main(String[] args) {
    int numOfitems = 3;
    int capacityOfKnapsack = 5; // max weight

    int[] weights = new int[numOfitems + 1]; // weight of items
    weights[1] = 4;
    weights[2] = 2;
    weights[3] = 3;

    int[] values = new int[numOfitems + 1]; // profit of items
    values[1] = 10;
    values[2] = 4;
    values[3] = 7;

    Knapsack knapsack = new Knapsack(numOfitems, capacityOfKnapsack, weights, values);
    knapsack.solve();
    knapsack.showResult();
    /*
      Total benefit: 11
      We take item: # 3
      We take item: # 2
    */
  }
}
```
