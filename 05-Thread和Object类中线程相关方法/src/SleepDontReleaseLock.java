import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Song Ningning
 * @Date: 2020-05-09 16:53
 */
public class SleepDontReleaseLock implements Runnable {

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        SleepDontReleaseLock r = new SleepDontReleaseLock();
        new Thread(r).start();
        new Thread(r).start();
    }

    @Override
    public synchronized void run() {
        LOCK.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取了 Lock 锁");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " 已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
