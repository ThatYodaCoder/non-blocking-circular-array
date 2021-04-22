package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AvgSumArr {

  public static void main(String[] args) throws Exception {

    Set<List<Integer>> set = new HashSet<>();
    set.add(Arrays.asList(1, 2, 3, 4));
    set.add(Arrays.asList(1, 2, 3, 4));
    set.add(Arrays.asList(1, 3, 2, 4));
    System.out.println(set);

    Set<Integer[]> set1 = new HashSet<>();
    set1.add(new Integer[]{1, 2, 3, 4});
    set1.add(new Integer[]{1, 2, 3, 4});
    set1.add(new Integer[]{1, 4, 3, 4});
    System.out.println(set1);

    int arr[] = new int[]{2, 1, 4, 1};
    //find(arr);

    //prefixSum(new int[]{10, 20, 10, 5, 15});
    int n = arr.length;
    System.out.println(countSubarrays(arr, n));

  }

  private static int find(int[] arr) {

    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {

        System.out.println("arr: {" + arr[i] + "," + arr[j] + "}");
      }
    }

    return 0;
  }

  private static void prefixSum(int[] arr) {

    int prefixSum[] = new int[arr.length];
    prefixSum[0] = arr[0];

    int sum = 0;
    for (int i = 1; i < arr.length; i++) {
      prefixSum[i] = prefixSum[i - 1] + arr[i];
    }

    for (int psum : prefixSum) {
      System.out.println(psum + ",");
    }
  }

  // Function to return the count of sub-arrays
  // such that the average of elements present
  // in the sub-array is greater than the
  // average of the elements not present
  // in the sub-array
  static int countSubarrays(int a[], int n) {
    // Initialize the count variable
    int count = 0;

    // Initialize prefix sum array
    int[] pre = new int[n + 1];
    Arrays.fill(pre, 0);

    // Preprocessing prefix sum
    for (int i = 1; i < n + 1; i++) {
      pre[i] = pre[i - 1] + a[i - 1];
    }

    for (int i = 1; i < n + 1; i++) {
      for (int j = i; j < n + 1; j++) {

        // Calculating sum and count
        // to calculate averages
        int sum1 = pre[j] - pre[i - 1];
        int count1 = j - i + 1;
        int sum2 = pre[n] - sum1;
        int count2 = ((n - count1) == 0) ? 1 : (n - count1);

        // Calculating averages
        int includ = sum1 / count1;
        int exclud = sum2 / count2;

        // Increment count if including avg
        // is greater than excluding avg
        if (includ > exclud) {
          count++;
        }
      }
    }
    return count;
  }
}
