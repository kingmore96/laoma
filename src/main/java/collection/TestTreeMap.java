package collection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestTreeMap {
    public static void main(String[] args) {
        Map<String,String> m = new TreeMap<>();
        m = new TreeMap<>(Collections.<String>reverseOrder(String.CASE_INSENSITIVE_ORDER));
        m.put("a","1");
        m.put("b","2");
        m.put("T","3");

        m = new TreeMap<>(new Comparator<String>() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            public int compare(String o1, String o2) {
                try {
                    int i = sdf.parse(o1).compareTo(sdf.parse(o2));
                    return i;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });

        m.put("2018-11-02","122");
        m.put("2018-12-02","133");
        m.put("2018-11-10","145");

        NavigableMap<String,String> m1 = new TreeMap<>();

        m1.put("a","1");
        m1.put("c","2");
        m1.put("f","3");

        NavigableMap<String, String> stringStringNavigableMap = m1.descendingMap();
        System.out.println(stringStringNavigableMap.subMap("f",true,"a",true));

        for (Map.Entry<String, String> entrySet : m1.entrySet()) {
            System.out.println(entrySet.getKey()+entrySet.getValue());
        }

        Queue<Integer> queue = new PriorityQueue<>();
        queue.addAll(Arrays.asList(new Integer[]{6,4,3,5,2,1}));

        System.out.println(queue);

        System.out.println(Arrays.toString(queue.toArray()));
    }
}
