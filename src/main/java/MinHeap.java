import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeap {

  private List<Integer> heap = new ArrayList<>();

  public MinHeap() {
  }

  public static void main(String[] args) {
     MinHeap mh = new MinHeap();

     List<Integer> list = Arrays.asList(100 ,20, 3 ,2, 101, 30);
     
     int ctr = 0;
     for(Integer num : list){
       mh.insert(num);
       System.out.println("Step:"+ (ctr++) + " :: inserting:"+num + " :: after insert:" + mh);
     }


  }

  public void insert(int num){

    heap.add(num);

    siftUp(heap.size()-1);
  }

  public Integer remove(){

    swap(0,heap.size()-1);
    final Integer valToRem = heap.get(heap.size() - 1);
    heap.remove(heap.size() - 1);

    return valToRem;

  }

  private void siftDown(int currIdx,int endIdx){

    int lcChildIdx = 2*currIdx+1;

    while(lcChildIdx <= endIdx){

      int rcChildIdx = 2*currIdx+2;
      if(rcChildIdx > endIdx){
        rcChildIdx = -1;
      }

      int minChildIdx = -1;
      if(rcChildIdx > 0){
        minChildIdx = minNumIdx(lcChildIdx,rcChildIdx);
      }

      if(heap.get(currIdx) > heap.get(minChildIdx)){
        swap(currIdx,minChildIdx);
        currIdx = minChildIdx;
        lcChildIdx = 2* lcChildIdx + 1;
      }else {
        break;
      }
    }
  }

  private int minNumIdx(int idx1,int idx2){

    if(heap.get(idx1) < heap.get(idx2)){
      return idx1;
    }else {
      return idx2;
    }
  }

  private void siftUp(int currIdx){

    int parentIdx = (currIdx-1)/2;

    while(currIdx > 0 && heap.get(currIdx) < heap.get(parentIdx)){
       swap(currIdx,parentIdx);
       currIdx = parentIdx;
       parentIdx = (currIdx-1)/2;
    }
  }

  private void swap(int idx1, int idx2){
    Integer tmp = heap.get(idx1);
    heap.set(idx1, heap.get(idx2));
    heap.set(idx2, tmp);
  }

  @Override
  public String toString() {
    return "MinHeap{" + "heap=" + heap + '}';
  }
}
