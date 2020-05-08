/**
 * 演示 wait 和 notify 的基本用法
 * 1. 注意代码执行顺序
 * 2. 证明 wait 会释放锁
 *
 * @Author: Song Ningning
 * @Date: 2020-05-08 16:52
 */
public class Wait {

    public static final Object OBJ = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (OBJ) {
                System.out.println(Thread.currentThread().getName() + " 开始执行");
                try {
                    OBJ.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 又获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (OBJ) {
                OBJ.notify();
                System.out.println(Thread.currentThread().getName() + " 调用了 notify()");
            }
        }
    }


    public static void main(String[] args) {

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
