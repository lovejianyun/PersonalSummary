package com.qijy.lambada.beans;

public class Dish {
    private String type;
    private int price;
    private int calories;

    public Dish(String type, int price, int calories) {
        this.type = type;
        this.price = price;
        this.calories = calories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                '}';
    }
}
