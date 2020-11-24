package com.qijy.algorithm.methods;

import java.util.ArrayList;
import java.util.List;

public class Solution15 {
    public static void main(String[] args) {
        List<Object> arrayY = getArrayY();
        for (Object o : arrayY) {
            System.out.println(o.toString());
        }
    }

    /*
     * @ Description   :
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/23 16:49
     */
    private static List<Object> getArrayY(){
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(null);
        list.add(null);
        list.add(5);
        list.add(null);
        list.add(null);
        list.add(6);
        list.add(7);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(8);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(9);
        list.add(null);
        List<Integer> indexNull = new ArrayList<>();
        // 记录为null的下标
        for(int i=0;i<list.size();i++){
            if(list.get(i) == null){
                indexNull.add(i);
            }
        }
        // 正序记录连续下标识null的个数
        int count = 1;
        int reversecount = 1;
        for(int j=0;j<indexNull.size();j++){
            int integer = indexNull.get(j);
            // 最后一个
            if(integer==list.size()-1){
                if(list.get(integer)==null){
                    for (int k = indexNull.size()-1;k>=0;k--){
                        Integer integer1 = indexNull.get(k);
                        if (list.get(integer1-1)!=null){
                            int reverse = integer1-1;
                            Object o = list.get(reverse);
                            for (int x=0;x<reversecount;x++){
                                list.set(reverse+x+1,o);
                            }
                            break;
                        }else{
                            reversecount++;
                        }

                    }
                }

            }
            if(integer < list.size()-1){
                if(list.get(integer+1)!=null){
                    // 获取值
                    int index = integer+1;
                    Object o = list.get(integer + 1);
                    for(int k=0;k<count;k++){
                        list.set(index-k-1,o);
                    }
                    // 重新将count置为1
                    count=1;
                }else {
                    // 个数
                    count++;
                }
            }
        }
        return list;
    }

}
