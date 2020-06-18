package singleton;

/**
 * 懒汉式（线程不安全）[不可用]
 *
 * @Author: Song Ningning
 * @Date: 2020-06-17 20:40
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {}

    public Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
