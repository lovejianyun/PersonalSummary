package com.qijy.lambada.predicates;

import com.qijy.lambada.UsageBase;
import com.qijy.lambada.beans.Apple;
import com.qijy.lambada.interfaces.PredicateApple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

public class PredicatesUsage extends UsageBase {

    /*
     * @ Description   :  传递参数是Apple类型
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/6 17:00
     */
    private static List<Apple> filter(List<Apple> list, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    /*
     * @ Description   :  方法策略
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/10 22:41
     */
    private static List<Apple> filter3(List<Apple> list, PredicateApple predicateApple){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if(predicateApple.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }


    /*
     * @ Description   :  predicate 传递参数是Long类型
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/6 16:59
     */
    private static List<Apple> filter1(List<Apple> list, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (predicate.test(apple.getWeigt())) {
                result.add(apple);
            }
        }
        return result;
    }


    private static List<Apple> filter2(List<Apple> list, BiPredicate<String, Integer> biPredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (biPredicate.test(apple.getColor(), apple.getWeigt())) {
                result.add(apple);
            }
        }
        return result;
    }


    public static void main(String[] args) {
//        List<Apple> list = Arrays.asList(new Apple("green", 100), new Apple("red", 120),new Apple("red",130));
        PredicatesUsage predicatesUsage = new PredicatesUsage();
        List<Apple> list = predicatesUsage.getAppleList();

        List<Apple> red = filter(list, apple -> apple.getColor().equals("red"));

        System.out.println("------filter---------");
        for (Apple apple : red) {
            System.out.println(apple);
        }
        System.out.println("--------------------------");
        List<Apple> apples = filter1(list, l -> l >= 110);
        System.out.println("------filter1---------");
        apples.stream().forEach(apple -> System.out.println(apple));
        System.out.println("--------------------");
        List<Apple> filter2 = filter2(list, (s, l) -> s.equals("green") && l == 100);
        System.out.println("------filter2---------");
        filter2.stream().forEach(apple -> System.out.println(apple));

        System.out.println("---------------------");
        System.out.println("--------filter3------------");
        List<Apple> green = filter3(list, apple -> apple.getColor().equals("green"));
        green.stream().forEach(apple -> System.out.println(apple));

    }

}
