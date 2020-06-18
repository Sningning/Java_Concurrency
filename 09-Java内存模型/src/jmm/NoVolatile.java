package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不适用于 volatile 的场景 1
 *
 * @Author: Song Ningning
 * @Date: 2020-06-16 21:26
 */
public class NoVolatile implements Runnable {

    volatile int a;
    AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new NoVolatile();
        Thread T1 = new Thread(r);
        Thread T2 = new Thread(r);
        T1.start();
        T2.start();
        T1.join();
        T2.join();
        System.out.println(((NoVolatile) r).a);
        System.out.println(((NoVolatile) r).realA);
    }
}
