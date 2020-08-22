package com.jiadong.dao;

import com.jiadong.bean.Student;
import com.jiadong.bean.PageBean;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface StudentDao {
    List<Map<String , Object>> studentList(Connection connection, PageBean pageBean, Student student, String birthday, String ebirthday) ;
    long studentCount(Connection connection,  Student student, String birthday, String ebirthday);
    int studentDelete(Connection connection, String delIds) ;
    int studentAdd(Connection connection, Student student);
    int studentModify(Connection connection, Student student);

}
