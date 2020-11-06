package com.qijy.lambada.details;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyIntegerFaceTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
        MyInterface myInterface = (i)->{
            return String.valueOf(i);
        };
        list.forEach(integer -> System.out.println(myInterface.printnum(integer)));

        List<String> printupcase = printupcase();
        printupcase.stream().forEach(i->System.out.println(i));
        printname();

        MyIntegerFaceTest myIntegerFaceTest = new MyIntegerFaceTest();
        System.out.println(myIntegerFaceTest.compute(3, (i) -> {
            return (i * i + 2 * i + 1);
        }));

        System.out.println(myIntegerFaceTest.compute1(3, 4, Integer::sum));

        System.out.println(myIntegerFaceTest.compute1(3, 3, (a, b) -> {
            return (a + b);
        }));

        System.out.println(myIntegerFaceTest.compute2(3, 4, (a, b) -> {
            return a * b;
        }, a -> a + a));

    }

    private static List<String> printupcase(){
        List<String> liststr = Arrays.asList("abc","cnm");
        List<String> collect = liststr.stream().map(str -> str.toUpperCase()).collect(Collectors.toList());
        return collect;
    }

    public static void printname(){
        PersonInterface personInterface = Person::new;
        System.out.println(personInterface.getPerson("zhangsan").getName());
    }

    public int compute(int a, Function<Integer,Integer> function){
        return function.apply(a);
    }


    public int compute1(int a, int b, BiFunction<Integer,Integer,Integer> biFunction){
        return biFunction.apply(a,b);
    }

    public int compute2(int a,int b,BiFunction<Integer,Integer,Integer> function,Function<Integer,Integer> function1){
        BiFunction<Integer, Integer, Integer> biFunction = function.andThen(function1);
        return biFunction.apply(a,b);
    }

}
