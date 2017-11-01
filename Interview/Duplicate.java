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

    System.out.println(duplicatesSet);
  }
}
