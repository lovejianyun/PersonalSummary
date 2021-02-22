package com.qijy.collections;

import com.qijy.collections.annotation.DateTime;
import com.qijy.collections.annotation.FiledLike;
import com.qijy.collections.annotation.IgnoreData;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @ Description   :  内存分页,R表示条件查询参数,T表示结果
 * @ Author        :  qijy
 * @ CreateDate    :  2021/1/21 10:37
 */
public class ListPageCommon<R,T> {
    // 条件查询参数
    private R r;
    // 总数据
    private List<T> totalList;
    // 条件筛选后数据
    private List<T> resultList;


    public ListPageCommon(R r, List<T> totalList) {
        this.r = r;
        this.totalList = totalList;
        this.resultList = new ArrayList<>();
        filterList();
    }
    /*
     * @ Description   :  获取分页后数据
     * @ Author        :  qijy
     * @ CreateDate    :  2021/1/21 14:48
     */
    public List<T> getListPaged(int currentPage,int pageSize){
        List<T> result = new ArrayList<>();
        int start = (currentPage-1)*pageSize;
        int end = start + pageSize;
        if(end > resultList.size()){
            end = resultList.size();
        }
        result = resultList.subList(start,end);
        return result;
    }
    /*
     * @ Description   :  获取总页数
     * @ Author        :  qijy
     * @ CreateDate    :  2021/1/21 14:48
     */
    public int getToltalPage(int pageSize){
        int size = resultList.size();
        System.out.println("size:"+size);
        int totalPage = size/pageSize+(size%pageSize==0?0:1);
        return totalPage;
    }
    private void filterList(){
        if(null == totalList || totalList.size()==0 ){
            return;
        }
        Field[] fields = r.getClass().getDeclaredFields();
        // 获取字段值不为空的字段和值
        Map<Field, Object> map = getMap(fields);
        if(map.size() == 0){
            resultList.addAll(totalList);
            return;
        }
        List<T> tmplist = null;
        for (Map.Entry<Field, Object> entry : map.entrySet()) {
            Field key = entry.getKey();
            Object value = entry.getValue();
            tmplist = new ArrayList<>();
            for (T t : totalList) {
                try {
                    Field tkey = t.getClass().getDeclaredField(key.getName());
                    tkey.setAccessible(true);
                    Object o = tkey.get(t);
                    if(o == null && tkey.isAnnotationPresent(DateTime.class)){
                        // null 值处理
                        DateTime annotation1 = key.getAnnotation(DateTime.class);
                        Field[] declaredFields = t.getClass().getDeclaredFields();
                        Object o1 = null;
                        for (Field declaredField : declaredFields) {
                            declaredField.setAccessible(true);
                            if(declaredField.isAnnotationPresent(DateTime.class)){
                                DateTime annotation = declaredField.getAnnotation(DateTime.class);
                                if(annotation != null){
                                    if(annotation.name().equals(annotation1.name())&& annotation.type().equals("")){
                                        o1 = declaredField.get(t);
                                    }
                                }
                            }
                        }
                        if (o1 != null){
                            if(betweenDate(value.toString(),o1.toString(),annotation1.type())){
                                tmplist.add(t);
                            }
                        }

                    }else{
                        if(key.isAnnotationPresent(FiledLike.class)){
                            if(o instanceof String){
                                if(o.toString().contains(value.toString())){
                                    tmplist.add(t);
                                }
                            }else if(o instanceof Integer){
                                if((Integer)o==(Integer)value){
                                    tmplist.add(t);
                                }
                            }
                        }else{
                            if(o instanceof String){
                                if(o.toString().equals(value.toString())){
                                    tmplist.add(t);
                                }
                            }else if(o instanceof Integer){
                                if((Integer)o==(Integer)value){
                                    tmplist.add(t);
                                }
                            }
                        }
                    }
                } catch (NoSuchFieldException|IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            totalList.clear();
            totalList = tmplist;
        }
        if(tmplist != null && tmplist.size() > 0){
            resultList.addAll(tmplist);
        }
    }
    /*
     * @ Description   :  时间是否在范围内
     * @ Author        :  qijy
     * @ CreateDate    :  2021/1/21 17:53
     */
    private boolean betweenDate(String value,String o1,String type){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Boolean falg = false;
        try {
            Date date1 = simpleDateFormat.parse(value);
            Date date2 = simpleDateFormat.parse(o1);
            if("start".equals(type)){
                falg = date1.before(date2);
            }else if("end".equals(type)){
                falg = date1.after(date2);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return falg;
    }

    /*
     * @ Description   :  获取值非空的字段
     * @ Author        :  qijy
     * @ CreateDate    :  2021/1/21 13:23
     */
    private Map<Field,Object> getMap(Field[] fields){
        Map<Field, Object> map = new HashMap<>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if(field.isAnnotationPresent(IgnoreData.class)){
                    continue;
                }
                Object o = field.get(r);
                if (o != null) {
                    if (o instanceof String && o.toString().equals("")) {
                        continue;
                    }
                    map.put(field, o);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}
