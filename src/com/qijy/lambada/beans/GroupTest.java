package com.qijy.lambada.beans;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/*
 * @ Description   :  分组测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/13 10:25
 */
public class GroupTest {
    public static void main(String[] args) {
        test006();
//        test002();
//        test003();
    }

    private static void test001() {
        Map<String, List<Dish>> collect = getDishes().stream().collect(Collectors.groupingBy(Dish::getType));
        Map<String, Long> collect1 = getDishes().stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        print(collect);
        for (Map.Entry<String, Long> entry : collect1.entrySet()) {
            System.out.println("key:"+entry.getKey()+"  value:"+entry.getValue());
        }
    }
    //分组后取每一个分组中的最大值
    private static void test004(){
        Map<String, Optional<Dish>> collect = getDishes().stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparing(Dish::getPrice))));
        for (Map.Entry<String, Optional<Dish>> entry : collect.entrySet()) {
            System.out.println("key:"+entry.getKey());
            Optional<Dish> value = entry.getValue();
            System.out.println("value:"+value.get());
        }
    }

    private static void test005(){
        Map<String, Dish> collect = getDishes().stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
                                Optional::get)));

        Set<Map.Entry<String, Dish>> entries = collect.entrySet();
        for (Map.Entry<String, Dish> entry : entries) {
            System.out.println("key:"+entry.getKey());
            System.out.println("value:"+entry.getValue());
        }
    }
    /*
     * @ Description   :  获取分组后的累加和
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/13 14:59
     */
    private static void test006(){
        Map<String, IntSummaryStatistics> collect = getDishes().stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.summarizingInt(Dish::getCalories)));

        for (Map.Entry<String, IntSummaryStatistics> entry : collect.entrySet()) {
            IntSummaryStatistics value = entry.getValue();
            System.out.println("key:"+entry.getKey()+"   value:"+value.getSum());
        }
    }
    private static void test002() {
        Map<Claories, List<Dish>> collect = getDishes().stream().collect(Collectors.groupingBy(dish -> {
            return getClaories(dish);
        }));
        print(collect);
    }



    /*
     * @ Description   :  二级分组
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/13 11:38
     */
    private static void test003(){
        Map<String, Map<Claories, List<Dish>>> collect = getDishes().stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            return getClaories(dish);
        })));
        Set<Map.Entry<String, Map<Claories, List<Dish>>>> entries = collect.entrySet();
        for (Map.Entry<String, Map<Claories, List<Dish>>> entry : entries) {
            String key = entry.getKey();
            System.out.println("外层key:"+key);
            Map<Claories, List<Dish>> value = entry.getValue();
            for (Map.Entry<Claories, List<Dish>> listEntry : value.entrySet()) {
                Claories key1 = listEntry.getKey();
                System.out.println("内存key:"+key1);
                listEntry.getValue().stream().forEach(System.out::println);
            }
        }
    }
    // 打印
    private static <T> void print(Map<T, List<Dish>> collect) {
        for (Map.Entry<T, List<Dish>> entry : collect.entrySet()) {
            T key = entry.getKey();
            List<Dish> value = entry.getValue();
            value.stream().forEach(System.out::println);
            System.out.println("------------------------");
        }
    }

    private static List<Dish> getDishes(){
       return Arrays.asList(new Dish("fruit",100,100),
                new Dish("fish",20,150),
                new Dish("mean",1,20),
               new Dish("fruit",120,120));
    }

    private static Claories getClaories(Dish dish) {
        if (dish.getCalories() > 130) {
            return Claories.FAT;
        } else if (dish.getCalories() < 90) {
            return Claories.DIET;
        } else {
            return Claories.NORMAL;
        }
    }


}
