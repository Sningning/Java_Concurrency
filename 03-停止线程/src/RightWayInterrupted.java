/**
 * Thread.interrupted()方法的目标对象是“当前线程”，而不管本方法来自于哪个对象
 *
 * @Author: Song Ningning
 * @Date: 2020-05-06 0:59
 */
public class RightWayInterrupted {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                }
            }
        });

        // 启动线程
        threadOne.start();
        //设置中断标志
        threadOne.interrupt();
        //获取中断标志---①
        System.out.println("isInterrupted: " + threadOne.isInterrupted());
        //获取中断标志并重置---②
        System.out.println("isInterrupted: " + threadOne.interrupted());
        //获取中断标志并重直---③
        System.out.println("isInterrupted: " + Thread.interrupted());
        //获取中断标志---④
        System.out.println("isInterrupted: " + threadOne.isInterrupted());
        threadOne.join();
        System.out.println("Main thread is over.");
    }
}
