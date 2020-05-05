package createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTask
 *
 * @Author: Song Ningning
 * @Date: 2020-04-29 0:05
 */
public class TimmerTaskDemo {

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("TimerTask");
            }
        }, 1000, 1000);
    }
}
