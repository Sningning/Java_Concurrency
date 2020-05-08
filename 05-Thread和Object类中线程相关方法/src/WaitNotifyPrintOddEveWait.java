import com.sun.deploy.ref.DeployJRE;

/**
 * 两个线程交替打印0~100的奇偶数，用wait和notify
 *
 * @Author: Song Ningning
 * @Date: 2020-05-08 21:29
 */
public class WaitNotifyPrintOddEveWait {

    private static int count = 0;
    private static Object obj = new Object();

    public static void main(String[] args) {

        new Thread(new PrintNumber(), "偶数").start();
        new Thread(new PrintNumber(), "奇数").start();
    }

    static class PrintNumber implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (obj) {
                    // 唤醒其他线程
                    obj.notify();
                    System.out.println(Thread.currentThread().getName() + " : " + count++);
                    if (count <= 100) {
                        try {
                            // 如果任务还未结束，就休眠并释放锁
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
