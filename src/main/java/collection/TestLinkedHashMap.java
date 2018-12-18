package collection;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestLinkedHashMap {
    public static void main(String[] args) {
        Map<String,Integer> map = new LinkedHashMap<>();

        map = new LinkedHashMap<>(16,0.75f,true);

        map.put("c",1);
        map.put("d",2);
        map.put("a",3);
        map.put("d",10);

        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            System.out.println(stringIntegerEntry.getKey()+stringIntegerEntry.getValue());
        }
    }
}
