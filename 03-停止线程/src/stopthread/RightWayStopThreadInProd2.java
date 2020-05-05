package stopthread;

/**
 * 最佳实践 2：在 catch 子语句中调用 Thread.currentThread().interrupt() 来恢复设置中断状态，
 *          以便于在后续的执行中，依然能够检查到刚才发生了中断，回到刚才
 *          RightWayStopThreadInProd1 补上中断，让它跳出
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 23:08
 */
public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted，程序运行结束");
                break;
            }
            System.out.println("go");
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
