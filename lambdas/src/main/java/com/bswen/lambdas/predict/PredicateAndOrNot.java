package com.bswen.lambdas.predict;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created on 2018/5/4.
 */
public class PredicateAndOrNot {
    private static List<String> originList
            = Arrays.asList("a","bc","def","fgha");

    private static void demoAnd() {
        //filter strings that contain 'a' and length >3
        Predicate<String> p1 = s->s.contains("a");
        Predicate<String> p2 = s->s.length()>3;
        List<String> filtered =
                originList.stream().
                        filter(p1.and(p2)).
                        collect(Collectors.toList());
        filtered.stream().forEach(s-> System.out.println(s));
    }

    private static void demoOr() {
        //filter strings that contain 'a' or length == 3
        Predicate<String> p1 = s->s.contains("a");
        Predicate<String> p2 = s->s.length()==3;
        List<String> filtered =
                originList.stream().
                        filter(p1.or(p2)).
                        collect(Collectors.toList());
        filtered.stream().forEach(s-> System.out.println(s));
    }

    private static void demoNegate() {
        //filter strings that not contain 'a'
        Predicate<String> p1 = s->s.contains("a");
        List<String> filtered =
                originList.stream().
                        filter(p1.negate()).
                        collect(Collectors.toList());
        filtered.stream().forEach(s-> System.out.println(s));
    }



    public static void main(String[] args) {
        System.out.println("demo and ");
        demoAnd();//should return fgha
        System.out.println("\ndemo or");
        demoOr();//should return a def
        System.out.println("\ndemo negate");
        demoNegate();//shoudl return bc,def
    }
}
