import java.util.ArrayList;
import java.util.List;

public class IslandRemover {

  public static void main(String[] args) {

    int[][] matrix =
        {{1, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 1, 1},
        {0, 0, 1, 0, 1, 0},
        {1, 1, 0, 0, 1, 0},
        {1, 0, 1, 1, 0, 0},
        {1, 0, 0, 0, 0, 1}};

    removeIslands(matrix);


  }

  public static int[][] removeIslands(int[][] matrix) {
    // Write your code here.

    for (int i = 0; i < matrix.length; i++) {

      for (int j = 0; j < matrix[0].length; j++) {

        if (isBorder(i, j, matrix)) {
          dfs(i, j, matrix);
        }
      }
    }

    for (int i = 0; i < matrix.length; i++) {

      for (int j = 0; j < matrix[0].length; j++) {

        if (matrix[i][j] == 1) {
          matrix[i][j] = 0;
        } else if (matrix[i][j] == 2) {
          matrix[i][j] = 1;
        }
        System.out.print( matrix[i][j] + " , ");
      }
      System.out.println();
    }

    return matrix;
  }

  private static void dfs(int i, int j, int[][] matrix) {

    if (matrix[i][j] == 0 || matrix[i][j] == 2) {
      return;
    }

    matrix[i][j] = 2;

    List<int[]> neighbours = getNeighbours(i, j, matrix);

    for (int[] neighbour : neighbours) {

      dfs(neighbour[0], neighbour[1], matrix);
    }

  }

  private static boolean isBorder(int i, int j, int[][] matrix) {

    int noOfRows = matrix.length - 1;
    int noOfCols = matrix[j].length - 1;

    if (i == 0 || i == noOfRows) {
      return true;
    }
    if (j == 0 || j == noOfCols) {
      return true;
    }
    return false;
  }

  private static List<int[]> getNeighbours(int i, int j, int[][] matrix) {

    List<int[]> neighbours = new ArrayList<>();

    int noOfRows = matrix.length;
    int noOfCols = matrix[j].length;

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
    //RIGHT
    if (j + 1 < noOfCols) {
      neighbours.add(new int[]{i, j + 1});
    }
    return neighbours;
  }
}
