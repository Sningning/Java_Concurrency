package createthreads.wrongways;

/**
 * lambda表达式创建线程
 *
 * @Author: Song Ningning
 * @Date: 2020-04-28 23:30
 */
public class LambdaDemo {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
