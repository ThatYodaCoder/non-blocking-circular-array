import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

public class AtomicIntTest {

  private int a = 0;
  private AtomicInteger b = new AtomicInteger(0);

  @Test
  public void testInts() throws InterruptedException {

    int size = 32;
    int noOfItr = 10_000_000;

    //Create threads and invode CircularAccessor.getgetValue() method inside run method of every thread.
    Thread[] threads = createThreads(size, noOfItr);
    joinThreads(size, threads);

    System.out.println("a="+a);
    System.out.println("b="+b.get());

  }


  public synchronized int incrIntValue() {
    return a++;
  }

  public int incrAtomicIntValue() {
    return b.incrementAndGet();
  }


  private Thread[] createThreads(int size, int noOfItr) {
    Thread[] threads = new Thread[size];
    for (int ctr = 0; ctr < size; ctr++) {
      threads[ctr] = new Thread(getThread(noOfItr));
      threads[ctr].start();
    }
    return threads;
  }

  private Runnable getThread(final int noOfItr) {
    return new Runnable() {
      @Override
      public void run() {
        final String name = Thread.currentThread().getName();
        for (int cnt = 0; cnt < noOfItr; cnt++) {

          //final int index = incrAtomicIntValue();
          final int index = incrIntValue();

        }
      }
    };
  }

  private void joinThreads(int size, Thread[] threads) throws InterruptedException {
    for (int ctr = 0; ctr < size; ctr++) {
      threads[ctr].join();
    }
  }
}
