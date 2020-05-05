package createthreads;

/**
 * 用继承 Thread 类方式实现线程
 *
 * @Author: Song Ningning
 * @Date: 2020-04-27 23:46
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("用继承 Thread 类方式实现线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
