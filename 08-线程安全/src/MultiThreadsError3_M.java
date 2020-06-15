import java.util.HashMap;
import java.util.Map;

/**
 * 返回“副本”，解决逸出
 *
 * @Author: Song Ningning
 * @Date: 2020-05-12 10:36
 */
public class MultiThreadsError3_M {
    private Map<String, String> states;

    public MultiThreadsError3_M() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    public Map<String, String> getStatesImproved() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadsError3_M m = new MultiThreadsError3_M();
        System.out.println(m.getStatesImproved().get("1"));  // 周一
        m.getStatesImproved().remove("1");
        System.out.println(m.getStatesImproved().get("1"));  // 周一
    }
}
