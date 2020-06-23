package deadlock;

/**
 * 改变一个哲学家拿筷子的顺序避免死锁
 *
 * @Author: Song Ningning
 * @Date: 2020-06-22 22:29
 */
public class DiningPhilosophersAvoid {

    // 哲学家类，实现 Runnable 接口
    public static class Philosopher implements Runnable {

        // 筷子对象
        private Object leftChopstick;
        private Object rightChopstick;
        // 构造方法传入左右筷子
        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        doAction("Pick up left chopstick");
                        synchronized (rightChopstick) {
                            doAction("Pick up right chopstick --> eating");
                            doAction("Pick down right chopstick");
                        }
                        doAction("Pick down left chopstick --> finished");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            Thread.sleep((long) (Math.random() * 10));
        }
    }

    public static void main(String[] args) {
        // 创建 5 个哲学家
        Philosopher[] philosophers = new Philosopher[5];
        // 创建 5 支筷子
        Object[] chopsticks = new Object[philosophers.length];
        // 初始化每支筷子
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        // 初始化每个哲学家并启动线程
        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];
            if (i == philosophers.length - 1) {
                // 改变其中一位哲学家拿筷子的顺序
                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
            } else {
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            }
            new Thread(philosophers[i], (i + 1) + "号哲学家").start();
        }
    }
}
