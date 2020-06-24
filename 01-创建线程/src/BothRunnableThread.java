/**
 * 同时使用 Runnable 和 Thread 两种方式实现线程
 *
 * @Author: Song Ningning
 * @Date: 2020-04-27 23:57
 */
public class BothRunnableThread {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("Thread");
            }
        }.start();
    }
}
