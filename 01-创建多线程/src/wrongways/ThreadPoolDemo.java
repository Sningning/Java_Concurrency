package wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用线程池创建线程
 *
 * @Author: Song Ningning
 * @Date: 2020-04-28 23:35
 */
public class ThreadPoolDemo {

    // public static void main(String[] args) {
    //     ExecutorService executorService = Executors.newCachedThreadPool();
    //     for (int i = 0; i < 100; i++) {
    //         executorService.submit(new Task());
    //     }
    // }

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadPool");
            }
        });
        service.shutdown();
    }
}