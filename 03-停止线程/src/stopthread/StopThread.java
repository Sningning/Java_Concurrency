package stopthread;

/**
 * 错误的停止方法：用 stop() 来停止线程，会导致线程运行一半突然停止，没办法完成一个基本单位的操作（一个连队），
 * 会造成脏数据（有的连队多领取少领取装备）。
 *
 * @Author: Song Ningning
 * @Date: 2020-05-02 0:20
 */
public class StopThread implements Runnable {

    @Override
    public void run() {
        // 循环 5 次，每次打印出 0 - 9
        for (int i = 1; i < 6; i++) {
            System.out.println("第 " + i + " 轮开始打印");
            for (int j = 0; j < 10; j++) {
                System.out.print(j + " ");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            System.out.println("第 " + i + " 轮打印完成");
        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
