/**
 * 如果 while 里面放 try/catch，会导致中断失效
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 0:38
 */
public class CantInterrupt {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            int num = 100;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                System.out.println(num + " 是 100 的倍数");
                num += 100;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
