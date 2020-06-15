/**
 * 隐式逸出——注册监听事件
 *
 * @Author: Song Ningning
 * @Date: 2020-05-12 9:22
 */
public class MultiThreadsError5 {

    int count;

    public MultiThreadsError5(MySource source) {
        // 注册监听器
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                // 打印出当前 count 值
                System.out.println("\n得到的数字是" + count);
            }
        });
        for (int i = 0; i < 10000; i++) {
            System.out.print(i + " ");
        }
        // 将 count 赋值为 100
        count = 100;
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
        // 初始化
        MultiThreadsError5 multiThreadsError5 = new MultiThreadsError5(mySource);
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

