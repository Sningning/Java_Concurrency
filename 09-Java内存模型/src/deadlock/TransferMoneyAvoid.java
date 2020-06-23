package deadlock;

/**
 * 转账时的通过调整获取锁的顺序避免死锁现象
 *
 * @Author: Song Ningning
 * @Date: 2020-06-21 22:01
 */
public class TransferMoneyAvoid implements Runnable {

    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);
    static final Object lock = new Object();

    static class Account {
        int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TransferMoneyAvoid r1 = new TransferMoneyAvoid();
        TransferMoneyAvoid r2 = new TransferMoneyAvoid();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a 的余额：" + a.balance);
        System.out.println("b 的余额：" + b.balance);
    }

    @Override
    public void run() {
        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    private void transferMoney(Account from, Account to, int amount) {

        class helper {
            public void transfer() {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败。");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账" + amount + "元。");
            }
        }
        // 获取哈希值
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        // 根据哈希值大小确定获取锁的顺序
        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    new helper().transfer();
                }
            }
        } else { // fromHash == toHash
            synchronized (lock) {
                synchronized (from) {
                    synchronized (to) {
                        new helper().transfer();
                    }
                }
            }
        }
    }
}
