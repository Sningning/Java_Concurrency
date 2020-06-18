package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示重排序现象
 *
 * @Author: Song Ningning
 * @Date: 2020-06-15 21:28
 */
public class OutOfOrderExecution {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(1);
            Thread T1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            });

            Thread T2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });

            T1.start();
            T2.start();
            latch.countDown();
            T1.join();
            T2.join();
            if (x == 0 && y == 0) {
                System.out.println("x = " + x + ", y = " + y);
                break;
            }
        }
    }
}
