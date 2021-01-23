package com.qijy.bisets;

public class MoveTest {
    public static void main(String[] args) {
        int i = -1 << (Integer.SIZE - 3);
        // -536870912
        System.out.println(i);

        int i222 = -536870912|0;
        System.out.println(i222);

        System.out.println((1 << 30));

    }
}
