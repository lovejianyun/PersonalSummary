package com.qijy.enums;
/*
 * @ Description   :
 * @ Author        :  qijy
 * @ CreateDate    :  2021/1/23 11:49
 */
public enum Day {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3),
    THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);
    private int name;

    private Day(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }
}
