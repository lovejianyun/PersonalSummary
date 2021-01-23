package com.qijy.enums;
/*
 * @ Description   :  带有抽象方法的枚举
 * @ Author        :  qijy
 * @ CreateDate    :  2021/1/23 13:56
 */
public enum  AbstactDay {
    MONDAY{
        @Override
        public void print(String name) {
            System.out.println("星期一:"+name);
        }
    }, TUESDAY{
        @Override
        public void print(String name) {
            System.out.println("星期二");
        }
    }, WEDNESDAY{
        @Override
        public void print(String name) {
            System.out.println("星期三");
        }
    },
    THURSDAY{
        @Override
        public void print(String name) {
            System.out.println("星期四");
        }
    }, FRIDAY{
        @Override
        public void print(String name) {
            System.out.println("星期五");
        }
    }, SATURDAY{
        @Override
        public void print(String name) {
            System.out.println("星期六");
        }
    }, SUNDAY{
        @Override
        public void print(String name) {
            System.out.println("星期天");
        }
    };

    private String name;

    public abstract void print(String name);

    public String getName() {
        return name;
    }
}
