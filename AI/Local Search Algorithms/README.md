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
