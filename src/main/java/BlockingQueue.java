import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue {

  private List<String> queue = new LinkedList<String>();

  private final int SIZE = 5;

  private Lock lock = new ReentrantLock();

  private Condition queueFullCond = lock.newCondition();
  private Condition queueEmptyCond = lock.newCondition();

  public static void main(String[] args) {

    BlockingQueue blkQueue = new BlockingQueue();

    Thread producer = new Thread(new Runnable() {
      @Override
      public void run() {

        for (int i = 0; i < 20; i++) {
          blkQueue.put("D" + i);
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    Thread consumer = new Thread(new Runnable() {
      @Override
      public void run() {

        for (int i = 0; i < 20; i++) {
          blkQueue.delete();
          try {
            Thread.sleep(140);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    try {

      consumer.start();
      Thread.sleep(10);
      producer.start();

      producer.join();
      consumer.join();

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Exiting");
  }

  public void put(String data) {

    lock.lock();

    try {
      while (isFull()) {
        try {
          System.out.println("Queue Full, waiting for consumer to consume");
          queueFullCond.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      queue.add(data);

      System.out.println(data + " added to queue");

      queueEmptyCond.signalAll();
    } finally {
      lock.unlock();
    }
  }

  private String delete() {
    String data = null;
    lock.lock();
    try {

      while (isEmpty()) {
        try {
          System.out.println("Queue empty, waiting for producer to produce");
          queueEmptyCond.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      data = queue.remove(0);

      System.out.println(data + " removed from queue");

      queueFullCond.signalAll();
    } finally {
      lock.unlock();
    }

    return data;
  }

  private boolean isFull() {
    return queue.size() == SIZE;
  }

  private boolean isEmpty() {
    return queue.isEmpty();
  }


}
