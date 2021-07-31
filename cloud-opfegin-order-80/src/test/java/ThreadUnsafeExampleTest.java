import java.util.concurrent.locks.Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

class  ThreadUnsafeExample {

//    AtomicInteger

    private AtomicInteger cnt = new AtomicInteger(0);

    public void add() {
        cnt.incrementAndGet();
    }

//    public  void add() {
//        Lock lock = new ReentrantLock();
//        lock.lock();
//        try {
//            cnt++;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }

    public int get() {
        return cnt.intValue();
    }
}

public class ThreadUnsafeExampleTest {

    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
                /**
         13          * 最大线程该如何定义（线程池的最大的大小如何设置！）
         14          * 1、CPU  密集型，几核，就是几，可以保持CPU的效率最高！
         15          */

                 //获取电脑CPU核数
                 System.out.println(Runtime.getRuntime().availableProcessors());    //8核
        System.out.println(Runtime.getRuntime().availableProcessors());    //8核
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                //System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }
}


