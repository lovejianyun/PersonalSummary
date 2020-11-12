package com.qijy.lambada.comsumers;

import com.qijy.lambada.UsageBase;
import com.qijy.lambada.beans.Apple;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/*
 * @ Description   :  consumer测试用例
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/6 17:11
 */
public class ComsumersUsage extends UsageBase {
    private void print(List<Apple> list, Consumer<Apple> consumer){
        list.stream().forEach(apple -> consumer.accept(apple));
    }

    private void print1(List<Apple> list,String s, BiConsumer<Apple,String> biConsumer){
        list.stream().forEach((apple)->{
            biConsumer.accept(apple,s);
        });
    }


    public static void main(String[] args) {
        ComsumersUsage comsumersUsage = new ComsumersUsage();
        List<Apple> appleList = comsumersUsage.getAppleList();
        System.out.println("------print------------");
        comsumersUsage.print(appleList,apple -> System.out.println(apple));
        System.out.println("--------------------");
        System.out.println("------print1------------");
        comsumersUsage.print1(appleList,"qijy",(apple,s)->{
            apple.setColor(s+apple.getColor());
            System.out.println(apple);
        });



    }
}
