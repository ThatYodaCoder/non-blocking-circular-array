import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {


  public static  String minWinString(String s, String t){

    Map<Character,Integer> cntPerChar = new HashMap<>();
    for(Character ch : t.toCharArray()){
      final Integer cnt = cntPerChar.getOrDefault(ch, 0);
      cntPerChar.put(ch,cnt+1);
    }

    final int requiredLen = cntPerChar.size();

    int left = 0;
    int right = 0;

    while(left < right){

      char ch = s.charAt(left);


    }


    return "";
  }


  public static void main(String[] args) {

  }
}
