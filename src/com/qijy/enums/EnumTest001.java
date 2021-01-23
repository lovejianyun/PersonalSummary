package com.qijy.enums;

import static com.qijy.enums.EnumTest001.Color.RED;

public class EnumTest001 {
    enum Color {
        RED,GREEN,BLUE;
    }

    public static void printName(Color color){
        switch (color){
            case RED:
                System.out.println("红色");
                break;
            case BLUE:
                System.out.println("蓝色");
                break;
            case GREEN:
                System.out.println("绿色");
                break;
                default:
                    System.out.println("漆剑云");
        }
    }

    public static void main(String[] args) {
        printName(Color.RED);
    }

}
