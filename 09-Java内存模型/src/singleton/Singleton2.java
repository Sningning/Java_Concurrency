package singleton;

/**
 * 饿汉式（静态代码块）[可用]
 *
 * @Author: Song Ningning
 * @Date: 2020-06-17 20:38
 */
public class Singleton2 {

    private static final Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2() {}

    public Singleton2 getInstance() {
        return INSTANCE;
    }
}
