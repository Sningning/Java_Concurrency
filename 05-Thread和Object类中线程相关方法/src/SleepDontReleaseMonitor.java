/**
 * 展示线程 sleep 的时候不释放 synchronized 的 monitor，
 * 等 sleep 时间到了以后，正常结束后才释放锁
 *
 * @Author: Song Ningning
 * @Date: 2020-05-09 16:44
 */
public class SleepDontReleaseMonitor implements Runnable {

    public static void main(String[] args) {
        SleepDontReleaseMonitor r = new SleepDontReleaseMonitor();
        new Thread(r).start();
        new Thread(r).start();
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println(Thread.currentThread().getName() + " 获取到了 monitor");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 退出了同步方法");
    }
}
