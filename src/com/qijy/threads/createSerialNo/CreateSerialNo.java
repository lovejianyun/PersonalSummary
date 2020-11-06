package com.qijy.threads.createSerialNo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @ Description   :  根据时间生成流水编号
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/3 10:13
 */
public class CreateSerialNo implements CircleSerialGenerator{
    private static final CreateSerialNo INSTANCE = new CreateSerialNo();
    private static final long SERIALNO_LIMIT = 9999L;
    private volatile long count=0;
    private CreateSerialNo() {
    }

    public static CreateSerialNo getInstance(){
        return  INSTANCE;
    }

    @Override
    public synchronized long nextCount() {
        if (count>=SERIALNO_LIMIT){
            count = 0;
        }else {
            count ++;
        }
        return count;
    }
    /*
     * @ Description   :  生成序列号
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/3 10:15
     */
    public String nextSerialNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = sdf.format(new Date());
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        long count = nextCount();
        return format+decimalFormat.format(count);
    }

}
