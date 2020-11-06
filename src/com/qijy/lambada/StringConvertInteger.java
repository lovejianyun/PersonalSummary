package com.qijy.lambada;

public class StringConvertInteger implements Convert<Long,String>{
    @Override
    public String convert(Long l) {
       return String.valueOf(l);
    }
    public static void main(String[] args) {
        StringConvertInteger stringConvertInteger = new StringConvertInteger();
        System.out.println(stringConvertInteger.convert(132134L));
        stringConvertInteger.getxxxx();
    }
}
