public class LambdaTest {

    @FunctionalInterface
    interface Compute {
        public int add(int x,int y);
        public default int mvl(int x, int y){
            return x -y;
        }
    }

    /**
     * 1 复制小括号， 写固定箭头 ，紧跟大括号
     * 2 只能是
     * @param args
     */
    public static void main(String[] args) {

        Compute compute = (int x,int y) -> {System.out.println("test");
        return  x+y;};
        compute.add(1,2);
        System.out.println(compute.mvl(1,2));
    }
}
