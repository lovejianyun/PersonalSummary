package com.qijy.create;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/6/21 15:51
 */
public class SqlCreate {
    public static void main(String[] args) {
        String backupStart = "select \n" +
                "ldl.id,\n" +
                "ldl.loanserialno,\n" +
                "ldl.overdueprincipalamt,\n" +
                "ldl.overduedays,\n" +
                "ldl.penaltyamt,\n" +
                "ldl.transdate,\n" +
                "ldl.`status`,\n" +
                "ldl.remark,\n" +
                "ldl.inputtime \n" +
                "from loan_daily_log ldl INNER JOIN acct_loan al on ldl.loanserialno = al.serialno where al.finishdate >= ";


        String deleteStart = "delete from loan_daily_log where id in (\n" +
                "select \n" +
                "ldl.id\n" +
                "from loan_daily_log ldl INNER JOIN acct_loan al on ldl.loanserialno = al.serialno where al.finishdate >= ";


        String backupMid = " and al.finishdate <= ";
        LocalDate localDate = LocalDate.parse("2019/07/01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate date = localDate.withDayOfMonth(localDate.lengthOfMonth());
        for (int i=1;i< 60;i++){
            String result = "";
            String resultStr = "";
            localDate = localDate.plusMonths(-1);
            date = localDate.withDayOfMonth(localDate.lengthOfMonth());
            result = backupStart + "'" + localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "'" + backupMid + "'" + date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) +"'" + ";";
            System.out.println("--"+" "+localDate.format(DateTimeFormatter.ofPattern("yyyy/MM"))+"月备份" );
            System.out.println(result);
            System.out.println();
            System.out.println();
            System.out.println("-- " + "清理"+localDate.format(DateTimeFormatter.ofPattern("yyyy/MM")));
            resultStr = deleteStart + "'" + localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "'" + backupMid + "'" + date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) +"'"+");";
            System.out.println(resultStr);


            System.out.println();
            System.out.println();
        }
    }
}
