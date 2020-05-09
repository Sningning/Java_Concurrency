import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 每个 1 秒钟输出当前时间，被中断。
 * Thread.sleep()
 * TimeUnit.SECONDS.sleep()
 *
 * @Author: Song Ningning
 * @Date: 2020-05-09 17:07
 */
public class SleepInterrupted implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("已被中断");
            }
        }
    }
}
