import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadUsingLockPingPong {

  private final Lock lock = new ReentrantLock();

  private final Condition pingCond = lock.newCondition();

  private boolean ping = true;

  public static void main(String[] args) {

    MultiThreadUsingLockPingPong t = new MultiThreadUsingLockPingPong();

    Thread even = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int ctr = 0; ctr < 10; ctr++) {
          t.printPing();
        }
      }
    });

    Thread odd = new Thread(new Runnable() {
      @Override
      public void run() {

        for (int ctr = 0; ctr < 10; ctr++) {
          t.printPong();
        }
      }
    });

    even.start();
    odd.start();

    try {
      even.join();
      odd.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Exiting...");
  }


  public void printPong() {

    try {
      lock.lock();

      //wait till num become odd
      while (ping) {
        try {
          pingCond.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      //System.out.println("odd: "+num);
      System.out.print("pong , ");
      ping = true;

      pingCond.signalAll();
    } finally {
      lock.unlock();
    }


  }

  public void printPing() {

    try {
      lock.lock();

      //wait till num become even
      while (!ping) {
        try {
          pingCond.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.print("ping , ");
      ping = false;
      //System.out.println("even: "+num);
      pingCond.signalAll();
    } finally {
      lock.unlock();
    }
  }
}
