import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentTest {

  static final int CONCURRENT = 32;
  static final int LOOP = 1_000_000;
  static int counter;
  static AtomicInteger aCounter;

  public static void main(String[] args) throws Exception {
    char mode = (args.length == 0) ? 0 : (args[0].equals("-l")) ? 'l' : (args[0].equals("-i")) ? 'i' : 1;
    if (mode == 1) {
      throw new IllegalArgumentException("-i or -l");
    }
    int expected = CONCURRENT * LOOP;
    counter = 0;
    aCounter = new AtomicInteger(0);
    Runnable ts = null;
    if (mode == 0) {
      System.out.println("unuse lock.");
      ts = new Runnable() {
        public void run() {
          for (int j = 0; j < LOOP; j++) {
            counter++;
          }
        }
      };
    } else if (mode == 'l') {
      System.out.println("lock.");
      final Object o = new Object();
      ts = new Runnable() {
        public void run() {
          for (int j = 0; j < LOOP; j++) {
            synchronized (o) {
              counter++;
            }
          }
        }
      };
    } else if (mode == 'i') {
      System.out.println("AtomicInteger.");
      ts = new Runnable() {
        public void run() {
          for (int j = 0; j < LOOP; j++) {
            aCounter.incrementAndGet();
          }
        }
      };
    }

    Thread[] threads = new Thread[CONCURRENT];
    for (int i = 0; i < threads.length; ++i) {
      threads[i] = new Thread(ts);
    }
    long start = System.currentTimeMillis();
    for (int i = 0; i < threads.length; ++i) {
      threads[i].start();
    }
    for (int i = 0; i < threads.length; ++i) {
      threads[i].join();
    }
    long end = System.currentTimeMillis();
    if (counter != 0) {
      System.out.println(MessageFormat.format("counter : {0}", counter));
    } else {
      System.out.println(MessageFormat.format("seed    : {0}", aCounter.get()));
    }
    System.out.println(MessageFormat.format("expected: {0}", expected));
    System.out.println(MessageFormat.format("time: {0}(ms)", end - start));
  }
}