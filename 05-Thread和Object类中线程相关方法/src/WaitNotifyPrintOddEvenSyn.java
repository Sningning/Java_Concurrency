/**
 * 两个线程交替打印 0~100 的奇偶数，用 synchronized 关键字实现
 *
 * @Author: Song Ningning
 * @Date: 2020-05-08 21:01
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count;
    private static final Object OBJ = new Object();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (OBJ) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + " : " + count++);
                        }
                    }
                }
            }
        }, "线程 1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (OBJ) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName() + " : " + count++);
                        }
                    }
                }
            }
        }, "线程 2").start();
    }

}
