import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class TrieTest {

  public static void main(String[] args) {

    //Trie trie = createTrie(new String[]{"this", "that", "them", "OLA"});

    char[][] board = {{'t', 'h', 'i', 's', 'i', 's', 'a'}, {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
        {'b', 'x', 'x', 'x', 'x', 'e', 'b'}, {'x', 'o', 'g', 'g', 'l', 'x', 'o'}, {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
        {'R', 'E', 'P', 'E', 'A', 'd', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'N', 'O', 'T', 'R', 'E', '-', 'P'},
        {'x', 'x', 'D', 'E', 'T', 'A', 'E'}};

    String[] words = {"this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"};

    final List<String> strings = boggleBoard(board, words);
    System.out.println(strings);

  }

  public static List<String> boggleBoard(char[][] board, String[] words) {
    // Write your code here.

    Trie trie = createTrie(words);

    //printTrie(map);
    // boolean found = search(map,"BOO");
    // System.out.print("found"+found);

    List<String> res = boggleBoard(board, trie.rootNode);

    return res;
  }

  private static class Tuple {

    public TrieNode currRootNode;
    public int[] pos;
    public int[] parentPos;

    public Tuple(TrieNode currRootNode, int[] pos,int[] parentPos) {
      this.currRootNode = currRootNode;
      this.pos = pos;
      this.parentPos = parentPos;
    }
  }

  private static List<String> boggleBoard(char[][] board, TrieNode root) {

    Set<String> result = new HashSet<>();

    for (int i = 0; i < board.length; i++) {

      for (int j = 0; j < board[i].length; j++) {

        TrieNode rootNode = root;
        //Stack<int[]> stack = new Stack<>();
        Stack<Tuple> stack = new Stack<>();
        Stack<TrieNode> trieNodeStack = new Stack<>();
        boolean[][] visitedArr = new boolean[board.length][board[0].length];

        stack.push(new Tuple(rootNode, new int[]{i, j},null));
        //trieNodeStack.push(rootNode.map.get(board[i][j]));

        while (!stack.isEmpty()) {

          Tuple tuple = stack.pop();
          int[] pos = tuple.pos;

          if (visitedArr[pos[0]][pos[1]]) {
            continue;
          }

          Character ch = board[pos[0]][pos[1]];

          TrieNode nd = tuple.currRootNode.map.get(ch);

          if (nd != null && nd.map.containsKey('*')) {
            result.add(nd.finalWord);
            break;
          }

          if (nd != null) {
            System.out.println("finding neighbours of ::" + ch + "::" + pos[0] + "," + pos[1]);
            visitedArr[pos[0]][pos[1]] = true;

            List<int[]> neighbours = getNeighbours(board, pos);

            System.out.print("neighbours of ::" + ch + " are:: ");
            for (int[] neighbour : neighbours) {
              stack.push(new Tuple(nd, neighbour,pos));

              System.out.print(board[neighbour[0]][neighbour[1]]);
              System.out.print(", ");
            }
            System.out.println("\n");
            //rootNode = nd;

          } else {
            if(tuple.parentPos != null) {
              visitedArr[tuple.parentPos[0]][tuple.parentPos[1]] = false;
            }
          }
        }


      }
    }
    return new ArrayList<>(result);
  }


  private static List<int[]> getNeighbours(char[][] board, int[] pos) {

    int row = pos[0];
    int col = pos[1];

    int rowLen = board.length;
    int colLen = board[0].length;

    List<int[]> neighbours = new ArrayList<>();

    //RIGHT
    if (col + 1 < colLen) {
      neighbours.add(new int[]{row, col + 1});
    }

    //CROSS DOWN
    if (row + 1 < rowLen && col + 1 < colLen) {
      neighbours.add(new int[]{row + 1, col + 1});
    }

    // DOWN
    if (row + 1 < rowLen) {
      neighbours.add(new int[]{row + 1, col});
    }

    // cross
    if (row - 1 >= 0 && col - 1 >= 0) {
      neighbours.add(new int[]{row - 1, col - 1});
    }

    //left
    if (col - 1 >= 0) {
      neighbours.add(new int[]{row, col - 1});
    }

    //cross
    if (row - 1 >= 0 && col - 1 >= 0) {
      neighbours.add(new int[]{row - 1, col - 1});
    }

    //up
    if (row - 1 >= 0) {
      neighbours.add(new int[]{row - 1, col});
    }

    //cross
    if (row - 1 >= 0 && col + 1 < colLen) {
      neighbours.add(new int[]{row - 1, col + 1});
    }

    return neighbours;
  }

  private static void dfs() {

  }

  private static Trie createTrie(String[] words) {

    Map<Character, TrieNode> map = new HashMap<>();
    TrieNode rootNode = new TrieNode();
    rootNode.map = map;

    for (String word : words) {

      TrieNode rootNd = map.get(word.charAt(0));
      if (rootNd == null) {
        rootNd = new TrieNode();
        map.put(word.charAt(0), rootNd);
      }

      TrieNode nd = rootNd;
      for (int i = 1; i < word.length(); i++) {

        Character ch = word.charAt(i);
        TrieNode lNd = nd.map.get(ch);
        if (lNd == null) {
          lNd = new TrieNode();
        }
        nd.map.put(ch, lNd);
        nd = lNd;
      }
      nd.map.put('*', null);
      nd.finalWord = word;

    }

    Trie trie = new Trie(rootNode);

    return trie;
  }

  private static void printTrie(Map<Character, TrieNode> map) {
    for (Map.Entry<Character, TrieNode> entry : map.entrySet()) {
      System.out.println("\n");
      Character ch = entry.getKey();
      System.out.print(ch + "->");
      traverseTrie(entry.getValue());
    }
  }

  private static void traverseTrie(TrieNode nd) {

    if (nd == null) {
      System.out.println("\n");
      return;
    }

    for (Map.Entry<Character, TrieNode> entry : nd.map.entrySet()) {

      System.out.print(entry.getKey() + "->");
      traverseTrie(entry.getValue());
    }
  }

  private static boolean search(Map<Character, TrieNode> map, String word) {

    TrieNode nd = map.get(word.charAt(0));
    if (nd == null) {
      return false;
    }

    for (int i = 1; i < word.length(); i++) {

      Character ch = word.charAt(i);

      if (!nd.map.containsKey(ch)) {
        //System.out.println("here-0");
        return false;
      }

      nd = nd.map.get(ch);
    }
    if (nd.map.containsKey('*')) {
      return true;
    }

    return false;
  }

  private static class Trie {

    public TrieNode rootNode;

    public Trie(TrieNode rootNode) {
      this.rootNode = rootNode;
    }

  }

  private static class TrieNode {

    public String finalWord = "";
    public Map<Character, TrieNode> map = new HashMap<>();

    public String toString() {
      return "" + map.keySet();
    }

  }
}
