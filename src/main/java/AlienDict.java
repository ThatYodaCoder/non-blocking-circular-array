import java.util.HashMap;
import java.util.Map;

public class AlienDict {


  public static void main(String[] args) {

    String[] words1 = new String[]{"word", "world", "row"};
    final String order1 = "worldabcefghijkmnpqstuvxyz";

    String[] words2 = new String[]{"hello", "leetcode"};
    final String order2 = "hlabcdefgijkmnopqrstuvwxyz";

    String[] words3 = new String[]{"apple", "app"};
    final String order3 = "abcdefghijklmnopqrstuvwxyz";

    final boolean alienSorted = isAlienSorted(words3, order3);
    System.out.println(alienSorted);
  }

  public static boolean isAlienSorted(String[] words, String order) {

    Map<Character, Integer> map = getCharMap(order);

    for (int i = 0; i < words.length - 1; i++) {

      String word1 = words[i];
      String word2 = words[i + 1];

      if (compareTo(map, word1, word2) > 0) {
        return false;
      }

    }
    return true;

  }

  private static int compareTo(Map<Character, Integer> map, String word1, String word2) {

    int minLen = Math.min(word1.length(), word2.length());
    for (int j = 0; j < minLen; j++) {

      char ch1 = word1.charAt(j);
      char ch2 = word2.charAt(j);

      if (ch1 != ch2) {

        int v1 = map.get(ch1);
        int v2 = map.get(ch2);

        return v1 - v2;

      }
    }

    return word1.length() - word2.length();

  }


  private static Map<Character, Integer> getCharMap(String order) {

    Map<Character, Integer> map = new HashMap<>();

    int startVal = 97;

    for (char ch : order.toCharArray()) {
      map.put(ch, startVal);
      startVal++;
    }
    return map;


  }
}
