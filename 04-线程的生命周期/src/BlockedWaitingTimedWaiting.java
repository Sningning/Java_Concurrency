/**
 * 展示 Blocked, Waiting, TimedWaiting
 *
 * @Author: Song Ningning
 * @Date: 2020-05-08 0:58
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) {

        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TIMED_WAITING
        System.out.println("thread1 的状态：" + thread1.getState());
        // BLOCKED
        System.out.println("thread2 的状态：" + thread2.getState());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // WAITING
        System.out.println("thread1 的状态：" + thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
