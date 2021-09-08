package com.qijy.lambada.streams.commons;

import com.qijy.lambada.beans.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author qijy
 * @version 1.0
 * @description: 集合流的常用的用法
 *
 * Stream 是 Java 8 的新特性之一，它能够将数组、集合转换成流，借助Stream API 对流中的元素进行操作，比如筛选、排序、聚合等。这种对流中数据的操作，类似于使用SQL执行的数据库查询。
 *
 * Stream 有下面几个特性：
 *
 * stream不存储数据，而是按照特定的规则对数据进行计算，一般会输出结果。
 * stream不会改变数据源，通常情况下会产生一个新的集合或一个值。
 * stream具有延迟执行特性，只有调用终端操作时，中间操作才会执行。
 * 2、Stream操作分类
 * Stream可以由数组或集合创建，对流的操作分为两种：
 *
 * 中间操作：每次返回一个新的流，可以有多个。
 * 终端操作：每个流只能进行一次终端操作，终端操作结束后流无法再次使用。终端操作会产生一个新的集合或值。
 *
 *
 * @date 2021/9/8 14:32
 */
public class StreamsTest {


    public static void main(String[] args) {
        StreamsTest streamsTest = new StreamsTest();
    }

    /**
     * @description: Stream的创建方式
     * @author qijy
     * @date 2021/9/8 14:35
     * @version 1.0
     */
    public void create(){
        List<String> list = Arrays.asList("hello","world","stream");
        //创建顺序流
        Stream<String> stream = list.stream();
        //创建并行流
        Stream<String> parallelStream = list.parallelStream();
        // 数组工具类的stream方法
        String[] array = {"h", "e", "l", "l", "o"};
        Stream<String> arrayStream = Arrays.stream(array);
        // 通过静态方法来创建
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(3);
        stream2.forEach(System.out::println);
        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);
    }
    /**
     * @description:
     * 遍历/匹配（foreach/find/match）
     * Stream也是支持类似集合的遍历和匹配元素的，只是Stream中的元素是以Optional类型存在的。Stream的遍历、匹配非常简单
     * Optional类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。后面会出关于'Optional类的博客。
     * @author qijy
     * @date 2021/9/8 14:44
     * @version 1.0
     */
    public void use(){
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(a -> System.out.println(a));
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);


        List<String> list1 = Arrays.asList("adnm", "admmt", "pot", "xbangd", "helloStream");

        Optional<String> max = list1.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
    }
    /**
     * @description: 映射，可以将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap：
     * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     * @author qijy
     * @date 2021/9/8 14:50
     * @version 1.0
     */
    public void useMapAndFlatMap(){
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };

        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        // map的使用
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素+3：" + intListNew);

        // flatmap 处理数据
        List<String> list1 = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list1.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());
        System.out.println("处理前的集合：" + list1);
        System.out.println("处理后的集合：" + listNew);
    }
    /**
     * @description: 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
     * @author qijy
     * @date 2021/9/8 14:54
     * @version 1.0
     */
    public void reduceUse(){
        List<Integer> list2 = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list2.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list2.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list2.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list2.stream().reduce((x, y) -> x * y);

        // 求最大值方式1
        Optional<Integer> max = list2.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list2.stream().reduce(1, Integer::max);

        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);
    }
    /**
     * @description: collect，收集，可以说是内容最繁多、功能最丰富的部分了。从字面上去理解，就是把一个流收集起来，最终可以是收集成一个值也可以收集成一个新的集合。
     *
     * collect主要依赖java.util.stream.Collectors类内置的静态方法。
     *
     * 4.6.1 归集(toList/toSet/toMap)
     * 因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据重新归集到新的集合里。toList、toSet和toMap比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法。
     *
     * 演示toList、toSet和toMap
     * @author qijy
     * @date 2021/9/8 14:55
     * @version 1.0
     */
    public void collectUse(){
        List<Integer> list3 = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew3 = list3.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list3.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "tony", 12, "202@qq.com"));
        userList.add(new User(2L, "hai", 15, "372@qq.com"));
        userList.add(new User(3L, "summer", 20, "865@163.com"));

        Map<?, User> map = userList.stream().filter(p -> p.getAge() > 14)
                .collect(Collectors.toMap(User::getName, p -> p));
        System.out.println("toList:" + listNew3);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);
    }
    /**
     * @description: Collectors提供了一系列用于数据统计的静态方法：
     *
     * 计数：count
     * 平均值：averagingInt、averagingLong、averagingDouble
     * 最值：maxBy、minBy
     * 求和：summingInt、summingLong、summingDouble
     * 统计以上所有：summarizingInt、summarizingLong、summarizingDouble
     * 统计用户数、平均年龄、年龄总数、最大年龄。
     * @author qijy
     * @date 2021/9/8 14:57
     * @version 1.0
     */
    public void collectionsUse(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "tony", 12, "202@qq.com"));
        userList.add(new User(2L, "hai", 15, "372@qq.com"));
        userList.add(new User(3L, "summer", 20, "865@163.com"));

        // 求用户数
        Long count = userList.stream().collect(Collectors.counting());
        // 求平均年龄
        Double average = userList.stream().collect(Collectors.averagingDouble(User::getAge));
        // 求最大年龄
        Optional<Integer> max1 = userList.stream().map(User::getAge).collect(Collectors.maxBy(Integer::compare));
        // 求年龄总数
        Integer sum1 = userList.stream().collect(Collectors.summingInt(User::getAge));
        // 一次性统计所有信息
        IntSummaryStatistics collect = userList.stream().collect(Collectors.summarizingInt(User::getAge));

        System.out.println("用户总数：" + count);
        System.out.println("用户平均年龄：" + average);
        System.out.println("用户年龄总和：" + sum1);
        System.out.println("用户年龄所有统计：" + collect);
    }
    /**
     * @description: 分组(partitioningBy / groupingBy)
     * 分区：将stream按条件分为两个Map，比如用户按年龄是否高于14分为两部分。
     * 分组：将集合分为多个Map，比如用户按邮箱类型分组。有单级分组和多级分组。
     * @author qijy
     * @date 2021/9/8 15:06
     * @version 1.0
     */
    public void groupUse(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "tony", 12, "202@qq.com"));
        userList.add(new User(2L, "hai", 15, "372@qq.com"));
        userList.add(new User(3L, "summer", 20, "865@163.com"));

        // 用户按年龄是否高于14分组
        Map<Boolean, List<User>> part = userList.stream().collect(Collectors.partitioningBy(User -> User.getAge() > 8000));
        // 用户按邮箱类型分组分组
        Map<Boolean, List<User>> group = userList.stream().collect(Collectors.groupingBy(User -> User.getEmail().contains("qq.com")));
        System.out.println("用户按年龄是否高于14分组:" + part);
        System.out.println("用户按邮箱类型分组分组：" + group);
    }
    /**
     * @description: 可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
     * @author qijy
     * @date 2021/9/8 15:06
     * @version 1.0
     */
    public void joiningUse(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "tony", 12, "202@qq.com"));
        userList.add(new User(2L, "hai", 15, "372@qq.com"));
        userList.add(new User(3L, "summer", 20, "865@163.com"));

        String names = userList.stream().map(user -> user.getName()).collect(Collectors.joining(","));
        System.out.println("所有用户的姓名：" + names);
    }
    /**
     * @description: Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
     * @author qijy
     * @date 2021/9/8 15:07
     * @version 1.0
     */
    public void reducingUse(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "tony", 12, "202@qq.com"));
        userList.add(new User(2L, "hai", 15, "372@qq.com"));
        userList.add(new User(3L, "summer", 20, "865@163.com"));

        // 每个用户年龄数加0减去2
        Integer sum4 = userList.stream().collect(Collectors.reducing(0, User::getAge, (i, j) -> (i + j - 2)));
        System.out.println("用户剩余年龄总和：" + sum4);
    }
    /**
     * @description: sorted，中间操作。有两种排序：
     *
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：Comparator排序器自定义排序
     * @author qijy
     * @date 2021/9/8 15:08
     * @version 1.0
     */
    public void sortedUse(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "tony", 12, "202@qq.com"));
        userList.add(new User(2L, "hai", 15, "372@qq.com"));
        userList.add(new User(3L, "summer", 20, "865@163.com"));

        // 按用户年龄降序排序
        List<String> newList2 = userList.stream().sorted(Comparator.comparing(User::getAge).reversed())
                .map(User::getName).collect(Collectors.toList());
        System.out.println("按工资降序排序：" + newList2);
    }
    /**
     * @description: 流也可以进行合并、去重、限制、跳过等操作。
     * @author qijy
     * @date 2021/9/8 15:11
     * @version 1.0
     */
    public void combineUse(){
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };

        Stream<String> streamExample1 = Stream.of(arr1);
        Stream<String> streamExample2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(streamExample1, streamExample2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect1 = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect1);
        System.out.println("skip：" + collect2);
    }
}
