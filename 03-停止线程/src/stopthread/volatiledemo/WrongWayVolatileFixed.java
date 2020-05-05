package stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用中断来修复 WrongWayVolatileCantStop 中无尽等待问题
 *
 * @Author: Song Ningning
 * @Date: 2020-05-05 23:27
 */
public class WrongWayVolatileFixed {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }

        System.out.println("消费者不需要更多数据了");

        //一旦消费不需要更多数据了，应该让生产者也停下来
        producerThread.interrupt();
    }

    // 生产者类
    static class Producer implements Runnable {

        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 100;
            try {
                while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                    storage.put(num);
                    System.out.println(num + "是 100 的倍数，已被加到仓库中");
                    num += 100;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者结束运行");
            }
        }
    }

    // 消费者类
    static class Consumer {

        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }
}
