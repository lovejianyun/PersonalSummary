package com.qijy.kafkas.manythreadskafka;
/*
 * @ Description   :  多生产这测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/12/9 20:34
 */
public class ManyProductTest {
    public static void main(String[] args) {
        for (int i=0;i<2;i++){
            new Thread(() -> {
                ManyProduct manyProduct = new ManyProduct();
                for(int j=0;j<100000;j++){
                    manyProduct.send("songli","love"+"qijy"+j);
                }
            }).start();
        }

    }
}
