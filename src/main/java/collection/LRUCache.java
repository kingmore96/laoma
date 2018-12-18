package collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU策略缓存，采用LinkedHashMap实现
 * @param <K>
 * @param <V>
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private int maxEntries;

    public LRUCache(int initialCapacity, float loadFactor, boolean accessOrder, int maxEntries) {
        super(initialCapacity, loadFactor, accessOrder);
        this.maxEntries = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxEntries;
    }

    public static void main(String[] args) {
        LRUCache<String,Integer> lruCache = new LRUCache<>(16,0.75f,true,3);
        lruCache.put("a",1);
        lruCache.put("b",2);
        lruCache.put("c",3);
        lruCache.put("d",4);
        lruCache.get("c");

        for (Map.Entry<String, Integer> stringIntegerEntry : lruCache.entrySet()) {
            System.out.println(stringIntegerEntry.getKey()+stringIntegerEntry.getValue());
        }
    }
}

