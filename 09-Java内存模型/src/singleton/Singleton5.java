package singleton;

/**
 * 懒汉式（线程不安全，同步代码块）[不可用]
 *
 * @Author: Song Ningning
 * @Date: 2020-06-17 20:50
 */
public class Singleton5 {

    private static Singleton5 instance;

    private Singleton5() {}

    public Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
