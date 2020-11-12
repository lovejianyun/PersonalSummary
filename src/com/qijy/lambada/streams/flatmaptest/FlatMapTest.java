package com.qijy.lambada.streams.flatmaptest;

import java.lang.String;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class FlatMapTest {
    public static void main(String[] args) {
        test004();
    }

    private static void test001() {
        List<String> list = Arrays.asList("hello", "world");
        List<Stream<String>> collect = list.stream().map(a -> a.split("")).map(a -> Arrays.stream(a)).distinct().collect(Collectors.toList());
        //扁平化的流,即将流String[] 单个的每一个都变成流
        list.stream().map(a->a.split("")).flatMap(a->Arrays.stream(a)).distinct().collect(Collectors.toList());
        list.stream().map(a -> a.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
    }
/*
 * @ Description   :  给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4,
5]，应该返回[1, 4, 9, 16, 25]。
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/11 17:30
 */
    private static void test002(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = integers.stream().map(a -> a * a).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
    /*
     * @ Description   :  给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代
表数对。
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/11 17:29
     */
    private static void test003(){
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Integer> integers1 = Arrays.asList(3, 4);

//        List<Stream<int[]>> collect = integers.stream().map(i -> integers1.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        List<int[]> collect = integers.stream().flatMap(i -> integers1.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        collect.stream().forEach(a->System.out.println(Arrays.toString(a)));


    }
    /*
     * @ Description   :  如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/11 17:49
     */
    private static void test004(){
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Integer> integers1 = Arrays.asList(3, 4);
        List<int[]> collect = integers.stream().flatMap(i -> integers1.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList());
        collect.stream().forEach(a-> System.out.println(Arrays.toString(a)));

    }


}
