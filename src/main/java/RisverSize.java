import java.util.ArrayList;
import java.util.List;

public class RisverSize {

  //USING RECURSIVE DFS
  public static void main(String[] args) {

    int[][] matrix = {{1, 0, 0, 1, 0}, {1, 0, 1, 0, 0}, {0, 0, 1, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 0}};

    final List<Integer> sizes = riverSizes(matrix);
    System.out.println(sizes);

  }


  public static List<Integer> riverSizes(int[][] matrix) {
    // Write your code here.

    List<Integer> riverSizes = new ArrayList<>();

    boolean[][] visited = new boolean[matrix.length][matrix[0].length];

    for (int i = 0; i < matrix.length; i++) {

      for (int j = 0; j < matrix[0].length; j++) {

        int riverSize = dfs(matrix, i, j, visited, 0);
        if (riverSize > 0) {
          riverSizes.add(riverSize);
        }
      }

    }

    return riverSizes;
  }

  private static int dfs(int[][] matrix, int i, int j, boolean[][] visited, int riverSize) {

    if (visited[i][j]) {
      return riverSize;
    }

    visited[i][j] = true;

    if (matrix[i][j] == 0) {
      return riverSize;
    }

    riverSize++;

    List<int[]> neighbours = getNeighbours(i, j, matrix);

    for (int[] neighbour : neighbours) {
      riverSize = dfs(matrix, neighbour[0], neighbour[1], visited, riverSize);
    }

    return riverSize;
  }

  private static List<int[]> getNeighbours(int i, int j, int[][] matrix) {

    int noOfRows = matrix.length;
    int noOfCols = matrix[0].length;

    List<int[]> neighbours = new ArrayList<>();

    //UP
    if (i - 1 >= 0) {
      neighbours.add(new int[]{i - 1, j});
    }

    //DOWN
    if (i + 1 < noOfRows) {
      neighbours.add(new int[]{i + 1, j});
    }

    //LEFT
    if (j - 1 >= 0) {
      neighbours.add(new int[]{i, j - 1});
    }

    //right
    if (j + 1 < noOfCols) {
      neighbours.add(new int[]{i, j + 1});
    }

    return neighbours;
  }
}
