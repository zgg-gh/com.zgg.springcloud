import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ShowData{
    volatile int  number = 0;
    public void addT060(){
        this.number = 60;
    }
    public void  numPlus(){
        number ++;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    public void myAtomicAdd(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * volatile 可见性
 * 不能保证原子性
 *
 * 解决  加  sync
 */
public class VolatileDamo {
    public static void main(String[] args) {
        ShowData showData = new ShowData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    showData.numPlus();
                    showData.myAtomicAdd();
                }

            },String.valueOf(i)).start();
        }
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while (Thread.activeCount() >2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t come out"+showData.number);
        System.out.println(Thread.currentThread().getName()+"\t come out"+showData.atomicInteger);


    }
    public static void seokVolatile(String[] args) {
        ShowData showData = new ShowData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            showData.addT060();
            System.out.println(Thread.currentThread().getName()+"\t come out"+showData.number);
        },"A").start();


        while (showData.number == 0){

        }
        System.out.println(Thread.currentThread().getName()+"\t mission over"+showData.number);
    }
}
