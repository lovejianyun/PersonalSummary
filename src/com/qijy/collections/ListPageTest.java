package com.qijy.collections;

import java.util.ArrayList;
import java.util.List;

/*
 * @ Description   :  list分页测试
 * @ Author        :  qijy
 * @ CreateDate    :  2019/12/19 15:08
 */
public class ListPageTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            list.add(""+i);
        }
        int page = 4;
        int rows = 10;
        List<String> list1 = list.subList((page-1)*rows, page*rows);
        for (String s : list1) {
            System.out.println(s);
        }

    }
}
