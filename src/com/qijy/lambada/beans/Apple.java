package com.qijy.lambada.beans;
/*
 * @ Description   :  苹果lei
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/6 16:41
 */
public class Apple {
    // 颜色
    private String color;
    // 重量
    private int weigt;

    public Apple(String color, int weigt) {
        this.color = color;
        this.weigt = weigt;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeigt() {
        return weigt;
    }

    public void setWeigt(int weigt) {
        this.weigt = weigt;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weigt=" + weigt +
                '}';
    }
}
