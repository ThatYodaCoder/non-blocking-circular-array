import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArrayCircularAccessorTest {

  private final ArrayCircularAccessor circularAccessor = new ArrayCircularAccessor();

  @Test
  void testGetValue() throws InterruptedException {

    long start = System.currentTimeMillis();
    //index::count
    final Map<Integer, AtomicInteger> map = getTestMap();

    int size = 32;
    int noOfItr = 1_000_000;

    //Create threads and invode CircularAccessor.getgetValue() method inside run method of every thread.
    Thread[] threads = createThreads(map, size, noOfItr);
    joinThreads(size, threads);

    // total access count per index = noOfItr * noOfThreads / arr.length;
    int expectedAccessCountPerIdx = 4000000;
    for (Entry<Integer, AtomicInteger> entry : map.entrySet()) {
      Assertions.assertEquals(expectedAccessCountPerIdx, entry.getValue().get());
    }
    System.out.println("Time:"+(System.currentTimeMillis()-start));
  }

  private Thread[] createThreads(Map<Integer, AtomicInteger> map, int size, int noOfItr) {
    Thread[] threads = new Thread[size];
    for (int ctr = 0; ctr < size; ctr++) {
      threads[ctr] = new Thread(getThread(map, noOfItr));
      threads[ctr].start();
    }
    return threads;
  }

  private Runnable getThread(Map<Integer, AtomicInteger> map, final int noOfItr) {
    return new Runnable() {
      @Override
      public void run() {
        final String name = Thread.currentThread().getName();
        for (int cnt = 0; cnt < noOfItr; cnt++) {

          final int index = circularAccessor.getValue();

          AtomicInteger count = map.get(index);
          count.incrementAndGet();

          //System.out.println(name + " :: " + index);
        }
      }
    };
  }

  private void joinThreads(int size, Thread[] threads) throws InterruptedException {
    for (int ctr = 0; ctr < size; ctr++) {
      threads[ctr].join();
    }
  }


  private Map<Integer, AtomicInteger> getTestMap() {
    final Map<Integer, AtomicInteger> map = new ConcurrentHashMap<>();
    for (int idx : circularAccessor.getArr()) {
      map.put(idx, new AtomicInteger(0));
    }
    return map;
  }

}