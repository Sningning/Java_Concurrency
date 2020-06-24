package deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用 tryLock 来避免死锁
 *
 * @Author: Song Ningning
 * @Date: 2020-06-23 22:36
 */
public class TryLockDeadlock implements Runnable {

    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadlock r1 = new TryLockDeadlock();
        TryLockDeadlock r2 = new TryLockDeadlock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    // 线程 1 尝试获取锁 1
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        System.out.println("线程 1 获取到了锁 1");
                        Thread.sleep(new Random().nextInt(1000));
                        // 线程 1 尝试获取锁 2
                        if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                            System.out.println("线程 1 获取到了锁 2");
                            System.out.println("线程 1 获取到了两把锁");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        } else {
                            System.out.println("线程 1 获取锁 2 失败，已重试");
                            lock1.unlock();
                        }
                    } else {
                        System.out.println("线程 1 获取锁 1 失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag == 0) {
                try {
                    // 线程 2 尝试获取锁 2
                    if (lock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        System.out.println("线程 2 获取到了锁 2");
                        Thread.sleep(new Random().nextInt(1000));
                        // 线程 1 尝试获取锁 2
                        if (lock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                            System.out.println("线程 2 获取到了锁 1");
                            System.out.println("线程 2 获取到了两把锁");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        } else {
                            System.out.println("线程 2 获取锁 1 失败，已重试");
                            lock2.unlock();
                        }
                    } else {
                        System.out.println("线程 2 获取锁 2 失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
