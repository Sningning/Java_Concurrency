/**
 * run 方法内没有 sleep 或 wait 方法时，停止线程
 *
 * @Author: Song Ningning
 * @Date: 2020-04-30 19:00
 */
public class RightWaysStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 10000;
        while (!Thread.currentThread().isInterrupted() && num <= (Integer.MAX_VALUE >> 1)) {
            System.out.println(num + "是 10000 的倍数");
            num += 10000;
        }
        System.out.println("运行结束");
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new RightWaysStopThreadWithoutSleep());
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
