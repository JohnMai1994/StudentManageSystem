package com.jiadong.dao;

import com.jiadong.bean.Grade;
import com.jiadong.bean.PageBean;

import java.sql.Connection;
import java.util.List;

public interface GradeDao {

    List<Grade> gradeList(Connection connection, PageBean pageBean, Grade grade) ;
    long gradeCount(Connection connection, Grade grade);
    int gradeDelete(Connection connection, String delIds) ;
    int gradeAdd(Connection connection, Grade grade);
    int gradeModify(Connection connection, Grade grade);

}
