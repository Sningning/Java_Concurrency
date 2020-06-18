package singleton;

/**
 * 静态内部类 [推荐用]
 *
 * 静态内部类不会自动初始化，只有调用静态内部类的方法，静态域，或者构造方法的时候才会加载静态内部类。
 *
 * 该方法也属于懒汉式。
 *
 * @Author: Song Ningning
 * @Date: 2020-06-17 20:57
 */
public class Singleton7 {

    private Singleton7() {}

    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
