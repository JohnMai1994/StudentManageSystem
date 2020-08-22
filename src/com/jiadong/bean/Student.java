package com.jiadong.bean;

import java.util.Date;

public class Student {

    private int stuId;
    private String stuNo;
    private String stuName;
    private String sex;
    private Date birthday;
    private int gradeId=-1;
    private String email;
    private String stuDesc;

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", gradeId=" + gradeId +
                ", email='" + email + '\'' +
                ", stuDesc='" + stuDesc + '\'' +
                '}';
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getStuDesc() {
        return stuDesc;
    }

    public void setStuDesc(String stuDesc) {
        this.stuDesc = stuDesc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
