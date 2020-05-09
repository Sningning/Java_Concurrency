/**
 * 演示join，注意语句输出顺序，会变化。
 *
 * @Author: Song Ningning
 * @Date: 2020-05-09 19:28
 */
public class Join implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        Join r = new Join();
        Thread thread1 = new Thread(r, "Thread-1");
        Thread thread2 = new Thread(r, "Thread-2");

        thread1.start();
        thread2.start();
        System.out.println("开始等待子线程运行完毕");
        thread1.join();
        thread2.join();
        System.out.println("所有子线程执行完毕");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 执行完毕");
    }
}
