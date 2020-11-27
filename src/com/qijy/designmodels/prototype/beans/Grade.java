package com.qijy.designmodels.prototype.beans;

public class Grade implements Cloneable{
    // 课程名
    private String coursename;
    // 成绩
    private int grade;

    public Grade(String coursename, int grade) {
        this.coursename = coursename;
        this.grade = grade;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "coursename='" + coursename + '\'' +
                ", grade=" + grade +
                '}';
    }

    @Override
    public Grade clone() throws CloneNotSupportedException {
        return (Grade)super.clone();
    }
}
