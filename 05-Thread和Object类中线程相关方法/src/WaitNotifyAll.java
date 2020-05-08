/**
 * 3 个线程，线程0 和线程1 首先被阻塞，线程2 唤醒它们。
 * notify, notifyAll。 start先执行不代表线程先启动。
 *
 * @Author: Song Ningning
 * @Date: 2020-05-08 17:12
 */
public class WaitNotifyAll implements Runnable {

    private static final Object OBJ = new Object();

    @Override
    public void run() {
        synchronized (OBJ) {
            System.out.println(Thread.currentThread().getName() + " 拿到了锁");
            try {
                System.out.println(Thread.currentThread().getName() + " 等待被唤醒");
                OBJ.wait();
                System.out.println(Thread.currentThread().getName() + " 执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        WaitNotifyAll r = new WaitNotifyAll();

        Thread thread0 = new Thread(r);
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(() -> {
            synchronized (OBJ) {
                OBJ.notifyAll();
                // OBJ.notify();
                System.out.println("Thread2 中执行了 notifyAll()");
            }
        });

        thread0.start();
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
