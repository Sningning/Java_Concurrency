/**
 * 线程安全问题第二种：演示死锁
 *
 * @Author: Song Ningning
 * @Date: 2020-05-11 20:50
 */
public class MultiThreadsError2 implements Runnable {

    boolean flag = true;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        MultiThreadsError2 r1 = new MultiThreadsError2();
        MultiThreadsError2 r2 = new MultiThreadsError2();
        r1.flag = true;
        r2.flag = false;
        new Thread(r1).start();
        new Thread(r2).start();

    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);

        if (flag == true) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("true");
                }
            }
        }

        if (flag == false) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("false");
                }
            }
        }
    }
}
