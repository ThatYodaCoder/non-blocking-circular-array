import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrieRecurTest {

  //check if node is already visited
  //check if the trienode contains character
  //check if the curr trie node contains end symbol



  public static void main(String[] args) {

    //Trie trie = createTrie(new String[]{"this", "that", "them", "OLA"});

    char[][] board =
        {
        {'t', 'h', 'i', 's', 'i', 's', 'a'},
        {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
        {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
        {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
        {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
        {'R', 'E', 'P', 'E', 'A', 'd', 'x'},
        {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
        {'N', 'O', 'T', 'R', 'E', '-', 'P'},
        {'x', 'x', 'D', 'E', 'T', 'A', 'E'}};

    //String[] words = {"this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"};

    String[] words = {"REPEATED"};

    final List<String> strings = boggleBoard(board, words);
    System.out.println(strings);

  }

  public static List<String> boggleBoard(char[][] board, String[] words) {

    Trie trie =  createTrie(words);

    // Write your code here.
    return boggleBoard(board, trie);
  }

  private static List<String> boggleBoard(char[][] board, Trie trie){

    Set<String> set = new HashSet<>();

    TrieNode rootNode = trie.rootNode;
    boolean[][] visited = new boolean[board.length][board[0].length];

    for(int i = 0; i < board.length; i++){

      for(int j = 0; j < board[0].length; j++){

        dfs(i, j, board, visited, rootNode, set);

      }
    }

    return new ArrayList<>(set);
  }

  private static void dfs(int i, int j, char[][] board, boolean[][] visited, TrieNode node, Set<String> wordsFound ){

    if(visited[i][j]){
      return;
    }

    final Character ch = board[i][j];

    if(!node.map.containsKey(ch)){
      return;
    }


    visited[i][j] = true;

    TrieNode currNode = node.map.get(ch);

    if(currNode.map.containsKey('*')){
      wordsFound.add(currNode.finalWord);
    }

    List<int[]> neighbours = getNeighbours(i, j, board);

    for(int[] neighbour : neighbours){
      dfs(neighbour[0], neighbour[1], board, visited, currNode, wordsFound);
    }

    visited[i][j] = false;
  }


  private static List<int[]> getNeighbours(int i, int j, char[][] board){

    int noOfRows = board.length;
    int noOfCols = board[0].length;

    int row = i;
    int col = j;

    List<int[]> neighbours = new ArrayList<>();

    //up
    if(row-1 >= 0){
      neighbours.add(new int[]{row-1,col});
    }
    //down
    if(row+1 < noOfRows){
      neighbours.add(new int[]{row+1,col});
    }
    //left
    if(col-1 >= 0){
      neighbours.add(new int[]{row,col-1});
    }
    //right
    if(col+1 < noOfCols){
      neighbours.add(new int[]{row,col+1});
    }

    //CROSS
    if( row+1 < noOfRows && col+1 < noOfCols){
      neighbours.add(new int[]{row+1,col+1});
    }
    if( row+1 < noOfRows && col-1 >= 0){
      neighbours.add(new int[]{row+1,col-1});
    }
    if( row-1 >=0 && col-1 >= 0){
      neighbours.add(new int[]{row-1,col-1});
    }
    if( row-1 >=0 && col+1 < noOfCols){
      neighbours.add(new int[]{row-1,col+1});
    }
    return neighbours;
  }

  private static Trie createTrie(String[] words){

    TrieNode rootNode = new TrieNode();
    Trie trie = new Trie(rootNode);

    for(String word : words){

      TrieNode nd = rootNode.map.get(word.charAt(0));
      if(nd == null){
        nd = new TrieNode();
        rootNode.map.put(word.charAt(0),nd);
      }

      for(int i = 1; i < word.length(); i++){

        Character ch = word.charAt(i);
        TrieNode currNd  =  nd.map.get(ch);

        if(currNd == null){
          currNd = new TrieNode();
          nd.map.put(ch,currNd);
        }
        nd = currNd;
      }
      nd.map.put('*',null);
      nd.finalWord = word;
    }

    return trie;

  }

  private static class Trie{

    public TrieNode rootNode;

    public Trie(TrieNode nd){
      this.rootNode = nd;
    }
  }

  public static class TrieNode{
    public Map<Character,TrieNode> map = new HashMap<>();
    public String finalWord = "";
  }
}
