package com.qijy.lambada.streams.reduces;

import com.qijy.lambada.beans.Trader;
import com.qijy.lambada.beans.Transaction;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * @ Description   :  reduce测试 归约
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/11 20:36
 */
public class ReducesTest {
    public static void main(String[] args) {
        test0015();
    }
    private static  void test0001(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Integer reduce = integers.stream().reduce(0, (a, b) -> (a + b));

        Integer reduce1 = integers.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        System.out.println(reduce1);
    }

    private static  void test0002(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Optional<Integer> reduce = integers.stream().reduce((a, b) -> (a * b));
        System.out.println(reduce.get());
    }

/*
 * @ Description   :  max
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/11 20:50
 */
    private static  void test0003(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Optional<Integer> reduce = integers.stream().reduce((x, y) -> x > y ? x : y);
        Optional<Integer> reduce1 = integers.stream().reduce(Integer::max);
        System.out.println(reduce.get());
        System.out.println(reduce1.get());
    }

    /*
     * @ Description   :  max
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/11 20:50
     */
    private static  void test0004(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Optional<Integer> reduce = integers.stream().reduce((x, y) -> x < y ? x : y);
        Optional<Integer> reduce1 = integers.stream().reduce(Integer::min);
        System.out.println(reduce.get());
        System.out.println(reduce1.get());
    }

/*
 * @ Description   :  统计个数
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/11 20:53
 */
    private static  void test0005(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4,5,6,7,8,9,10,11,12);
        Integer reduce = integers.stream().map(a -> 1).reduce(0, (a, b) -> (a + b));
        System.out.println(reduce);
    }

    private static void test0006(){
        List<Transaction> transactions = getTransactions();
        // 找出2011年的所有交易并按交易额排序（从低到高）
        List<Transaction> collect = transactions.stream().filter(transaction -> transaction.getYear() == 2011).
                sorted(Comparator.comparing(transaction -> transaction.getValue())).collect(Collectors.toList());
        collect.stream().forEach(transaction -> System.out.println(transaction));


        // 交易员都在哪些不同的城市工作过

        Set<String> collect1 = transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(Collectors.toSet());
        collect1.stream().forEach(transaction-> System.out.println(transaction));

        //查找所有来自于剑桥的交易员，并按姓名排序

        List<Trader> cambridge = transactions.stream().map(transaction -> transaction.getTrader()).
                filter(trader -> trader.getCity().equals("Cambridge")).distinct().
                sorted(Comparator.comparing(trader -> trader.getName())).collect(Collectors.toList());
        cambridge.stream().forEach(trader -> System.out.println(trader));


        //返回所有交易员的姓名字符串，按字母顺序排序

        String reduce = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce("", (a, b) -> a + b);
        System.out.println(reduce);
        String collect2 = transactions.stream().map(transaction -> transaction.getTrader()).map(trader -> trader.getName()).distinct().sorted().collect(Collectors.joining());
        System.out.println(collect2);


        //有没有交易员是在米兰工作的

        boolean milan = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milan);

        //打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).
                map(transaction -> transaction.getValue()).forEach(System.out::println);

        //所有交易中，最高的交易额是多少

        Optional<Integer> reduce1 = transactions.stream().map(transaction -> transaction.getValue()).reduce(Integer::max);

        System.out.println(reduce1.get());


        // 找到交易额最小的交易

        Optional<Transaction> reduce2 = transactions.stream().reduce((a, b) -> a.getValue() < b.getValue() ? a : b);

        System.out.println(reduce2.get());


        Optional<Transaction> min = transactions.stream().min(Comparator.comparing(transaction -> transaction.getValue()));

        System.out.println(min.get());

        int sum = transactions.stream().mapToInt(Transaction::getValue).sum();

        System.out.println(sum);


        IntStream intStream = transactions.stream().mapToInt(Transaction::getValue);

        // 将intStream 转换成Stream
        Stream<Integer> boxed = intStream.boxed();

        OptionalInt max = intStream.max();




    }

    private static List<Transaction> getTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /*
     * @ Description   :  查找勾股数
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/12 16:03
     */
    private static void test0007(){
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 1000).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 1000)
                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                        );


        pythagoreanTriples.forEach(a->System.out.println(Arrays.toString(a)));


    }

    private static void test0008(){
        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(
                                                b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                        .filter(t -> t[2] % 1 == 0));


        pythagoreanTriples2.forEach(a-> System.out.println(Arrays.toString(a)));
    }

    /*
     * @ Description   :  创建流
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/12 16:17
     */
    private static void test0009(){
        Stream<String> qijy = Stream.of("qijy", "tangt", "zhouyu", "sanba", "shacha");
        qijy.map(String::toUpperCase).forEach(System.out::println);
        IntStream stream = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(stream.sum());



    }

    /*
     * @ Description   :
     *
     * 斐波纳契数列是著名的经典编程练习。下面这个数列就是斐波纳契数列的一部分：0, 1, 1,
2, 3, 5, 8, 13, 21, 34, 55…数列中开始的两个数字是0和1，后续的每个数字都是前两个数字之和。
斐波纳契元组序列与此类似，是数列中数字和其后续数字组成的元组构成的序列：(0, 1),
(1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21) …
你的任务是用 iterate 方法生成斐波纳契元组序列中的前20个元素。
让我们帮你入手吧。第一个问题是， iterate 方法要接受一个 UnaryOperator<t> 作为
参数，而你需要一个像(0,1)这样的元组流。你还是可以（这次又是比较草率地）使用一个数组
的两个元素来代表元组。例如， new int[]{0,1} 就代表了斐波纳契序列(0, 1)中的第一个元
素。这就是 iterate 方法的初始值：
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/12 16:45
     */
    private static void test0010(){
        Stream.iterate(new int []{0,1},t->new int[]{t[1],t[0]+t[1]}).limit(20).map(t->t[0]).forEach(System.out::println);
    }

    private static void test0011(){
        IntStream.generate(new IntSupplier() {
            private int previous=0;
            private int current=1;
            @Override
            public int getAsInt() {
                int oldprevious = this.previous;
                int nextvalue = this.previous + this.current;
                this.previous = current;
                this.current = nextvalue;
                return oldprevious;
            }
        }).limit(10).forEach(System.out::println);
    }

    /*
     * @ Description   :  分组处理
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/12 17:57
     */
    private static void test0012(){
        List<Transaction> transactions = getTransactions();
        Map<Integer, List<Transaction>> transactionsByCurrencies =
                new HashMap<>();
        for (Transaction transaction : transactions) {
            Integer currency = transaction.getValue();
            List<Transaction> transactionsForCurrency =
                    transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies
                        .put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

    }
    /*
     * @ Description   :  流分组处理
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/12 18:04
     */
    private static void test0013(){
        List<Transaction> transactions = getTransactions();
        Map<Integer, List<Transaction>> collect = transactions.stream().collect(Collectors.groupingBy(Transaction::getValue));
        Set<Map.Entry<Integer, List<Transaction>>> entries = collect.entrySet();
        for (Map.Entry<Integer, List<Transaction>> entry : entries) {
            System.out.println("key:"+entry.getKey());
            System.out.println("value:"+entry.getValue());
        }

    }

    private static void test0014(){
        List<Integer> integerList = createIntegerList();
        long start = System.currentTimeMillis();
        integerList.stream().forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }

    private static void test0015(){
        List<Integer> integerList = createIntegerList();
        long start = System.currentTimeMillis();
        for (Integer integer : integerList) {
            System.out.println(integer);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }


    private static List<Integer> createIntegerList() {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<1000000;i++){
            list.add(i);
        }
        return list;
    }


}
