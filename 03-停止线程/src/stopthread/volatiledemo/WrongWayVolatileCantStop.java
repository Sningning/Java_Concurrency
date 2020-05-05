package stopthread.volatiledemo;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * 演示用volatile的局限part2 陷入阻塞时，volatile是无法停止线程的
 * 此例中，生产者的生产速度快，消费者消费速度慢，所以阻塞队列满了以后，
 * 生产者会阻塞，等待消费者进一步消费。
 *
 * @Author: Song Ningning
 * @Date: 2020-05-05 22:53
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<>(10);

        // 生产
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        // 消费
        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }

        System.out.println("消费者不需要更多数据了");

        //一旦消费不需要更多数据了，应该尝试让生产者也停下来
        producer.canceled = true;
        System.out.println(producer.canceled);

    }

    // 生产者类
    static class Producer implements Runnable {

        public volatile boolean canceled = false;
        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 100;
            try {
                while (num <= 10000 && !canceled) {
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
