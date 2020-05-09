/**
 * 先 join 再 mainThread.getState()
 *
 * @Author: Song Ningning
 * @Date: 2020-05-09 20:30
 */
public class JoinThreadState {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("主线程状态: " + mainThread.getState());
                    System.out.println("Thread-0 执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        System.out.println("主线程等待子线程执行完毕");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程执行完毕");
    }
}
