package com.collection;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/22
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, String> map =
//                new LinkedHashMap<>();
//                new HashMap<>();
                new TreeMap<>(); // key 按照升序有序的排列
        Random random = new Random();
        for (int i = 10; i < 100; i++) {
            String key = "key" + i;
            String value = "value:" + i;

            map.put(key, value);
        }

        Set<String> set = map.keySet();
        for (String key : set) {
            System.out.println("key:" + key);
        }
    }
}
