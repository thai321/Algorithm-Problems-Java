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

## `Linear and Binary Search`
1) `Linear search`: in an unsorted array we have to iterate through the whole array sequentially ... checking every item
> <span style="color:blue">`O(n) time complexity` <span>

2) `Binary Search` : we can do better if the array is sorted
<p> &nbsp; Finds the position of a target value within a sorted array !!!

> <span style="color:blue">`O( log(n) ) time complexity` <span>

- The binary search algorithm begins by comparing the target value to the value of the middle element of the sorted array:
  - If this element is greater than the item we are looking for: we continue the sarch on the left subarray
  - If the element is less -> we search the right subarray
+ Why is it good? We can discard half of the items on every iteration (that's why it will have logarithmic time complexity)


3) `Interpolation search`: We can use it if the array is sorted
- It is like we humans look for a name in a telephone book
  - We make a guess where in the remaining search space the sought item might be
  - in binary search we jump to the middle index ... here this is not the case!!!
  - ~ We just make a simple linear interpolation
  - Ex: a a a a z z z
  - <span style="color:blue">`O( log(log(N)) ) comparisons !!! `<span>

```java
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
```

## `Towers of Hanoi`
- It consists of three rods and number of disks of different sizes which can slide onto any rod
- The puzzle starts with the disks in a neat stack in ascending order of size on one rod, the smallest at the top, thus making a conical shape
- The minimum number of moves required to solve a Tower of Hanoi problem is
- > 2<sup>n</sup> -1 // O( 2<sup>n</sup> ) exponential time complexity
- We have some rules
  - Only one disk can be moved at a time
  - each move consists of taking the upper disk from one of the stacks and placing it on top of another stack -> a disk can only be moved if it is the uppermost disk on a stack
  - No disk may be placed on top of a smaller disk

+ There will be always a situation like this:
  + We have managed to shift n - 1 plates to the auxilary rod
  +  -> We just hae to put the largest to the last rod!!!
+ And we have to put the plates form the auxilary rod to the top of the biggest plate !!!
  + But it is the same problem again so we can use recursion

```java
public class TowerOfHanoi {

  public void solveHanoi(int n , char rodFrom, char middleRod, char rodTo) {
    if (n == 1) { // base case is the number of plates we handle is equal to 1
      System.out.println("Plate 1 from " + rodFrom + " to " + rodTo);
      return;
    }

    // We to have shift n-1 plates from the 1st rod to middle rod
    solveHanoi(n - 1, rodFrom, rodTo, middleRod);

    // Move the current bigest (from stack) disk from 1st rod to 3rd rod
    System.out.println("Plate " + n + " from " + rodFrom + " to " + rodTo);

    // After move bigest disk to the 3rd rod
    // Move n - 1 plates from middle rod to 3rd rod
    solveHanoi(n - 1, middleRod, rodFrom, rodTo);
  }
  public static void main(String[] args) {
    TowerOfHanoi f = new TowerOfHanoi();
    f.solveHanoi(3, 'A', 'B', 'C');
  }
}
/*
(3, A, B, C) --> (2, A, C, B) -> (1, A, B, C) base case
Plate 1 from A to C  (1, A, B, C) --> base case

(2, A, C, B) pop from stack
Plate 2 from A to B  (2, A, C, B) ; from solveHanoi(2, rodFrom, rodTo, middleRod);

(2, A, C, B) -> (1, C, A, B) base case
Plate 1 from C to B  (1, C, A, B) ; from solveHanoi(1, middleRod, rodFrom, rodTo);

(3, A, B, C) largest disk, pop from stack
Plate 3 from A to C  (3, A, B, C) ; from solveHanoi(3, rodFrom, middleRod, rodTo);

(2, B, A, C) --> (1, B, C, A) base case
Plate 1 from B to A  (1, B, C, A) ; from solveHanoi(1, middleRod, rodFrom, rodTo);

(2, B, A, C) pop from stack
Plate 2 from B to C ; from solveHanoi(2, rodFrom, rodTo, middleRod);

(2, B, A, C) --> (1, A, B ,C) base case
Plate 1 from A to C ; from solveHanoi(1, middleRod, rodFrom, rodTo);
*/
```
