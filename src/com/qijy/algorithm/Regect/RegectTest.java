package com.qijy.algorithm.Regect;
/*
 * @ Description   :  (m,n)矩形从左上角到右下角有多少中走法  (只能向右和向下走)
 * @ Author        :  qijy
 * @ CreateDate    :  2021/2/1 15:26
 */
public class RegectTest {
    public static void main(String[] args) {
        System.out.println(getTotal(5, 3));
    }

    public static int getTotal(int m,int n){
        if(m==1){
            return n+1;
        }
        if(n==1){
            return m+1;
        }
//        return getTotal(m,n-1);
//        return getTotal(m-1,n);
        return getTotal(m-1,n)+getTotal(m,n-1);

    }
}
