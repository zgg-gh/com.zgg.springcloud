import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * 1 故障现象
 * java.util.ConcurrentModificationException
 * 2 导致原因
 * 3 解决方法
 */
public class NoSafe {
    public static void main(String[] args) {
        List<String> testList = new Vector<>();

 /*       testList.add("a");
        testList.add("a");
        testList.add("a");
//        System.out.println(testList.forEach(()->{}));

        testList.forEach(System.out::println);*/

        for (int i = 0; i <=30; i++) {
            new Thread(()->{
                testList.add(UUID.randomUUID().toString().substring(0,8));
//                testList.forEach(System.out::println);
                System.out.println(testList);
            },"a").start();

        }

    }
}
