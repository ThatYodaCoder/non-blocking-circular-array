import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TopologicalSort {

  public static void main(String[] args) {

    main(null);

    List<Integer> jobs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
    List<Integer[]> deps = new ArrayList<>();
    Integer[][] depArr = new Integer[][]{{3, 1}, {8, 1}, {8, 7}, {5, 7}, {5, 2}, {1, 4}, {6, 7}, {1, 2}, {7, 6}};
    for (Integer[] dep : depArr) {
      deps.add(dep);
    }

    final List<Integer> jobOrder = topologicalSort(jobs, deps);

    System.out.println("jobOrder:" + jobOrder);
  }

  public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
    // Write your code here.

    Map<Integer, JobNode> dag = createDAG(jobs, deps);

    Stack<Integer> stack = new Stack<Integer>();
    for (JobNode jobNode : dag.values()) {
      boolean foundCycle = dfs(jobNode, null);
      if (foundCycle) {
        return Collections.emptyList();
      }
    }

    List<Integer> jobOrder = new ArrayList<Integer>();
    while (!stack.isEmpty()) {
      jobOrder.add(stack.pop());
    }

    return jobOrder;
  }

  private static boolean dfs(JobNode node, Stack<Integer> stack) {

    if (node.visiting) {
      return true;
    }
    node.visiting = true;

    for (JobNode neighbour : node.neighbours) {

      if (!neighbour.visited) {
        boolean foundCycle = dfs(neighbour, stack);
        if (foundCycle) {
          return true;
        }
      }
    }

    if (!node.visited) {
      stack.push(node.jobId);
      node.visited = true;
      node.visiting = false;
    }

    return false;
  }

  private static Map<Integer, JobNode> createDAG(List<Integer> jobs, List<Integer[]> deps) {

    Map<Integer, JobNode> dag = new HashMap<>();
    for (Integer jobId : jobs) {
      dag.put(jobId, new JobNode(jobId));
    }

    for (Integer[] dep : deps) {
      JobNode preReqJob = getJobNode(dag, dep[0]);
      JobNode dependentJob = getJobNode(dag, dep[1]);
      preReqJob.addNeighbours(dependentJob);
    }

    //Print directed acyclic graph
    // for(JobNode value: dag.values()){
    // 	System.out.println(value);
    // }

    return dag;
  }

  private static JobNode getJobNode(Map<Integer, JobNode> dag, Integer jobId) {

    JobNode nd = dag.get(jobId);
    if (nd == null) {
      nd = new JobNode(jobId);
      dag.put(jobId, nd);
    }
    return nd;
  }

  static class JobNode {

    public Integer jobId;
    public List<JobNode> neighbours;

    public boolean visited = false;
    public boolean visiting = false;

    public JobNode(Integer jobId) {
      this.jobId = jobId;
      this.neighbours = new ArrayList<>();
    }

    public void addNeighbours(JobNode nd) {
      neighbours.add(nd);
    }

    public String toString() {
      return "JobId=" + this.jobId + ", neighbours=" + this.neighbours;
    }
  }
}
