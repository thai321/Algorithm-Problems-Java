// 1) Number is keep on incrementing.
// 2) Two for loops
import java.util.Scanner;

public class FloydsTriangle {
  public static void main(String[] args) {
    int n, c, d, num = 1;

    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();  // 4

    System.out.println("Floyds Triangle");

    for(c = 1; c <= n; c++) {
      for(d = 1; d <= c; d++) {
        System.out.print(num + " ");
        num++;
      }
      System.out.println();
    }
  }
}

/*
4
Floyds Triangle
1
2 3
4 5 6
7 8 9 10
*/
