package com.bswen.lambdas.predict;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created on 2018/5/3.
 */
public class ListFilters {
    //The most old-fashion way to filter a String List
    public static List<String> filterString(List<String> stringList, int minLength) {
        List<String> result = new ArrayList<>();
        for(int i=0;i<stringList.size();i++) {
            String temp = stringList.get(i);
            if(temp.length()>minLength) {
                result.add(temp);
            }
        }
        return result;
    }

    //The generic and functional way to do filter
    public static <T> List<T> filterList(List<T> originList, Predicate<T> thePredicate) {
        List<T> result = new ArrayList<T>();
        for(T t:originList) {
            if(thePredicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    //The generic and functional way and removeIf to do filter,without modifying the originList
    public static <T> List<T> filterListWithRemoveIf(List<T> originList,
                                                     Predicate<T> thePredicate) {
        List<T> result = new ArrayList<T>(originList);//shadow copy
        result.removeIf(thePredicate);//would not affect the orgin list
        return result;
    }

    //The generic and functional way and removeIf to do filter, do modify the originList
    public static <T> List<T> filterListWithRemoveIf2(List<T> originList,
                                                      Predicate<T> thePredicate) {
        originList.removeIf(thePredicate);//would not affect the orgin list
        return originList;
    }

    //The stream and functional way to do the filter.
    public static <T> List<T> filterListWithStream(List<T> originList,Predicate<T>
            thePredicate) {
        return originList.stream().filter(thePredicate).collect(Collectors.toList());
    }

    //use apache commons-collections4 CollectionUtils ,would modify originList
    public static <T> boolean filterListWithCollectionUtils(List<T> originList,Predicate<T>
            thePredicate) {
        return CollectionUtils.filter(originList, t->thePredicate.test(t));//because apache use its own Predicate class,
        //so we just use the lambda .
    }

    //use apache commons-collections4 CollectionUtils ,without modifying originList
    public static <T> Collection<T> filterListWithCollectionUtilsNoModify(List<T> originList, Predicate<T>
            thePredicate) {
        return CollectionUtils.select(originList,t->thePredicate.test(t));//because apache use its own Predicate class,
        //so we just use the lambda .
    }

    public static void main(String[] args) {
        List<String> s = Arrays.asList("a","bc","asdf","232323");

        System.out.println("after filter");
        List<String> result = filterList(s,temp->temp.length()>3);
//        List<String> result =  filterListWithRemoveIf2(s,temp->temp.length()>3);
//        List<String> result = filterCollection2(s,temp->temp.length()>3);

//        List<String> result = new ArrayList<>(s);
//        filterListWithCollectionUtils(result,temp->temp.length()>3);
//

//        Collection<String> result = filterListWithCollectionUtilsNoModify(s,temp->temp.length()>3);
        consumeCollection(result,temp-> System.out.println(temp));

        System.out.println("\nbefore filter:");
        consumeCollection(s,temp-> System.out.println(temp));
    }

    private static <T> void consumeCollection(Collection<T> collection, Consumer<T> consumer) {
        for(T t:collection) {
            consumer.accept(t);
        }
    }
}
