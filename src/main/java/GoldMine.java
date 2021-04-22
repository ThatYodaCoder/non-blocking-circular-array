import java.util.ArrayList;
import java.util.List;

public class GoldMine {

  public static void main(String[] args) {

    //final int maximumGold = getMaximumGold(new int[][]{{1, 3, 3}, {2, 1, 4}, {0, 6, 4}});
    //final int maximumGold = getMaximumGold(new int[][]{{1, 3, 1, 5}, {2, 2, 4, 1}, {5, 0, 2, 3}, {0, 6, 1, 2}});
    final int maximumGold = getMaximumGold(new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}});
    System.out.println("maximumGold=" + maximumGold);
  }

  public static int getMaximumGold(int[][] grid) {

    boolean[][] visited = new boolean[grid.length][grid[0].length];

    int goldVal = 0;
    for (int i = 0; i < grid.length; i++) {

      for (int j = 0; j < grid[0].length; j++) {

        int tmp = dfs(i, j, grid, visited);

        if (tmp > goldVal) {
          goldVal = tmp;
        }
      }
    }

    return goldVal;
  }

  private static int dfs(int i, int j, int[][] grid, boolean[][] visited) {

    if (visited[i][j]) {
      return 0;
    }

    if (grid[i][j] == 0) {
      return 0;
    }

    visited[i][j] = true;

    List<int[]> neighbours = getNeighbours(i, j, grid);

    int tmp = 0;

    //left
    if (j - 1 >= 0) {
      tmp = Math.max(tmp, dfs(i, j - 1, grid, visited));
    }

    //up
    if (i - 1 >= 0) {
      tmp = Math.max(tmp, dfs(i - 1, j, grid, visited));
    }

    //right
    if (j + 1 < grid[0].length) {
      tmp = Math.max(tmp, dfs(i, j + 1, grid, visited));
    }

    //down
    if (i + 1 < grid.length) {
      tmp = Math.max(tmp, dfs(i + 1, j, grid, visited));
    }

    int sum = tmp + grid[i][j];
    visited[i][j] = false;
    return sum;
  }

  private static List<int[]> getNeighbours(int i, int j, int[][] grid) {

    List<int[]> neighbours = new ArrayList<>();
    //left
    if (j - 1 >= 0) {
      neighbours.add(new int[]{i, j - 1});
    }

    //up
    if (i - 1 >= 0) {
      neighbours.add(new int[]{i - 1, j});
    }

    //right
    if (j + 1 < grid[0].length) {
      neighbours.add(new int[]{i, j + 1});
    }

    //down
    if (i + 1 < grid.length) {
      neighbours.add(new int[]{i + 1, j});
    }

    return neighbours;
  }
}
