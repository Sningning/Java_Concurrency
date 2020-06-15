import java.util.HashMap;
import java.util.Map;

/**
 * 发布逸出
 *
 * @Author: Song Ningning
 * @Date: 2020-05-12 8:28
 */
public class MultiThreadsError3 {

    private Map<String, String> states;

    public MultiThreadsError3() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    // 此处 getStates 直接将 private 类型的 states 直接发布了出去
    // 使得外界可以对其进行修改
    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) {
        MultiThreadsError3 m = new MultiThreadsError3();
        Map<String, String> states = m.getStates();
        System.out.println(states.get("1"));  // 周一
        states.remove("1");
        System.out.println(states.get("1"));  // null
    }
}
