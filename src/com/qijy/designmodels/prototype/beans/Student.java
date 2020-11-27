package com.qijy.designmodels.prototype.beans;

public class Student implements Cloneable{
    private String name;
    private Grade grade;

    public Student(String name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        // 对象深克隆,将内部对象复制赋值给克隆对象
        Student student = (Student) super.clone();
        Grade grade = student.getGrade().clone();
        student.setGrade(grade);
        return student;
    }
}
