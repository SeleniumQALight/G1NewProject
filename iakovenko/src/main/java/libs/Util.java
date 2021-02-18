package libs;

public class Util {
    public static void waitABit(Integer second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
