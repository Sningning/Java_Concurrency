/**
 * 1. 不加try catch抛出4个异常，都带线程名字
 * 2. 加了try catch,期望捕获到第一个线程的异常，线程234不应该运行，希望看到打印出Caught Exception
 * 3. 执行时发现，根本没有Caught Exception，线程234依然运行并且抛出异常
 *
 * 说明线程的异常不能用传统方法捕获
 *
 * @Author: Song Ningning
 * @Date: 2020-05-11 10:29
 */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        try {
            new Thread(new CantCatchDirectly(), "Thread-1").start();
            Thread.sleep(500);
            new Thread(new CantCatchDirectly(), "Thread-2").start();
            Thread.sleep(500);
            new Thread(new CantCatchDirectly(), "Thread-3").start();
            Thread.sleep(500);
            new Thread(new CantCatchDirectly(), "Thread-4").start();
        } catch (RuntimeException e) {
            System.out.println("捕获了异常");
        }
    }

    @Override
    public void run() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("Caught Exception.");
        }
    }
}
