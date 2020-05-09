/**
 * 演示 join 期间被中断的效果
 *
 * @Author: Song Ningning
 * @Date: 2020-05-09 20:00
 */
public class JoinInterrupt {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("sleep()结束");
                } catch (InterruptedException e) {
                    System.out.println("子线程被中断");
                }
            }
        }, "子线程");

        thread.start();
        System.out.println("主线程等待子线程执行完毕");
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "线程被中断");
            thread.interrupt();
        }
        System.out.println("子线程执行完毕");
    }
}
