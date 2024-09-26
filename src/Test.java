import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer, String> m = new HashMap<>();

        m.put(1, "da");
        System.out.println(m.get(1));

        System.out.println("==============");
        for (Map.Entry<Integer, String> entry : m.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
