import java.util.ArrayList;

public class FindingSubArray {

  public static void main(String[] args) {

    //int arr[] = new int[]{2, 1, 4, 1};
    int arr[] = new int[]{6, 3, 5};
    int n = arr.length;

    ArrayList<Integer> a = new ArrayList<>();
    ArrayList<Integer> b = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        double avg1 = getAverage(i, j, arr);
        double avg2 = getAverageOfRest(i, j, arr);
        //System.out.println(avg1+" "+avg2);
        if (avg1 > avg2) {
          a.add(i);
          b.add(j);
        }
      }
    }

    System.out.println(a.size());
    for (int i = 0; i < a.size(); i++) {
      System.out.println(a.get(i) + " " + b.get(i));
    }
  }

  private static double getAverageOfRest(int i, int j, int[] arr) {
    double result = 0;
    int count = 0;

    for (int k = 0; k < i; k++) {
      result += arr[k];
      count++;
    }
    for (int k = j + 1; k < arr.length; k++) {
      result += arr[k];
      count++;
    }
    if (count > 0) {
      return result / count;
    } else {
      return 0;
    }
  }

  private static double getAverage(int i, int j, int[] arr) {
    double result = 0;
    int count = 0;
    for (int k = i; k <= j; k++) {
      result += arr[k];
      count++;
    }

    if (count > 0) {
      return result / count;
    } else {
      return 0;
    }
  }
}