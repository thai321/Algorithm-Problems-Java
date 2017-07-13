# Recursion

### `Tail Recursion`
- If the recursive call occurs at the end of a method
  - -> It is called a tail recursion
- The tail recursion is similar to a loop
- The method executes all the statements before jumping into the next recursive call


```java
public void tail(int N) {
  if (N == 1) return;

  System.out.println(N);

  tail(N - 1)
}
```

### `Head Recursion`
- If the recursive call occurs at the beginning of a method
  - -> It is called a head recursion
- The method saves the state before jumping into the next recursive
```java
public void head(int N) {
  if (N == 1) return;

  head(N - 1);

  System.out.println(N);
}
```

## `Stack with recursion`
- We have to track during recursion who called the given method and what arguments are to be handed over
- <span style="color:red"> AND WE HAVE TO TRACK THE PENDING CALLS !!! </span>
- We just need a single stack data structure: the operating sytem doeseverything for us
- Values are popped from the stack
- Comparing recursive implementation against iterative implementation
  - ->recursion is at least twice slower because first we unfold recursive calls (pushing them on a stack)
  - until we reach the base case and then we traverse the stack and retrieve all recursive calls

```python
def recursionSum(4):
  def recursionSum(3):
    def recursionSum(2):
      def recursionSum(1):
        return 1
      return 2 + 1
    return 3 + 2 + 1
  return 4 + 3 + 2 + 1
```

## Sum Iterative and Recursive
```java
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
    System.out.println(algorithm.sumIterative(5)); // 15
    System.out.println(algorithm.sumRecursive(5)); // 15
  }
}
```

### `HouseBuilding Layer Tail and Head`

```java
public class HouseBuilding {
  // like simple for loop
  public void buildLayerTail(int height) {
    if( height == 0) return;

    System.out.println(height);

    buildLayerTail(height - 1);
  }

  // use operating system stack memory
  public void buildLayerHead(int height) {
    if( height == 0) return;

    buildLayerHead(height - 1);

    System.out.println(height);
  }

  public static void main(String[] args) {
    HouseBuilding house = new HouseBuilding();
    house.buildLayerHead(5); // 1 2 3 4 5
    house.buildLayerTail(5); // 5 4 3 2 1
  }
}
```

## `Factorial`
- `Method 1:` (slower)
  - Pushing to the stack
  - Hit the base case n = 1
  - retrieve all recursie calls
```java
public int factorial(int n) {
  if (n == 1) return 1;
  return n * factorial(n - 1);
}
```
```python
def factorial(5):
  def factorial(4):
    def factorial(3):
      def factorial(2):
        def factorial(1):
          return 1
        return 2 * 1
      return 3 * 2 * 1
    return 4 * 3 * 2 * 1
  return 5 * 4 * 3 * 2 * 1 # 120
```

- `Method 2:` (Faster)
  - Pushing to the stack
  - Hit the base case n = 1 , and return the result(accumulator)
```java
public int factorial(int accumulator , int n) {
  if (n == 1) return accumulator;
  return factorial(n * accumulator, n - 1);
}
```

```python
def factorial(1,5):
  def factorial(1 * 5, 4):
    def factorial(1 * 5 * 4, 3):
      def factorial(1 * 5 * 4 * 3, 2):
        def factorial(1 * 5 * 4 * 3 * 2 * 1, 1):
          return (1 * 5 * 4 * 3 * 2 * 1) # 120
```

#### `Factorial source`

```java
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
```
## `Euclidean Algorithm: Greatest common divisor (GCD)`

```java
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
```
