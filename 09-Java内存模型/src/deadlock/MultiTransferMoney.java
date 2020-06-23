package deadlock;

import java.util.Random;

/**
 * 多人同时转账发生死锁
 *
 * @Author: Song Ningning
 * @Date: 2020-06-20 15:13
 */
public class MultiTransferMoney {

    // 账户数量
    private static final int NUM_ACCOUNTS = 500;
    // 账户余额
    private static final int NUM_MONEY = 1000;
    // 每个线程转账次数
    private static final int NUM_ITERATIONS = 1000000;
    // 线程数量
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random random = new Random();
        Account[] accounts = new Account[NUM_ACCOUNTS];
        // 初始化每个账户
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(NUM_MONEY);
        }
        // 创建转账线程
        class TransferThread extends Thread {
            @Override
            public void run() {
                // 每个线程进行 NUM_ITERATIONS 次转账操作
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = random.nextInt(NUM_ACCOUNTS);
                    int toAcct = random.nextInt(NUM_ACCOUNTS);
                    int amount = random.nextInt(NUM_MONEY);
                    transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
                System.out.println("运行结束。");
            }
        }
        // 启动 NUM_THREADS 个线程同时进行转账操作（模拟 NUM_THREADS 人同时转账）
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }

    // 表示账户的内部类
    static class Account {
        int balance;
        public Account(int balance) {
            this.balance = balance;
        }
    }

    private static void transferMoney(Account from, Account to, int amount) {
        synchronized (from) {
            synchronized (to) {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败。");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账" + amount + "元。");
            }
        }
    }
}
