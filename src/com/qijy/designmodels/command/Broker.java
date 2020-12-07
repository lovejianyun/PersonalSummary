package com.qijy.designmodels.command;

import java.util.ArrayList;
import java.util.List;

public class Broker {
    List<Order> orderList = new ArrayList<>();
    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
