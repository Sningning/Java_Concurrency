/**
 * run 方法中带有 sleep 的中断线程的写法
 *
 * @Author: Song Ningning
 * @Date: 2020-04-30 23:01
 */
public class RightWaysStopThreadWithSleep {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            int num = 100;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    System.out.println(num + " 是 100 的倍数");
                    num += 100;
                }
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
