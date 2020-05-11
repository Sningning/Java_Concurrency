/**
 * 使用刚才自己写的 UncaughtExceptionHandler
 *
 * @Author: Song Ningning
 * @Date: 2020-05-11 11:32
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器"));
        UseOwnUncaughtExceptionHandler r = new UseOwnUncaughtExceptionHandler();
        new Thread(r, "Thread-1").start();
        Thread.sleep(500);
        new Thread(r, "Thread-2").start();
        Thread.sleep(500);
        new Thread(r, "Thread-3").start();
        Thread.sleep(500);
        new Thread(r, "Thread-4").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
