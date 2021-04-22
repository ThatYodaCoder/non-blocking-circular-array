import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rec {

  //abcd
  public void print(){


  }

  public static void main(String[] args) {

    System.out.println("MOd:"+ (52%4));

//    String str = "aaaaa";
//    final boolean palindrome = isPalindrome(str, 0, str.length()-1);
//    System.out.println(palindrome);

//    int array[][] = {{1,2,3,4},{10,11,12,5},{9,8,7,6}};
//
//    final List<Integer> integers = spiralTraverse(array);
  }


  public static List<Integer> spiralTraverse(int[][] array) {
    // Write your code here.

    List<Integer> list = new ArrayList<Integer>();

    int noOfRows = array.length;
    int noOfCols = array[0].length;

    int startRow = 0;
    int startCol = 0;

    int endCol = noOfCols - 1;
    int endRow = noOfRows - 1;

    while (startRow <= endRow && startCol <= endCol) {

      for (int i = startCol; i <= endCol; i++) {
        list.add(array[startRow][i]);
      }

      startRow++;

      for (int j = startRow; j <= endRow; j++) {
        list.add(array[j][endCol]);
      }

      endCol--;

      for (int k = endCol; k >= startCol; k--) {

        if(startRow==endRow || startRow > endRow){
          break;
        }
        list.add(array[endRow][k]);
      }

      endRow--;

      for (int l = endRow; l >= startRow; l--) {
        list.add(array[l][startCol]);
      }

      startCol++;
    }
    return list;
  }


  public static boolean isPalindrome(String str,int r,int l) {

    if(r >  l){
      return true;
    }

    if(str.charAt(r) != str.charAt(l)){
      return false;
    }else{
      return isPalindrome(str,r+1,l-1);
    }



  }
}
