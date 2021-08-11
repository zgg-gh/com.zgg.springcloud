import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String  test = "...xxx..xx.xx...";
        test = test.replace(".x",".1x");
        test = test.replace("x.","x1.");
        System.out.println(test);
           String[] arr  =      test.split("1");
        Map arrX = new HashMap();
        Map arrT = new HashMap();
        for(int i = 0; i<arr.length;i++){
            if(arr[i].contains("x")){
                arrX.put(i,arr[i].length());
            }else{
                arrT.put(i,arr[i].length());
            }
        }
        for(int m=0;m<arrX.size();m++){
            arrX.values().toArray()[m] =0;
        }
    }
}
