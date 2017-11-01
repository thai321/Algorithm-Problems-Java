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
