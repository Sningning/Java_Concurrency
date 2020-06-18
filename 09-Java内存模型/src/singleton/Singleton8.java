package singleton;

/**
 * 枚举 [推荐用，生产中多用]
 *
 * @Author: Song Ningning
 * @Date: 2020-06-17 20:57
 */
public enum Singleton8 {

    INSTANCE;

    public void doSomething() {
        System.out.println("doSomething");
    }
}
