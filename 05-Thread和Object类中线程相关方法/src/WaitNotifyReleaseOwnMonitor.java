/**
 * 证明 wait 只释放当前的那把锁
 *
 * @Author: Song Ningning
 * @Date: 2020-05-08 17:53
 */
public class WaitNotifyReleaseOwnMonitor {

    private static Object ObjA = new Object();
    private static Object ObjB = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ObjA) {
                    System.out.println("Thread-1 拿到了 ObjA 锁");
                    synchronized (ObjB) {
                        System.out.println("Thread-1 拿到了 ObjB 锁");
                        try {
                            System.out.println("Thread-1 释放了 ObjA 锁");
                            ObjA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (ObjA) {
                    System.out.println("Thread-2 拿到了 ObjA 锁");
                    System.out.println("Thread-2 尝试拿到 ObjB 锁");
                    synchronized (ObjB) {
                        System.out.println("Thread-2 拿到了 ObjB 锁");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
