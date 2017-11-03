## Extra Long Factorials

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    System.out.println(factorial(n));
  }

  private static BigInteger factorial(int n) {
		BigInteger result = BigInteger.ONE;
		while(n > 1) {
			result = result.multiply(BigInteger.valueOf(n))
			n--;
		}

		return result;
  }
}

```
