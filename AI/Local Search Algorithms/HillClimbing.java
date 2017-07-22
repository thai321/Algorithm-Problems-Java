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
