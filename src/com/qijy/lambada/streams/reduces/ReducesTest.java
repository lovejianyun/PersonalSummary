package com.qijy.lambada.streams.reduces;

import com.qijy.lambada.beans.Trader;
import com.qijy.lambada.beans.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * @ Description   :  reduce测试 归约
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/11 20:36
 */
public class ReducesTest {
    public static void main(String[] args) {
        test0006();
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
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> collect = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(Comparator.comparing(transaction -> transaction.getValue())).collect(Collectors.toList());
        collect.stream().forEach(transaction -> System.out.println(transaction));


    }

}
