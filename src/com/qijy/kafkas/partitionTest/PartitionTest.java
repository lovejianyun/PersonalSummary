package com.qijy.kafkas.partitionTest;

import org.apache.kafka.common.utils.Utils;

/*
 * @ Description   :  给定一个字符串,生成一个取模算法
 * @ Author        :  qijy
 * @ CreateDate    :  2021/3/5 13:52
 */
public class PartitionTest {

    public static void main(String[] args) {
        String qijy = "ewrqewsdff11346375f122";

        int mw2Index = Utils.toPositive(Utils.murmur2(qijy.getBytes()))%100;
        System.out.println(mw2Index);
    }
}
