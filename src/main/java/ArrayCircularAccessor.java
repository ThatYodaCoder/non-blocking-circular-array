import java.util.concurrent.atomic.AtomicInteger;

public class ArrayCircularAccessor {

  private int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};

  private AtomicInteger idx = new AtomicInteger(-1);

  private int arrIdx = 0;

  /**
   * Non blocking implementation of accessing array in circular way.
   * Equivalent blocking implementation of this method is given below
   * in this class. Please check getValue1() method.
   *
   * @return index
   */
  public int getValue() {

    int val = idx.incrementAndGet();

    while (val >= arr.length) {
      if (idx.compareAndSet(val, 0)) {
        val = 0;
        break;
      }
      val = idx.get();

      if (val < arr.length) {
        val = idx.incrementAndGet();
      }
    }
    return arr[val];
  }

  public synchronized int getValue1() {
    if (arrIdx >= arr.length) {
      arrIdx = 0;
    }
    return arrIdx++;
  }

  public int[] getArr() {
    return arr;
  }

}
