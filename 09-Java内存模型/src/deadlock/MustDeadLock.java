package deadlock;

/**
 * 必定发生死锁的情况
 *
 * @Author: Song Ningning
 * @Date: 2020-06-18 23:09
 */
public class MustDeadLock implements Runnable {

    int flag = 1;

    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                System.out.println("线程 1 拿到了 o1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("线程 1 拿到了两把锁");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                System.out.println("线程 2 拿到了 o2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("线程 2 拿到了两把锁");
                }
            }
        }
    }
}
