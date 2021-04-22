import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class TaskWorker {


  public static void main(String[] args) {

    int noOfPersons = 4;
    int[][] tasks1 = {{6, 20}, {15, 10}};
    int[][] tasks2 = {{15, 10},{6, 27}};
    int[][] tasks3 = {{6, 10},{6, 20}};

    Arrays.sort(tasks2, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return Integer.compare(o2[1],o1[1]);
      }
    });

    final int maxTimeUnit = calTime3(noOfPersons,tasks2);

    System.out.println(maxTimeUnit);
  }

  public static int calTime(int noOfPersons,int[][] tasks ) {

    List<Integer> queue = new LinkedList<>();
    int result = 0;

    for (int i = 0; i < tasks.length; i++) {

      int noOfTasks = tasks[i][0];
      int timeUnit = tasks[i][1];

      for (int j = 0; j < noOfTasks; j++) {

        if (queue.size() >= noOfPersons) {

          //queue full
          int max = 0;
          max = getMaxTimeUnit(queue);

          result = result + max;

        }
        queue.add(timeUnit);
      }
    }

    if (!queue.isEmpty()) {
      int max = getMaxTimeUnit(queue);

      result = result + max;
    }

    return result;
  }


  public static int calTime1(int noOfPersons,int[][] tasks) {

    Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return Integer.compare(o1,o2);
      }
    });

    int result = 0;
    //m
    for (int i = 0; i < tasks.length; i++) {

      int noOfTasks = tasks[i][0];
      int timeUnit = tasks[i][1];

      //n
      for (int j = 0; j < noOfTasks; j++) {

        if(queue.size() >= 4){

          Integer tmp = removeAndUpdateQueue(queue);

          result = result + tmp;
        }
        queue.add(timeUnit);
      }

    }

    while(!queue.isEmpty()) {
      Integer tmp = removeAndUpdateQueue(queue);
      result = result + tmp;
    }



    return result;
  }


  public static int calTime2(int noOfPersons,int[][] tasks ) {

    Stack<Integer> stack = new Stack<>();

    int result = 0;
    for (int i = 0; i < tasks.length; i++) {

      int noOfTasks = tasks[i][0];
      int timeUnit = tasks[i][1];

      for (int j = 0; j < noOfTasks; j++) {

        if(stack.size() >= 4){

          Integer tmp = removeAndUpdateStack(stack);

          result = result + tmp;
        }
        stack.push(timeUnit);
      }

    }

    while(!stack.isEmpty()) {
      Integer tmp = removeAndUpdateStack(stack);
      result = result + tmp;
    }



    return result;
  }

  private static Integer removeAndUpdateStack(Stack<Integer> stack) {
    Integer tmp = stack.pop();

    while(!stack.isEmpty() && stack.peek() == tmp){
      stack.pop();
    }

    List<Integer> list = new ArrayList();
    while (!stack.isEmpty()){
      list.add(stack.pop());
    }

    for(Integer val : list){
      stack.add(val-tmp);
    }
    return tmp;
  }


  private static Integer removeAndUpdateQueue(Queue<Integer> queue) {

    //logK
    Integer tmp = queue.remove();

    //K time
    while(queue.peek() == tmp){
      //logK
      queue.remove();

    }

    List<Integer> list = new ArrayList();
    while (!queue.isEmpty()){
      list.add(queue.remove());
    }

    for(Integer val : list){
      queue.add(val-tmp);
    }
    return tmp;
  }

  private static int getMaxTimeUnit(List<Integer> queue) {
    int prev = 0;
    while (queue.size() != 0) {
      Integer tmpTimeUnit = queue.remove(0);
      prev = Math.max(prev, tmpTimeUnit);
    }
    return prev;
  }

  public static int calTime3(int noOfPersons,int[][] tasks){
    int result = 0;
    int unUsedManHourFromLastTask = 0;
    for(int[] task : tasks){
      Tuple tuple = calTime3(task, noOfPersons, unUsedManHourFromLastTask);
      result += tuple.duration;
      unUsedManHourFromLastTask = tuple.unUsedManHourFromLastTask;
    }
    return result;
  }
  public static Tuple calTime3(int[] task, int noOfPerson, int unUsedManHourFromLastTask){
    int noOfTasks = task[0];
    int timeUnit = task[1];
    noOfTasks = noOfTasks-(unUsedManHourFromLastTask/timeUnit);
    int noOfIterations = noOfTasks/noOfPerson;
    int remainingTask = noOfTasks%noOfPerson;
    unUsedManHourFromLastTask=0;
    if(remainingTask>0){
      noOfIterations+=1;
      unUsedManHourFromLastTask = (noOfPerson-remainingTask)*timeUnit;
    }
    return new Tuple(noOfIterations*timeUnit, unUsedManHourFromLastTask);
  }
  public static class Tuple{
    int duration;
    int unUsedManHourFromLastTask;
    public Tuple(int duration, int unUsedManHourFromLastTask) {
      this.duration = duration;
      this.unUsedManHourFromLastTask = unUsedManHourFromLastTask;
    }
  }

}
