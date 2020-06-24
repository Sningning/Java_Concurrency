/**
 * 不能两次调用start方法，否则会报错
 *
 * @Author: Song Ningning
 * @Date: 2020-04-29 17:42
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}


