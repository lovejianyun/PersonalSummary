package com.qijy.lambada;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public class test {
    public static void main(String[] args) {
//        Convert<String,Integer> convert = (string) -> Integer.valueOf(string);
//        Integer integer = convert.convert("3698");
//        System.out.println(integer);
//        System.out.println("===========================");
//        getMethodtest();
//        System.out.println("===========================");
//
//        getStaticMethodTest();
        getListTest();

    }

    public static void getMethodtest(){
        Convert<String,Integer> convert = Integer::valueOf;
        System.out.println(convert.convert("520"));
    }
    public static void getStaticMethodTest(){
        PersonFactory<Person> personFactory = Person::new;
        System.out.println(personFactory.create("qijy", "18"));
        Predicate <String> predicate = (str) -> str.length()>0;
        System.out.println(predicate.test("sdfs"));
    }
    public static void getPredit(){
        Predicate nonNull = Objects::nonNull;
    }


    public static void getListTest(){
        List<Person> list = new ArrayList<>();
        Person p1 = new Person("qijy","18");
        Person p3 = new Person("binj","20");
        Person p4 = new Person("xo","15");
        Person p5 = new Person("xoxo","13");
        Person p2 = new Person("tangt","19");
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
//        list.stream().filter(s->(s.startsWith("a"))).forEach(System.out::println);

//        list.parallelStream().filter(s->s.startsWith("a")).map(s->s.toUpperCase()).forEach(System.out::println);

//        list.stream().sorted((a,b)->(a.compareTo(b))).filter(s->s.startsWith("b")).map(s->s.toUpperCase()).forEach(System.out::println);

//        list.stream().sorted((a,b)->(b.getAge().compareTo(a.getAge()))).forEach(s->System.out.println("姓名:"+s.getName()+";年龄:"+s.getAge()));
        list.stream().sorted((a,b)->(b.getAge().compareTo(a.getAge()))).forEach(System.out::println);

    }

}
