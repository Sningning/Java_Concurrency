package stopthread.volatiledemo;

/**
 * 演示用volatile的局限：part1 看似可行
 *
 * @Author: Song Ningning
 * @Date: 2020-05-03 1:31
 */
public class WrongWayVolatile implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 100;
        try {
            while (num <= 100000 && !canceled) {
                System.out.println(num + "是 100 的倍数");
                num += 100;
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        WrongWayVolatile r = new WrongWayVolatile();
        Thread thread = new Thread(r);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r.canceled = true;
    }
}
