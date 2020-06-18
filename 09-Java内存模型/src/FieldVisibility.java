/**
 * 可见性
 * @Author: Song Ningning
 * @Date: 2020-06-16 20:41
 */
public class FieldVisibility {

    int a = 1;
    // volatile int b = 2;
    int b = 2;

    private void change() {
        a = 3;  // ①
        b = a;  // ②
    }

    private void print() {
        System.out.println("b = " + b + "; a = " + a);  // ③
    }

    public static void main(String[] args) {
        FieldVisibility test = new FieldVisibility();
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.change();
            }
        });
        one.start();

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.print();
            }
        });
        two.start();
    }
}
