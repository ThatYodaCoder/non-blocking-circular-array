import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Print ordering of characters
public class AlienDictionary {

  public static void main(String[] args) {

    List sortedList1 = Arrays.asList("baa", "abcd", "abca", "cab", "cad");
    List sortedList2 = Arrays.asList("caa", "aaa", "aab");
    List sortedList3 = Arrays.asList("wrt", "wrf", "er", "ett", "rftt");

    final Map<Character, Node> dag = createDag(sortedList3);

    final List<Node> queue = findNodesWithZeroIndegree(dag);

    List<Character> sortOrder = new ArrayList<>();
    while (!queue.isEmpty()) {

      final Node node = queue.remove(0);
      sortOrder.add(node.ch);

      for (Node neb : node.neighbours) {
        neb.indegree--;
        if (neb.indegree == 0) {
          queue.add(neb);
        }
      }
    }

    System.out.println("sort order=" + sortOrder);
  }

  public static void traverseGraph() {

  }

  private static List<Node> findNodesWithZeroIndegree(Map<Character, Node> dag) {

    List<Node> list = new LinkedList<>();
    for (Node node : dag.values()) {
      if (node.indegree == 0) {
        list.add(node);
      }
    }
    return list;
  }

  public static Map<Character, Node> createDag(List<String> sortedWordList) {

    Map<Character, Node> map = new HashMap<>();

    for (int i = 0; i < sortedWordList.size() - 1; i++) {

      String word1 = sortedWordList.get(i);
      String word2 = sortedWordList.get(i + 1);

      int minLen = Math.min(word1.length(), word2.length());

      for (int ctr = 0; ctr < minLen; ctr++) {

        if (word1.charAt(ctr) != word2.charAt(ctr)) {

          Node pred = map.get(word1.charAt(ctr));
          Node succ = map.get(word2.charAt(ctr));

          if (pred == null) {
            pred = new Node(word1.charAt(ctr));
            map.put(word1.charAt(ctr), pred);
          }

          if (succ == null) {
            succ = new Node(word2.charAt(ctr));
            map.put(word2.charAt(ctr), succ);
          }

          pred.neighbours.add(succ);
          succ.indegree++;

          break;
        }

      }


    }
    return map;

  }


  private static class Node {

    private final char ch;
    private final List<Node> neighbours;
    private int indegree;

    public Node(char ch) {
      this.ch = ch;
      this.neighbours = new ArrayList<>();
    }
  }
}
