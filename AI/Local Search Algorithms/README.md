# Local Search

## Brute-force
- We just iterate through the given interval and do a brute-force maximum/minimum find
- **O(N)** time complexity
- WE examine all possible solutions: it can be very slow
- BUT always find the optimal solution

```java
public class BruteForceSearch {

  public static double f(double x) {
    return -(x*x) + 2;
  }

  public static void main(String[] args) {
    double startPositionX = -2;
    double maximumX = startPositionX;
    double max = f(startPositionX);
    double dx = 0.01;

    for(double i = startPositionX; i < 2; i += dx) {
      if( f(i) > max ) {
        max = f(i);
        maximumX = i;
      }
    }

    System.out.println("The maximum is at x = " + maximumX + ", y = " + f(maximumX));
  }
}
/*
The maximum is at x = 1.6410484082740595E-15, y = 2.0
*/
```

--------

## Stochastic Search
- We generate random numbers: the indexes where we evaluate the function
- We store a reference to the maximum element: keep checking whether the generated index **f(x) > max** **or not**
- Not always give the best solution !!! (unless we make infinite iteration)
- Sometimes a good guess is enough

```java
import java.util.Random;

public class StochasticSearch {

  public static double f(double x) {
    return -(x-1) * (x-1) + 2;
  }

  public static void main(String[] args) {

    Random random = new Random();
    double startPointX = 0;
    double max = f(startPointX);

    for(int i = 0; i < 1000; i++) {
      double index = 2*random.nextDouble();

      if(f(index) > max)
        max = f(index);
    }

    System.out.println("Maximum value y=f(x) is " + max);
  }
}
/*
10 iteration
Maximum value y=f(x) is 1.9972853110624096

100 iterations
Maximum value y=f(x) is 1.9998010181549513

1000 iterations
Maximum value y=f(x) is 1.9999991998781321a
*/
```

-------

## Hill Climbing
- Start at a given x position
- Keep comparing the adjacent elements: try to find the maximum among them
- Usually halts at a local minimum/maximum
- In order to find the global optimum we should start the algorithm again and again several times !!!
  - "Repeated local search"

```java
public class HillClimbing {

  public static double f(double x) {
    return -(x-1) * (x-1) + 2;
  }

  public static void main(String[] args) {
    double dx = 0.01;
    double actualPointX = -2;
    double max = f(actualPointX);

    while(f(actualPointX + dx) > max) {
      max = f(actualPointX);
      System.out.println("x = " + actualPointX + ", y = " + f(actualPointX));
      actualPointX += dx;
    }

    System.out.println("Local maximum is: " + max);
  }
}
/*
x = -2.0, y = -7.0
x = -1.99, y = -6.940100000000001
x = -1.98, y = -6.8804
x = -1.97, y = -6.820899999999998
x = -1.96, y = -6.7616
x = -1.95, y = -6.702500000000001
x = -1.94, y = -6.643599999999999
x = -1.93, y = -6.5848999999999975
x = -1.92, y = -6.526399999999999
...
x = 0.9600000000000023, y = 1.9984000000000002
x = 0.9700000000000023, y = 1.9991
x = 0.9800000000000023, y = 1.9996
x = 0.9900000000000023, y = 1.9999
Local maximum is: 1.9999
*/
```
