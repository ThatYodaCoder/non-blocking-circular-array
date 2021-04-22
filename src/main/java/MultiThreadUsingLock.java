import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadUsingLock {

  private final Lock lock = new ReentrantLock();

  private final Condition evenCond = lock.newCondition();

  private int num = 0;

  public static void main(String[] args) {

    MultiThreadUsingLock t = new MultiThreadUsingLock();

    Thread even = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int ctr = 0; ctr < 10; ctr++) {
          t.printEven();
        }
      }
    });

    Thread odd = new Thread(new Runnable() {
      @Override
      public void run() {

        for (int ctr = 0; ctr < 10; ctr++) {
          t.printOdd();
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


  public void printOdd() {

    try {
      lock.lock();

      //wait till num become odd
      while (isEven(num)) {
        try {
          evenCond.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      //System.out.println("odd: "+num);
      System.out.print(num + " , ");

      num++;
      evenCond.signalAll();
    } finally {
      lock.unlock();
    }


  }

  private boolean isEven(int n) {
    return n % 2 == 0;
  }


  public void printEven() {

    try {
      lock.lock();

      //wait till num become even
      while (!isEven(num)) {
        try {
          evenCond.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.print(num + " , ");
      //System.out.println("even: "+num);
      num++;
      evenCond.signalAll();
    } finally {
      lock.unlock();
    }
  }
}
