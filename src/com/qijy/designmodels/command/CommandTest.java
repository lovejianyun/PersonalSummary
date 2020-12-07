package com.qijy.designmodels.command;

public class CommandTest {
    public static void main(String[] args) {
        Stock stock = new Stock();
        Order buyStock = new BuyStock(stock);
        Order sellStock = new SellStock(stock);
        Broker broker = new Broker();
        broker.takeOrder(buyStock);
        broker.takeOrder(sellStock);
        broker.placeOrders();
    }
}
