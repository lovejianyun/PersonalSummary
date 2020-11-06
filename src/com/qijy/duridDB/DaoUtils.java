package com.qijy.duridDB;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoUtils {
    private DuridDatabaseUtil duridDatabaseUtil;

    public DaoUtils() {
        this.duridDatabaseUtil = new DuridDatabaseUtil();
    }
    /*
     * @ Description   :  执行增删改
     * @ Author        :  qijy
     * @ CreateDate    :  2020/10/30 14:55
     */
    public int updateSqlExecute(String sql, List<Object> params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = duridDatabaseUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i=0;i<params.size();i++){
                statement.setObject(i+1,params.get(i));
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            duridDatabaseUtil.close(connection,statement,null);
        }
    }

    public <T> List<T> querySqlExecute(String sql,List<Object> params,Class<T> tClass){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<>();
        try {
            connection = duridDatabaseUtil.getConnection();
            statement = connection.prepareStatement(sql);
            if(null != params){
                for (int i=0;i<params.size();i++){
                    statement.setObject(i+1,params.get(i));
                }
            }
            resultSet = statement.executeQuery();
            // 获取查询结果元数据
            ResultSetMetaData metaData = resultSet.getMetaData();

            // 获取查询的列总数
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                // 创建一个对象
                T t = tClass.newInstance();
                for(int i=1;i<=columnCount;i++){
                    String columnName = metaData.getColumnName(i);
                    Object object = resultSet.getObject(i);
                    if(null == object){
                        continue;
                    }
                    Field field = tClass.getDeclaredField(columnName);
                    // 字段类型
                    String typeName = field.getGenericType().getTypeName();
                    // 数据库值类型
                    field.setAccessible(true);
                    getTypeName((T) t, object, field, typeName);
                    field.setAccessible(false);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            duridDatabaseUtil.close(connection,statement,resultSet);
        }
    }
    /*
     * @ Description   :  查询的结果是map
     * @ Author        :  qijy
     * @ CreateDate    :  2020/10/30 17:20
     */
    public List<Map<String,Object>> queryListMapSqlExecute(String sql,List<Object> params){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            connection = duridDatabaseUtil.getConnection();
            statement = connection.prepareStatement(sql);
            if(null != params){
                for (int i=0;i<params.size();i++){
                    statement.setObject(i+1,params.get(i));
                }
            }
            resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                Map<String,Object> map = new HashMap<>();
                for (int i=1;i<=columnCount;i++){
                    // 字段名
                    String columnName = metaData.getColumnName(i);
                    // 字段值
                    Object object = resultSet.getObject(i);
                    map.put(columnName,object);
                }
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            duridDatabaseUtil.close(connection,statement,resultSet);
        }
    }

    /*
     * @ Description   :  执行存储过程调用
     * @ Author        :  qijy
     * @ CreateDate    :  2020/10/30 16:59
     */
    public <T> List<T> getListByProcedure(String procedureName,List<Object> params,Class<T> tClass){
        Connection connection = null;
        CallableStatement prepareCall = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<>();
        try {
            connection = duridDatabaseUtil.getConnection();
            prepareCall = connection.prepareCall(procedureName);
            if(null != params){
               for (int i=0;i<params.size();i++){
                   prepareCall.setObject(i+1,params.get(i));
               }
            }
            resultSet = prepareCall.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                T t = tClass.newInstance();
                for (int i=1;i<=columnCount;i++){
                    String columnName = metaData.getColumnName(i);
                    Object object = resultSet.getObject(i);
                    Field field = tClass.getDeclaredField(columnName);
                    String typeName = field.getGenericType().getTypeName();
                    field.setAccessible(true);
                    getTypeName((T) t, object, field, typeName);
                    field.setAccessible(false);
                }
                list.add(t);
            }
            return  list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            // 关闭资源
            duridDatabaseUtil.close(connection,prepareCall, resultSet);
        }
    }


    /*
     * @ Description   :  执行存储过程调用
     * @ Author        :  qijy
     * @ CreateDate    :  2020/10/30 16:59
     */
    public  List<Map<String,Object>> getListMapByProcedure(String procedureName,List<Object> params){
        Connection connection = null;
        CallableStatement prepareCall = null;
        ResultSet resultSet = null;
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            connection = duridDatabaseUtil.getConnection();
            prepareCall = connection.prepareCall(procedureName);
            if(null != params){
                for (int i=0;i<params.size();i++){
                    prepareCall.setObject(i+1,params.get(i));
                }
            }
            resultSet = prepareCall.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount1 = metaData.getColumnCount();
            while (resultSet.next()){
                Map<String,Object> map = new HashMap<>();
                for (int i=1;i<=columnCount1;i++){
                    Object object = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    map.put(columnName,object);
                }
                list.add(map);
            }
            return  list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            // 关闭资源
            duridDatabaseUtil.close(connection,prepareCall,resultSet);
        }
    }

    private <T> void getTypeName(T t, Object object, Field field, String typeName) throws IllegalAccessException {
        if (typeName.contains("String")) {
            field.set(t, String.valueOf(object));
        } else if (typeName.contains("int") || typeName.contains("Integer")) {
            field.set(t, Integer.valueOf(object.toString()));
        } else if (typeName.contains("long") || typeName.contains("Long")) {
            field.set(t, Long.valueOf(object.toString()));
        } else if (typeName.contains("BigDecimal")) {
            field.set(t, BigDecimal.valueOf((Double) object));
        } else if (typeName.contains("float") || typeName.contains("Float")) {
            field.set(t, Float.valueOf(object.toString()));
        } else if (typeName.contains("double") || typeName.contains("Double")) {
            field.set(t, Double.valueOf(object.toString()));
        }
    }
}
