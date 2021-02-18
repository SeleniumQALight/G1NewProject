package libs;

public class Util {
    public static void waitBit(Integer second) {
        try {
        Thread.sleep(second * 1000);
    }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
