import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FourNumSum {

  public static void main(String[] args) throws Exception {
    //{"array": [7, 6, 4, -1, 1, 2], "targetSum": 16}

    fourNumberSum(new int[]{7, 6, 4, -1, 1, 2},16);
  }
  public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
    // Write your code here.

    Map<Integer, List<Integer[]>> map = new HashMap<>();
    Set<List<Integer>> set = new HashSet<>();

    for (int i = 0; i < array.length - 1; i++) {
      for (int j = i + 1; j < array.length; j++) {

        int sum = array[i] + array[j];
        int diff = targetSum - sum;

        List<Integer[]> tmp = map.get(diff);
        if (tmp != null) {

          for (Integer[] pair : tmp) {
            System.out.println(diff + " diff| " + pair[0] + "," + pair[1]);
            if ((array[i] != pair[0] && array[i] != pair[1]) && (array[j] != pair[0] && array[j] != pair[1])) {
              Integer[] quad = new Integer[]{array[i], array[j], pair[0], pair[1]};
              Arrays.sort(quad);
              set.add(Arrays.asList(quad));
            }
          }
        }
        map.putIfAbsent(sum,new ArrayList<>());
        map.get(sum).add(new Integer[]{array[i], array[j]});
        System.out.println(sum + " | " + array[i] + "," + array[j]);


      }

    }

    List<Integer[]> res = new ArrayList<>();
    for (List<Integer> quad : set) {
      //res.add(quad.toArray(Integer[]::new));
      res.add(quad.stream().toArray(Integer[]::new));
    }

    return res;
  }
}
