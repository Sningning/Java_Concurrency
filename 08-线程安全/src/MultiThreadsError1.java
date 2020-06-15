/**
 * 线程安全问题第一种：运行结果出错
 *
 * @Author: Song Ningning
 * @Date: 2020-05-11 19:24
 */
public class MultiThreadsError1 implements Runnable {

    int index = 0;
    static MultiThreadsError1 instance = new MultiThreadsError1();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果是：" + instance.index);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
    }
}
