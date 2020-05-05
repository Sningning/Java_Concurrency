package createthreads;

/**
 * 用实现 Runnable 接口方式创建线程
 *
 * @Author: Song Ningning
 * @Date: 2020-04-27 23:42
 */
public class RunnableStyle implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用实现 Runnable 接口方式创建线程");
    }
}
