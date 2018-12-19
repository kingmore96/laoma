package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCollections {
    public static void main(String[] args) {
        List list = new ArrayList<Integer>();
        list = Collections.checkedList(list, Integer.class);
        list.add("hello");
        System.out.println(list);
    }
}
