/**
 * Find the element before which all the elements are smaller than it, and after which all are greater
 */
public class FindElement {

  public static void main(String[] args) {

    int arr[] = {5, 1, 4, 3, 6, 8, 10, 7, 9};

    final int index = findElement(arr);

    System.out.println("element:"+ arr[index]);

  }

  public static int findElement(int[] arr) {

    int result = -1;
    //O(N) time
    for (int i = 1; i < arr.length; i++) {

      int left = i - 1;
      int right = i + 1;

      while (left >= 0 && arr[i] > arr[left]) {
        left--;
      }

      while (right < arr.length - 1 && arr[i] < arr[right]) {
        right++;
      }

      if(left==-1 && right == arr.length-1){
        return i;
      }

    }

    return -1;
  }
}
