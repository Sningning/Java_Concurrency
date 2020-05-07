package wrongways;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * FutureTask + Callable
 *
 * @Author: Song Ningning
 * @Date: 2020-04-30 16:21
 */
public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Callable Style");
        return "success";
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new FutureTask<String>(new CallableDemo()));
        thread.start();
    }
}
