/**
 * 最佳实践 1：catch 住 InterruptedExcetion 之后的优先选择：
 *            在方法签名中抛出异常，在 run() 就会强制 try/catch
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 1:17
 */
public class RightWayStopThreadInProd1 implements Runnable {

    @Override
    public void run() {
        // 业务逻辑
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                // 响应中断的操作
                // 保存日志、停止程序等
                System.out.println("正确处理了中断请求");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new RightWayStopThreadInProd1());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
