package stopthread;

/**
 * 如果在执行过程中，每次循环都会调用 sleep 或 wait 等方法，
 * 那么不需要每次迭代都检查是否已中断
 *
 * @Author: Song Ningning
 * @Date: 2020-04-30 23:36
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            int num = 100;
            try {
                while (num <= 10000) {
                    System.out.println(num + " 是 100 的倍数");
                    num += 100;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
