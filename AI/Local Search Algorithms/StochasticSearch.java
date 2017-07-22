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
Maximum value y=f(x) is 1.9999991998781321
*/
