package singleton;

/**
 * 懒汉式（双重检查 DCL: Double Check Lock） [推荐面试用]
 *
 * 优点：线程安全；延迟加载；效率较高。
 *
 * 为什么要 double-check
 *     1.线程安全
 *     2.单 check 行不行?
 *     3.性能问题(与 Singleton4 相比)
 *
 * 为什么要用 volatile
 *     1.新建对象实际上有 3 个步骤
 *         ① 给指令申请内存
 *         ② 给成员变量初始化（执行构造方法）
 *         ③ 把申请的内存内容赋值给 instance
 *     2.重排序会带来 NPE
 *     3.防止重排序
 *
 * @Author: Song Ningning
 * @Date: 2020-06-17 20:57
 */
public class Singleton6 {

    private volatile static Singleton6 instance;

    private Singleton6() {}

    public Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
