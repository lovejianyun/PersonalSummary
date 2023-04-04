package com.qijy.reflects;

import com.qijy.reflects.bean.ABean;
import com.qijy.reflects.bean.CBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/24 14:24
 */
public class ParentReflectTest {
    public static void main(String[] args) throws Throwable{
        ABean aBean = new ABean();
        List<CBean> cBeans = new ArrayList<>();
        CBean cBean = new CBean();
        cBean.setName("qjy");
        cBeans.add(cBean);
        aBean.setContent(cBeans);
        aBean.setTotal(100);
        aBean.setPageNumber(0);
        aBean.setTotalPages(10);
        aBean.setPageSize(10);
        ParentReflectTest parentReflectTest = new ParentReflectTest();
        Object response = parentReflectTest.getResponse(cBeans, 10, 0, aBean);
        if(response instanceof ABean){
            ABean aBean1 = (ABean) response;
            System.out.println(aBean1);
            System.out.println(aBean1.getPageNumber());
            System.out.println(aBean1.getPageSize());
            System.out.println(aBean1.getTotal());
            System.out.println(aBean1.getTotalPages());
            List<CBean> content = aBean1.getContent();
            for (CBean bean : content) {
                System.out.println(bean.getName());
            }
        }
    }


    public <T,E> Object getResponse(List<T> list, int pageSize, int pageNumber, E e) throws Exception{
        int size = list.size();
        // 总页数
        int totalPage = 0;
        if(size % pageSize == 0){
            totalPage = size/pageSize;
        }else{
            totalPage = size/pageSize + 1;
        }
        // 使用sublist重新分页数据
        List<T> result = null;
        if(size < pageSize*(pageNumber+1)){
            result = list.subList(pageSize * (pageNumber), size);
        }else{
            result = list.subList(pageSize*(pageNumber),pageSize*(pageNumber+1));
        }
        Class<?> eClass = e.getClass();
        // 获取父类字节码
        Class<?> superclass = eClass.getSuperclass();
        // 获取父类的所有私有字段
        Field[] declaredFields = superclass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if(declaredField.getName().equals("pageSize")){
                declaredField.set(e,pageSize);
            }
            if(declaredField.getName().equals("pageNumber")){
                declaredField.set(e,pageNumber);
            }
            if(declaredField.getName().equals("totalPages")){
                declaredField.set(e,totalPage);
            }
            if(declaredField.getName().equals("total")){
                declaredField.set(e,size);
            }
            declaredField.setAccessible(false);
        }
        Field content = eClass.getDeclaredField("content");
        content.setAccessible(true);
        content.set(e,result);
        content.setAccessible(false);
        return e;
    }
}
