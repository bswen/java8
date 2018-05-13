package com.bswen.lambdas.collections;

import java.util.*;

/**
 * Created on 2018/5/13.
 */
public class IterateOverMap {
    private static Map<Integer,String> buildMap(List<String> names) {
        Map<Integer,String> result = new HashMap<>();
        for(int i=0;i<names.size();i++) {
            result.put(i,names.get(i));
        }
        return result;
    }

    private static void traverseMap1(Map<Integer,String> map) {
        for(Iterator<Integer> iter = map.keySet().iterator();iter.hasNext();) {
            Integer key = iter.next();
            String nextValue = map.get(key);
            System.out.println("map of ("+key+"-->"+nextValue+")");
        }
    }

    private static void traverseMap2(Map<Integer,String> map) {
        for(String value:map.values()) {
            System.out.println(value);
        }
    }

    private static void traverseMap3(Map<Integer,String> map) {
        Set<Map.Entry<Integer,String>> entries = map.entrySet();
        for(Map.Entry<Integer,String> entry:entries) {
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }
    }

    private static void traverseMap4(Map<Integer,String> map) {
        //using java 8 lambda
        map.forEach((k,v)-> System.out.println(k+"-->"+v));
    }

    private static void traverseMap5(Map<Integer,String> map) {
        //using java 8 stream and entrySet
        map.entrySet().stream().forEach(entrySet->
                System.out.println(entrySet.getKey()+"-->"+entrySet.getValue()));
    }

    public static void main(String[] args) {
        Map<Integer,String> map = buildMap(Arrays.asList("Jack","Mike","Tom","Jerry"));
        System.out.println("traverse map using map.keySet iterator");
        traverseMap1(map);
        System.out.println("\ntraverse map using map.values iterator");
        traverseMap2(map);
        System.out.println("\ntraverse map using map.entrySet iterator");
        traverseMap3(map);
        System.out.println("\ntraverse map using java 8 lambda");
        traverseMap4(map);
        System.out.println("\ntraverse map using java stream of EntrySet");
        traverseMap5(map);
    }
}
