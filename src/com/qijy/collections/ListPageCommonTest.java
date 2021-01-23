package com.qijy.collections;

import java.util.ArrayList;
import java.util.List;

public class ListPageCommonTest {
    public static void main(String[] args) {
        Request request = new Request();
        request.setStarttime("2021-01-21 12:59:28");
        request.setEndtime("2021-01-21 12:59:35");
        List<Result> list = new ArrayList<>();
        for(int i=0;i<60;i++){
            Result result = new Result(i+"",i+"qijy");
            result.setTime("2021-01-21 12:59:"+i);
            list.add(result);
        }
        ListPageCommon<Request,Result> listPageCommon = new ListPageCommon<>(request,list);
        int toltalPage = listPageCommon.getToltalPage(10);
        List<Result> listPaged = listPageCommon.getListPaged(1,10);
        System.out.println("总页数:"+toltalPage);
        for (Result result : listPaged) {
            System.out.println("id:"+result.getId()+"        name:"+result.getName());
        }
    }

}
