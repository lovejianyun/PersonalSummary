package com.qijy.designmodels.prototype;

import com.qijy.designmodels.prototype.beans.Grade;
import com.qijy.designmodels.prototype.beans.Student;

/*
 * @ Description   :  原型模式
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/25 9:47
 */
public class ProtoTypeTest {
    public static void main(String[] args) throws Exception{
        Student student = new Student("qijy",new Grade("语文",100));
        Student clone = student.clone();
        System.out.println(student.getGrade() == clone.getGrade());
    }
}
