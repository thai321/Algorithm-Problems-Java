import java.util.Scanner;

public class Multiplication {

  public static void main(String[] args) {
    int n, i;

    System.out.println("Enter the number to print multiplication: ");
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();

    for(i = 1; i <= 10; i++) {
      System.out.println(n + "*" + i + "=" + (n*i));
    }
  }
}

/*
Enter the number to print multiplication:
7
7*1=7
7*2=14
7*3=21
7*4=28
7*5=35
7*6=42
7*7=49
7*8=56
7*9=63
7*10=70
*/
