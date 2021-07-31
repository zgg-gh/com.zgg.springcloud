import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface SaleTread{

}

class Tiket{

    private int tiketNumber = 30;
    public void sale(){

        Lock lock = new ReentrantLock();
        lock.lock();
        try {

            if(tiketNumber > 0){
                System.out.println(Thread.currentThread().getName()+"开始卖第"+tiketNumber--+"张票，还剩"+tiketNumber+"张票");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}

public class SaleTiket {
    public static void main(String[] args) {


        Tiket tiket = new Tiket();

        new Thread(() -> {for (int i =0 ;i<=30 ;i++) tiket.sale();},"A").start();
        new Thread(() -> {for (int i =0 ;i<=30 ;i++) tiket.sale();},"B").start();
        new Thread(() -> {for (int i =0 ;i<=30 ;i++) tiket.sale();},"C").start();

  /*      new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0 ;i<=30 ;i++){

                    tiket.sale();
                }

            }
        }, "AAA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0 ;i<=30 ;i++){

                    tiket.sale();
                }

            }
        }, "BBB").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0 ;i<=30 ;i++){

                    tiket.sale();
                }

            }
        }, "CCC").start();*/


    }
}
