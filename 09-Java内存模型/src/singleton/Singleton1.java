package singleton;

/**
 * 饿汉式（静态常量）[可用]
 *
 * @Author: Song Ningning
 * @Date: 2020-06-17 20:28
 */
public class Singleton1 {

    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {}

    public Singleton1 getInstance() {
        return INSTANCE;
    }
}
