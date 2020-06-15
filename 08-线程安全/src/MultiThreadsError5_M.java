/**
 * 工厂模式
 *
 * @Author: Song Ningning
 * @Date: 2020-05-12 10:45
 */
public class MultiThreadsError5_M {

    int count;
    private EventListener listener;

    // 将构造方法私有化
    private MultiThreadsError5_M(MySource source) {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                // 打印出当前 count 值
                System.out.println("\n得到的数字是" + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i + " ");
        }
        // 将 count 赋值为 100
        count = 100;
    }

    public static MultiThreadsError5_M getInstance(MySource source) {
        // 初始化
        MultiThreadsError5_M safeListener = new MultiThreadsError5_M(source);
        // 初始化完成后，再注册监听器
        source.registerListener(safeListener.listener);
        return safeListener;
    }

    public static void main(String[] args) {

        MySource mySource = new MySource();

        // 利用子线程触发监听器
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 10 ms 之后触发监听器
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {});
            }
        }).start();
        MultiThreadsError5_M.getInstance(mySource);
    }

    static class MySource {
        private EventListener listener;
        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }
        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("未初始化完毕");
            }
        }
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {}
}
