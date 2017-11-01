## Print numbers up to 10 without using loop

```java
public class PrintNumbers {
  public static void main(String[] args) {
    printNumbers(1);
  }

  public static printNumbers(int n) {
    if(n <= 10) {
      System.out.println(n);
      printNumbers(n + 1);
    }
  }
}

/*
1
2
3
4
5
6
7
8
9
10
*/
```

-----

## Bubble Sort

```java
public class BubbleSort {
  public static void main(String[] args) {

    int intArray[] = {9,8,7,5,6};
    System.out.println("Before sorting");
    for(int i : intArray) {
      System.out.println(i);
    }
    bubbleSort(intArray);

    System.out.println("After sorting");
    for(int i : intArray) {
      System.out.println(i);
    }

  }

  private static void bubbleSort(int[] intArray) {
    int temp;
    for(int i = 0; i < intArray.length ; i++) {
      for(int j = 1; j < intArray.length - i; j++) {
        if(intArray[j - 1] > intArray[j]) {
          // Swap the elements
          temp = intArray[j - 1];
          intArray[j - 1] = intArray[j];
          intArray[j] = temp;
        }
      }
    }
  }
}
/*
Before sorting
9
8
7
5
6
After sorting
5
6
7
8
9
*/
```


----

## Floyds Triangle

```java
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
```

-----

## Star Pattern

```java
public class StarPattern {

  public static void main(String[] args) {

    for(int i = 1; i <= 5; i++) {
      for(int j = 1; j <= 5; j++) {
        System.out.print("* ");
      }
      System.out.println();
    }
  }
}
/*
* * * * *
* * * * *
* * * * *
* * * * *
* * * * *
*/
```

----

## Pyramid Pattern

```java
public class PyramidPattern {

  public static void main(String[] args) {
    for(int i = 5; i > 0; i--) {
      for (int j = 1; j <= i; j++ ) {
        System.out.print(j + " ");
      }
      System.out.println();
    }
  }
}
/*
1 2 3 4 5
1 2 3 4
1 2 3
1 2
1
*/
```
----

## Factors

```java
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
```


-----

## Second largest number

```java
import java.util.Arrays;

public class SecondLargest {

  public static void main(String[] args) {
    int array[] = {1,20, 5, 16, 9, 10};

    int largest = array[0];
    for(int el : array) {
      if (el > largest) {
        largest = el;
      }
    }

    int secondLargest = array[0];
    for(int el : array) {
      if(el > secondLargest  && el < largest && secondLargest < largest) {
        secondLargest = el;
      }
    }

    System.out.println(largest); // 20
    System.out.println(secondLargest); // 16
  }
}
```

----

## Duplicate elements in Array

```java
import java.util.HashSet;
import java.util.Set;

public class Duplicate {

  public static void main(String[] args) {
    String duplicates[] = new String[] {"java", "spring", "hibernate", "java"};

    Set nonDuplicatesSet = new HashSet<>();
    Set duplicatesSet = new HashSet<>();
    for(String string : duplicates) {
      if(!nonDuplicatesSet.contains(string)) {
        nonDuplicatesSet.add(string);
      } else {
        duplicatesSet.add(string);
      }
    }

    System.out.println(duplicatesSet); // [java]
  }
}
```

-----

## Missing Number

```java
public class MissingNumber {

  public static void main(String[] args) {

    int[] array = {1,2,3,4,6};
    int missingNumber = getMissingNumber(array,6);
    System.out.println(missingNumber); // 5
  }

  private static int getMissingNumber(int[] array, int n) {
    int expectedSum = (n*(n+1))/2;
    int actualSum = 0;
    for(int i : array) {
      actualSum = actualSum + i;
    }
    return expectedSum - actualSum;
  }
}
```

-----

## Factorial Big Integer

```java
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
```


----

## Swap 2 numbers without using temp

```java
a = a*b;
b = a/b;
a = a/b;

// or

a = a + b;
b = a - b;
a = a - b
```

----

## Print multiplication table

```java
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
```


-----

## Anagram

```java
import java.util.HashMap;

public class Anagram {

  public static void main(String[] args) {
    String str1 = "jasva";
    String str2 = "avasj";

    System.out.println(anagramCheck(str1, str2));
  }

  private static boolean anagramCheck(String str1, String str2) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    char[] str1Chars = str1.toCharArray();

    for(char c : str1Chars) {
      if(!map.containsKey(c)) {
        map.put(c, 1);
      } else {
        map.put(c, map.get(c) + 1);
      }
    }

    char[] str2Chars = str2.toCharArray();
    for(char c : str2Chars) {
      if(!map.containsKey(c)) {
        return false;
      } else {
        map.put(c, map.get(c) - 1);
        if (map.get(c) < 0) {
          return false;
        }
      }
    }

    for(Integer count : map.values()) {
      if (count != 0) {
        return false;
      }
    }

    System.out.println(map);

    return true;
  }
}
```

----

## Sort Character in String

```java
String str = "java"
char chars[] = str.toCharArray();
Arrays.sort(chars);
String sortedStr = new String(chars);
System.out.println(sortedStr); // avaj
```


----

## Duplicates characters from String

```java
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class DuplicateCharacters {

  public static void main(String[] args) {
    displayDuplicate("sandeep");
  }

  private static void displayDuplicate(String str) {

    Map<Character, Integer> map = new HashMap<>();
    char [] characters = str.toCharArray();

    for(char c : characters) {
      if(!map.containsKey(c)) {
        map.put(c, 1);
      } else {
        map.put(c, map.get(c) + 1);
      }
    }

    Set<Map.Entry<Character,Integer>> entrySet = map.entrySet();
    for(Map.Entry<Character, Integer> entry : entrySet) {
      if(entry.getValue() > 1) {
        System.out.printf("%s : %d\n",  entry.getKey(), entry.getValue());
      }
    }
  }
}
// e : 2
```
