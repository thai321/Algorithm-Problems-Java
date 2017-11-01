public class Factors {

  public static void main(String[] args) {
    for(int i = 1; i <= 100; i++) {
      System.out.print("Factors of a number " + i + " are ");

      for(int j = 1; j <= i; j++) {
        if(i % j == 0) {
          System.out.print(" " + j);
        }
      }
      System.out.println();
    }
  }
}
/*
Factors of a number 1 are  1
Factors of a number 2 are  1 2
Factors of a number 3 are  1 3
Factors of a number 4 are  1 2 4
Factors of a number 5 are  1 5
Factors of a number 6 are  1 2 3 6
Factors of a number 7 are  1 7
Factors of a number 8 are  1 2 4 8
Factors of a number 9 are  1 3 9
Factors of a number 10 are  1 2 5 10
...
*/
