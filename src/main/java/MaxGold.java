public class MaxGold {

  public static void main(String[] args) {

    //final int maximumGold = getMaximumGold(new int[][]{{1, 3, 3}, {2, 1, 4}, {0, 6, 4}});
    //final int maximumGold = getMaximumGold(new int[][]{{1, 3, 1, 5}, {2, 2, 4, 1}, {5, 0, 2, 3}, {0, 6, 1, 2}});
    final int maximumGold = getMaximumGold(new int[][]{{10, 33, 13, 15}, {22, 21, 04, 1}, {5, 0, 2, 3}, {0, 6, 14, 2}});
    System.out.println("maximumGold=" + maximumGold);
  }

  public static int getMaximumGold(int[][] grid) {

    int[][] goldCollArr = new int[grid.length][grid[0].length];

    for (int row = 0; row < grid.length; row++) {
      goldCollArr[row][grid.length - 1] = grid[row][grid.length - 1];
    }

    for (int col = grid.length - 2; col >= 0; col--) {

      for (int row = 0; row < grid.length; row++) {

        int gold = 0;

        //right
        if (col + 1 < grid[0].length) {
          gold = goldCollArr[row][col + 1];
        }

        if (isRightUp(row, col, grid.length, grid[0].length)) {
          if (gold < goldCollArr[row - 1][col + 1]) {
            gold = goldCollArr[row - 1][col + 1];
          }
        }

        if (isRightDown(row, col, grid.length, grid[0].length)) {
          if (gold < goldCollArr[row + 1][col + 1]) {
            gold = goldCollArr[row + 1][col + 1];
          }

        }
        goldCollArr[row][col] = grid[row][col] + gold;
      }
    }

    System.out.println("noOfRow=" + grid.length);

    System.out.println("noOfCol=" + grid[0].length);

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        System.out.print(goldCollArr[i][j] + " , ");
      }
      System.out.println();
    }
    System.out.println("\n");

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        System.out.print(grid[i][j] + " , ");
      }
      System.out.println();
    }

    int maxGold = 0;

    for (int row = 0; row < grid.length; row++) {
      if (maxGold <= goldCollArr[row][0]) {
        maxGold = goldCollArr[row][0];
      }
    }

    return maxGold;
  }

  private static boolean isRightUp(int row, int col, int noOfRows, int noOfCols) {

    //right->up
    if (row - 1 >= 0 && col + 1 < noOfCols) {
      return true;
    }
    return false;
  }

  private static boolean isRightDown(int row, int col, int noOfRows, int noOfCols) {

    //right->up
    if (row + 1 < noOfRows && col + 1 < noOfCols) {
      return true;
    }
    return false;
  }
}
