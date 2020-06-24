package wrongways;

/**
 * 匿名内部类的方式创建线程
 *
 * @Author: Song Ningning
 * @Date: 2020-04-28 23:32
 */
public class AnonymousInnerClassDemo {

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
