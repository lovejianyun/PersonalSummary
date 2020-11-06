package com.qijy.duridDB;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DuridTest {
    public static void main(String[] args) {
        DaoUtils daoUtils = new DaoUtils();
//        String sql = "select * from gread_tb";
//        testqueryListMap(daoUtils,sql,null);
//        testQueryExecute(daoUtils,sql,null);

//        String sql = "insert into gread_tb (`uid`,`grade`) values (?,?)";

//        String sql = "update gread_tb set grade = ? where id = ?";

//        String sql = "delete from gread_tb where id = ?";
//        List<Object> params = new ArrayList<>();
//        params.add(6);
//        params.add(71);
//        testupdateSqlExecute(daoUtils,sql,params);

//        String sql = "CALL proc_search_gread()";
//        testListByProcedure(daoUtils,sql,null);

//        testListMapByProcedure(daoUtils,sql,null);

        String sql = "{CALL proc_search_user(?,?,?,?)}";
        List<Object> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(Types.INTEGER);
        list.add(Types.INTEGER);
        testListMapByProcedure(daoUtils,sql,list);

    }
    /*
     * @ Description   :  测试查询List<Map<String,Object>>
     * @ Author        :  qijy
     * @ CreateDate    :  2020/10/30 17:39
     */
    private static void testqueryListMap(DaoUtils daoUtils,String sql,List<Object> params) {
        List<Map<String, Object>> maps = daoUtils.queryListMapSqlExecute(sql, params);
        for (Map<String, Object> map : maps) {
            System.out.println(map.get("id"));
            System.out.println(map.get("uid"));
            System.out.println(map.get("grade"));
            System.out.println("-------------");
        }
    }
    private static void testQueryExecute(DaoUtils daoUtils,String sql,List<Object> params){
        List<GradeBean> gradeBeans = daoUtils.querySqlExecute(sql, params, GradeBean.class);
        for (GradeBean gradeBean : gradeBeans) {
            System.out.println(gradeBean.getId());
            System.out.println(gradeBean.getUid());
            System.out.println(gradeBean.getGrade());
            System.out.println("---------------");
        }

    }

    private static void testupdateSqlExecute(DaoUtils daoUtils,String sql,List<Object> params){
        int i = daoUtils.updateSqlExecute(sql, params);
        System.out.println("成功笔数:" + i);
    }

    private static void testListByProcedure(DaoUtils daoUtils,String sql,List<Object> params){
        List<GradeBean> listByProcedure = daoUtils.getListByProcedure(sql, params, GradeBean.class);
        for (GradeBean gradeBean : listByProcedure) {
            System.out.println(gradeBean.getId());
            System.out.println(gradeBean.getUid());
            System.out.println(gradeBean.getGrade());
            System.out.println("======================");
        }

    }

    private static void testListMapByProcedure(DaoUtils daoUtils,String sql,List<Object> params) {
        List<Map<String, Object>> maps = daoUtils.getListMapByProcedure(sql, params);
        for (Map<String, Object> map : maps) {
            System.out.println("字段个数:"+map.size());
            System.out.println(map.get("id"));
            System.out.println(map.get("uid"));
            System.out.println(map.get("grade"));
            System.out.println(map.get("total_count"));
            System.out.println(map.get("total_page"));
            System.out.println("==============");
        }
    }


}
