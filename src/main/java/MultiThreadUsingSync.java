public class MultiThreadUsingSync {

  private final Object mutex = new Object();
  private int num=0;

  public static void main(String[] args) {

    MultiThreadUsingSync t = new MultiThreadUsingSync();

    Thread even = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int ctr=0;ctr<10;ctr++) {
          t.printEven();
        }
      }
    });

    Thread odd = new Thread(new Runnable() {
      @Override
      public void run() {

        for(int ctr=0;ctr<10;ctr++) {
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


  public void printOdd(){

    synchronized (mutex){

      //wait till num become odd
      while(isEven(num)){
        try {
          mutex.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      //System.out.println("odd: "+num);
      System.out.print(num + " , ");

      num++;
      mutex.notifyAll();
    }
  }

  private boolean isEven(int n){
    return n%2==0;
  }


  public void printEven(){

    synchronized (mutex){
      //wait till num become even
      while(!isEven(num)){
        try {
          mutex.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.print(num + " , ");
      //System.out.println("even: "+num);
      num++;
      mutex.notifyAll();
    }
  }
}
