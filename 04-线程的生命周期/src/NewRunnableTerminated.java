/**
 * 展示线程的 New、Runnable、Terminated 状态。
 * 即使是正在运行，也是 Runnable 状态，而不是 Running。
 *
 * @Author: Song Ningning
 * @Date: 2020-05-08 0:39
 */
public class NewRunnableTerminated implements Runnable{

    public static void main(String[] args) {

        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println("调用 start() 之前的状态：" + thread.getState());
        thread.start();
        System.out.println("调用 start() 之后的状态：" + thread.getState());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("休眠 5ms 后，处于运行中时的状态：" + thread.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run() 方法正常执行完之后的状态：" + thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
