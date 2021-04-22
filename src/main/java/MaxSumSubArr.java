import java.util.ArrayList;
import java.util.List;

public class MaxSumSubArr {

  public static void main(String[] args) {

    final int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    final int[] arr2 = {5,4,-1,7,8};

    final int i = maxSubArray(arr2);
    System.out.println(i);
  }

  public static int maxSubArray(int[] nums) {

    int maxSumSoFar = Integer.MIN_VALUE;
    int currMaxSum = 0;

    List<Integer> currSubArr = new ArrayList<>();
    List<Integer> maxSubArrSoFar = null;

    for (int i = 0; i < nums.length; i++) {

      if (currMaxSum < 0 && nums[i] > currMaxSum) {
        currMaxSum = nums[i];

        currSubArr = new ArrayList<>();
        currSubArr.add(nums[i]);
      } else {
        currMaxSum = currMaxSum + nums[i];
        currSubArr.add(nums[i]);
      }

     // System.out.println(currMaxSum);

      if (currMaxSum > maxSumSoFar) {
        maxSumSoFar = currMaxSum;
        maxSubArrSoFar = new ArrayList<>(currSubArr);
      }

    }

    System.out.println(maxSubArrSoFar);

    return maxSumSoFar;

  }
}
