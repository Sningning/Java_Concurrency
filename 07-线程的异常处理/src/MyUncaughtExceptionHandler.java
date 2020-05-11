import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自己定义的 MyUncaughtExceptionHanlder
 *
 * @Author: Song Ningning
 * @Date: 2020-05-11 11:27
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, t.getName() + "线程异常");
        System.out.println(name + "捕获了" + t.getName() + "的异常");
    }
}
