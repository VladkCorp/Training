package com.Udm1.Cache;

import java.util.LinkedHashMap;
import java.util.Map;


public class DefaultLruCache extends LinkedHashMap<Integer, Integer>
        implements LruCache {

    private int capacity;

    public DefaultLruCache() {
        super(16,0.75f,true);
    }

    public DefaultLruCache(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > this.capacity;
    }

    @Override
    public int get(int key) {
        if (!super.containsKey(key)) {
            return -1;
        } else {
            return super.get(key);
        }
    }

    @Override
    public void put(int key, int value) {
        super.put(key,value);
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        DefaultLruCache lruCache = new DefaultLruCache(2);
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lruCache.get(1)); // return 1
        lruCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lruCache.get(2)); // returns -1 (not found)
        lruCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lruCache.get(1)); // return -1 (not found)
        System.out.println(lruCache.get(3)); // return 3
        System.out.println(lruCache.get(4)); // return 4
    }

}