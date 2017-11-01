import java.math.BigInteger;

public class FactorialBigInteger {

  public static void main(String[] args) {
    int number = 10;
    // int fact = 1;
    BigInteger fact = BigInteger.ONE;
    for(int i = 1; i <= number; i++) {
      // fact = fact*i;
      fact = fact.multiply(BigInteger.valueOf(i));
    }

    System.out.println(fact); // 3628800

  }
}
