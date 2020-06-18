package singleton;

/**
 * 懒汉式（线程安全，同步方法）[不推荐用：效率低]
 *
 * @Author: Song Ningning
 * @Date: 2020-06-17 20:44
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {}

    public synchronized Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
